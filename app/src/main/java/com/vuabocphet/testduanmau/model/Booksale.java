package com.vuabocphet.testduanmau.model;

public class Booksale {

    private String MaSach;
    private int SL;


    public String getMaSach() {
        return MaSach;
    }

// --Commented out by Inspection START (10/13/2018 10:05 AM):
//    public void setMaSach(String maSach) {
//        MaSach = maSach;
//    }
// --Commented out by Inspection STOP (10/13/2018 10:05 AM)

    public int getSL() {
        return SL;
    }

// --Commented out by Inspection START (10/13/2018 10:05 AM):
//    public void setSL(int SL) {
//        this.SL = SL;
//    }
// --Commented out by Inspection STOP (10/13/2018 10:05 AM)





    public Booksale(String maSach, int SL) {

        MaSach = maSach;
        this.SL = SL;

    }
}
