package com.example.poounal.cozy;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Date;

public class MonitoreoActivity extends AppCompatActivity {

    private GraphView grafica;
    private LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {});
    private double graphLastXValue = 5d;
    private int color;



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
        final TextView nombreSensor = (TextView)findViewById(R.id.textView16);
        final TextView consejo =(TextView)findViewById(R.id.textView3);
        grafica = (GraphView)findViewById(R.id.grafica);
        grafica.getViewport().setXAxisBoundsManual(true);
        grafica.getViewport().setMinX(0);
        grafica.getViewport().setMaxX(4);

        grafica.getGridLabelRenderer().setLabelVerticalWidth(100);

        // first mSeries is a line
        series = new LineGraphSeries<>();
        series.setDrawDataPoints(true);
        series.setDrawBackground(true);
        grafica.getGridLabelRenderer().setVerticalAxisTitle("ppm");
        grafica.getViewport().setMinY(0);
        grafica.getViewport().setMaxY(1500);
        grafica.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        //grafica.getViewport().setYAxisBoundsManual(true);
        grafica.addSeries(series);

        color = medidaSensor.getCurrentTextColor();


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
                if(sensor1.isActivo()) {
                    nombreSensor.setText(sensor1.getNombre());
                    nombreSensor.setTextColor(color);
                } else {
                    nombreSensor.setText(sensor1.getNombre()+" (Sensor deshabilitado)");
                    nombreSensor.setTextColor(Color.RED);
                }

                if (sensor1.getUltima_medida()>=1000){
                    medidaSensor.setTextColor(Color.RED);
                    consejo.setText("PPM muy alto, ventile el área");
                    consejo.setVisibility(View.VISIBLE);
                    consejo.setBackgroundColor(Color.RED);
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if(Build.VERSION.SDK_INT>=26){
                        ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(1000,-1));
                    } else {
                        ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(1000);
                    }
                } else if (sensor1.getUltima_medida()>=800){
                    medidaSensor.setTextColor(Color.rgb(255,165,0));
                    consejo.setText("PPM elevado");
                } else if (sensor1.getUltima_medida()>=500){
                    medidaSensor.setTextColor(Color.DKGRAY);
                    consejo.setText("");
                    consejo.setBackgroundColor(Color.YELLOW);
                } else {
                    medidaSensor.setTextColor(color);
                    consejo.setText("");
                    consejo.setBackgroundColor(Color.GREEN);
                }

                graphLastXValue += 0.25d;
                series.appendData(new DataPoint(graphLastXValue, sensor1.getUltima_medida()), true, 22);


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
