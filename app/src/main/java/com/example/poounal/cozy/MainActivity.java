package com.example.poounal.cozy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button botonInformes = (Button) findViewById(R.id.boton_informes);
        botonInformes.setVisibility(View.INVISIBLE);
    }

    public void sensors(View view){
        //
        Intent intent = new Intent(this, ListaSensoresActivity.class);
        startActivity(intent);
    }

    public void monitored(View view){
        //
        Intent intent = new Intent(this, MonitoreoActivity.class);
        startActivity(intent);
    }

    public void informs(View view){
        //
        Intent intent = new Intent(this, InformeActivity.class);
        startActivity(intent);
    }

    public void export(View view){
        //
        Intent intent = new Intent(this, ListaSensoresActivity.class);
        startActivity(intent);
    }

}
