package com.example.poounal.cozy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
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

    private FirebaseDatabase fdb;
    private DatabaseReference myRef;
    private EditText idSensor1;
    private EditText nombreSensor1;
    private Switch estadoSensor1;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        //Instancia a la base de datos
        fdb = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        myRef = fdb.getReference("/sensores/1");

        final EditText idSensor = (EditText) findViewById(R.id.editText);
        final EditText nombreSensor = (EditText) findViewById(R.id.editText2);
        final Switch estadoSensor = (Switch) findViewById(R.id.switch1);

        idSensor1=idSensor;
        nombreSensor1=idSensor;
        estadoSensor1=estadoSensor;


        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //leeremos un objeto de tipo Sensor
                GenericTypeIndicator<Sensor> t = new GenericTypeIndicator<Sensor>() {
                };
                Sensor sensor1 = dataSnapshot.getValue(t);

                sensor = sensor1;

                idSensor.setText("1");
                idSensor.setEnabled(false);
                nombreSensor.setText(sensor1.getNombre());
                estadoSensor.setChecked(sensor1.isActivo());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(), "ERROR FIREBASE"+error.getMessage(), Toast.LENGTH_SHORT);
                toast1.show();
            }

        });

    }

    public void guardarSensor(View view){
        sensor.setNombre(nombreSensor1.getText().toString());
//        myRef.child("nombre").setValue(nombreSensor1.getText());
//        myRef.child("activo").setValue(estadoSensor1.isChecked());
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Datos guardados", Toast.LENGTH_SHORT);

        toast1.show();
    }
}
