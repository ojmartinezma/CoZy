package com.example.poounal.cozy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

//import de google firebase
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
//nuestra clase estudiante
import com.example.poounal.cozy.Sensor;

public class SensorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        //Instancia a la base de datos
        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        DatabaseReference myRef = fdb.getReference("/sensores/1");

        final TextView textview = (TextView)findViewById(R.id.textView3);
        final TextView nombreSensor = (TextView)findViewById(R.id.textView4);


        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //leeremos un objeto de tipo Estudiante
                GenericTypeIndicator<Sensor> t = new GenericTypeIndicator<Sensor>() {
                };
                Sensor sensor1 = dataSnapshot.getValue(t);

                String resultado = "";

                //Tambien podemos leer los datos como un string
                resultado += "\n\n-----------------------------\n\n";
                resultado += "Como JSON:\n\n";
                resultado += dataSnapshot.getValue().toString();
                resultado += sensor1.getNombre();
                resultado += sensor1.getUltima_medida();
                resultado += sensor1.getUltimo_timestamp();

                //mostramos en el textview
                textview.setText(resultado);
                nombreSensor.setText(sensor1.getNombre());
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
