package com.example.poounal.cozy;

public class Sensor {
    private int id;
    private String nombre;
    private int intervalo;
    private boolean activo;
    private float ultima_medida;
    private long ultimo_timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public String getNombre() {
        return nombre;
    }

    public float getUltima_medida() {
        return ultima_medida;
    }

    public void setUltima_medida(float ultima_medida) {
        this.ultima_medida = ultima_medida;
    }

    public long getUltimo_timestamp() {
        return ultimo_timestamp;
    }

    public void setUltimo_timestamp(long ultimo_timestamp) {
        this.ultimo_timestamp = ultimo_timestamp;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
