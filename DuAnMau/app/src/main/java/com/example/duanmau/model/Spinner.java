package com.example.duanmau.model;

public class Spinner {

    private String masach;
    private float giabia;

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public float getGiabia() {
        return giabia;
    }

    public void setGiabia(float giabia) {
        this.giabia = giabia;
    }


    public Spinner(String masach, float giabia) {

        this.masach = masach;

        this.giabia = giabia;
    }

}