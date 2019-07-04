package com.example.poounal.cozy;

import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Date;

public class MonitoreoActivity extends AppCompatActivity {

    private GraphView grafica;
    private LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {});



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoreo);

        //Instancia a la base de datos
        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        DatabaseReference myRef = fdb.getReference("/sensores/1");

        final TextView ubicacionSensor = (TextView)findViewById(R.id.textView16);
        final TextView medidaSensor = (TextView)findViewById(R.id.textView17);
        final TextView timestampSensor = (TextView)findViewById(R.id.textView5);
        final SimpleDateFormat horaFormato = new SimpleDateFormat("HH:mm:ss");
        grafica = (GraphView)findViewById(R.id.grafica_monitoreo);



//        grafica.getViewport().setXAxisBoundsManual(true);
        grafica.getViewport().setMinY(0);
        grafica.getViewport().setMaxY(1000);

        grafica.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX)
                {
                    return horaFormato.format(new Date((long)value));
                }
                return super.formatLabel(value, isValueX);
            }
        });

        grafica.getGridLabelRenderer().setHorizontalLabelsAngle(90);

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //leeremos un objeto de tipo Sensor
                GenericTypeIndicator<Sensor> t = new GenericTypeIndicator<Sensor>() {
                };
                Sensor sensor1 = dataSnapshot.getValue(t);

                String ultimaMedida = "";
                ultimaMedida += sensor1.getUltima_medida();
                ultimaMedida += " ppm";

                medidaSensor.setText(ultimaMedida);
                Date fecha = new java.util.Date(sensor1.getUltimo_timestamp()*1000);
                timestampSensor.setText("Ultima medida tomada:\n"+fecha.toString());


                String horaString = horaFormato.format(fecha);

                grafica.removeAllSeries();

                series.appendData(new DataPoint(fecha.getTime(),sensor1.getUltima_medida()),true,10);

                grafica.addSeries(series);



            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(), "ERROR FIREBASE"+error.getMessage(), Toast.LENGTH_SHORT);
                toast1.show();
            }

        });

    }
}
