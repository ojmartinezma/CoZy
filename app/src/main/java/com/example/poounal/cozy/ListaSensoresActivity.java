package com.example.poounal.cozy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ListaSensoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sensores);
    }

    public void sensor1(View view){
        //
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
    }
}
