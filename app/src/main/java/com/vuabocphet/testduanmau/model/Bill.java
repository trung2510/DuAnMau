package com.vuabocphet.testduanmau.model;

public class Bill {

  private String MaHoaDon;
  private long NgayMua;

  public String getMaHoaDon() {
    return MaHoaDon;
  }

// --Commented out by Inspection START (10/13/2018 10:05 AM):
//  public void setMaHoaDon(String maHoaDon) {
//    MaHoaDon = maHoaDon;
//  }
// --Commented out by Inspection STOP (10/13/2018 10:05 AM)

  public long getNgayMua() {
    return NgayMua;
  }

// --Commented out by Inspection START (10/13/2018 10:05 AM):
//  public void setNgayMua(long ngayMua) {
//    NgayMua = ngayMua;
//  }
// --Commented out by Inspection STOP (10/13/2018 10:05 AM)

  public Bill(String maHoaDon, long ngayMua) {

    MaHoaDon = maHoaDon;
    NgayMua = ngayMua;
  }
}
