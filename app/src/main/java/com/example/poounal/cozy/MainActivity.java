package com.example.poounal.cozy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sensores(View view){
        //
        Intent intent = new Intent(this, ListaSensoresActivity.class);
        startActivity(intent);
    }

    public void monitoreo(View view){
        //
        Intent intent = new Intent(this, MonitoreoActivity.class);
        startActivity(intent);
    }

    public void informes(View view){
        //
        Intent intent = new Intent(this, ListaSensoresActivity.class);
        startActivity(intent);
    }

    public void exportar(View view){
        //
        Intent intent = new Intent(this, ListaSensoresActivity.class);
        startActivity(intent);
    }

}
