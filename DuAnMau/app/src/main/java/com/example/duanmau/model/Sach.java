package com.example.duanmau.model;

public class Sach {

    private String MaSach;
    private String MaTheLoai;
    private String TacGia;
    private String NXB;
    private float giaBia;
    private int soLuong;

    public Sach(String maSach, String tacGia, String NXB, float giaBia, int soLuong) {
        MaSach = maSach;
        TacGia = tacGia;
        this.NXB = NXB;
        this.giaBia = giaBia;
        this.soLuong = soLuong;
    }

    public Sach() {
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String maSach) {
        MaSach = maSach;
    }

    public String getMaTheLoai() {
        return MaTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        MaTheLoai = maTheLoai;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String tacGia) {
        TacGia = tacGia;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public float getGiaBia() {
        return giaBia;
    }

    public void setGiaBia(float giaBia) {
        this.giaBia = giaBia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
