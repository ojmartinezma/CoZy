package com.example.poounal.cozy;

public class Medida {
    private double medida;
    private int sensor;
    private int timestamp;

    public Medida(double medida, int sensor, int timestamp) {
        this.medida = medida;
        this.sensor = sensor;
        this.timestamp = timestamp;
    }

    public double getMedida() {
        return medida;
    }

    public void setMedida(double medida) {
        this.medida = medida;
    }

    public int getSensor() {
        return sensor;
    }

    public void setSensor(int sensor) {
        this.sensor = sensor;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
