package com.example.duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duanmau.dao.NguoiDungDAO;
import com.example.duanmau.dao.SachDAO;
import com.example.duanmau.dao.TheLoaiDAO;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static  final String DATABASE_NAME ="dbBook";
    public static  final int VERSION =1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NguoiDungDAO.SQL_NGUOI_DUNG);
        db.execSQL(SachDAO.SQL_SACH);
        db.execSQL(TheLoaiDAO.SQL_THELOAI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" drop table if exists " + NguoiDungDAO.TABLE_NguoiDung);
        db.execSQL(" drop table if exists " + SachDAO.TABLE_SACH);
        db.execSQL(" drop table if exists " + TheLoaiDAO.TABLE_THELOAI);
    }
}
