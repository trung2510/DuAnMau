package com.vuabocphet.testduanmau;

public interface Constant {

    //TABLE User

    //Tên bảng
    String TABLE_USER = "User";
    String TABLE_SACH = "Sach";
    String TABLE_HOADONCHITIET = "HoaDonChiTiet";
    String TABLE_TYPE_BOOK = "typeBook";
    String TABLE_BILL="HoaDon";




    //Tên cột
    String COLUMN_USER = "username";
    String COLUMN_PASSWORD = "Password";
    String COLUMN_PHONE = "Phone";
    String COLUMN_HOTEN = "hoTen";


    String COLUMN_MASACH = "MaSach";
    String COLUMN_MATHELOAI = "MaTheLoai";
    String COLUMN_TACGIA = "TacGia";
    String COLUMN_NXB = "NXB";
    String COLUMN_GIABIA = "giaBia";
    String COLUMN_SOLUONG = "soLuong";


    String TB_COLUMN_ID = "MaTheLoai";
    String TB_COLUMN_NAME = "typeName";
    String TB_COLUMN_DES = "Description";
    String TB_COLUMN_POS = "Position";

    String BILL_COLUMN_ID="MaHoaDon";
    String BILL_COLUMN_DATE="NgayMua";


    String B_COLUMN_ID="MaHDCT";
    String B_COLUMN_IDBILL="MaHoaDon";
    String B_COLUMN_IDBOOK="MaSach";
    String B_COLUMN_POS="SoLuong";





    //Câu lệnh tạo bảng

    String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" +

            COLUMN_USER + " NVARCHAR(50) PRIMARY KEY," +

            COLUMN_PASSWORD + " NVARCHAR(50) NOT NULL ," +

            COLUMN_PHONE + " NCHAR(50) NOT NULL  ," +

            COLUMN_HOTEN + " NVARCHAR(50) " +

            ")";

//         TABLE BOOK



       String CREATE_TABLE_SACH="CREATE TABLE " + TABLE_SACH + "(" +

               COLUMN_MASACH + " NCHAR(5) PRIMARY KEY NOT NULL," +

               COLUMN_MATHELOAI + " NCHAR(50)," +

               COLUMN_TACGIA + " NVARCHAR(50)," +

               COLUMN_NXB + " NVARCHAR(50) NOT NULL," +

               COLUMN_GIABIA + " FLOAT NOT NULL," +

               COLUMN_SOLUONG + " INTEGER  NOT NULL" +

               ")";


       //TABLE HOADONCHITIET

    String CREATE_TABLE_HOADONCHITIET="CREATE TABLE " + TABLE_HOADONCHITIET + "(" +

               B_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

               B_COLUMN_IDBILL + " NCHAR(7) NOT NULL," +

               B_COLUMN_IDBOOK + " NCHAR(5) NOT NULL," +

               B_COLUMN_POS + " INTEGER NOT NULL" +

            ")";



































    //TABLE BẢNG TYPE BOOK

    String CREATE_TABLE_TYPE_BOOK="CREATE TABLE " + TABLE_TYPE_BOOK +"("+

            TB_COLUMN_ID +" CHAR(5) PRIMARY KEY NOT NULL,"+

            TB_COLUMN_NAME +" NVARCHAR(50) NOT NULL,"+

            TB_COLUMN_DES +" NVARCHAR(255) ,"+

            TB_COLUMN_POS +" INT "+

            ")";


    String CREATE_TABLE_BILL_BOOK="CREATE TABLE " + TABLE_BILL +"("+

            BILL_COLUMN_ID +" NCHAR(5) PRIMARY KEY NOT NULL,"+

            BILL_COLUMN_DATE +" LONG NOT NULL"+

            ")";


}
