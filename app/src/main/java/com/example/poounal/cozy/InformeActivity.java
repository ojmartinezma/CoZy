package com.example.poounal.cozy;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Map;

public class InformeActivity extends AppCompatActivity {

    private FirebaseDatabase fdb;
    private DatabaseReference myRef;
    GraphView grafica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Instancia a la base de datos
        fdb = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        myRef = fdb.getReference("/medidas");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        grafica = findViewById(R.id.grafica);






    }

    public void load(View view) {
        grafica = findViewById(R.id.grafica);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String,Object> medidas = (Map<String,Object>) dataSnapshot.getValue();
                ArrayList<DataPoint> puntos = new ArrayList<DataPoint>();
                for(Map.Entry<String,Object> entry : medidas.entrySet()){
                    //
                    Map medida = (Map) entry.getValue();
                    int timestamp = (int) medida.get("timestamp");
                    double co2 = (double) medida.get("co2");
                    puntos.add(new DataPoint(timestamp,co2));
                }
                DataPoint[] arrayPuntos = (DataPoint[])puntos.toArray();
                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(arrayPuntos);
                grafica.addSeries(series);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
//                new DataPoint(1,1),
//                new DataPoint(2,2),
//                new DataPoint(3,3),
//                new DataPoint(4,4),
//                new DataPoint(5,5),
//                new DataPoint(6,6),
//        });
//
//        grafica.addSeries(series);
    }

}
