package com.example.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.Sach;

public class SachDAO {
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    public static final String TAG = "SachDAO";
    public static final String SQL_SACH = "CREATE TABLE Sach (" +
            " MaSach text primary key," +
            " MaTheLoai text," +
            " TacGia text," +
            " NXB text," +
            " giaBia float," +
            " soLuong integer" +
            ");";
    public static final String TABLE_SACH = "Sach: ";

    public SachDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }
    public int insertSach(Sach sach){
        ContentValues values = new ContentValues();
        values.put("MaSach",sach.getMaSach());
        values.put("MaTheLoai",sach.getMaTheLoai());
        values.put("TacGia",sach.getTacGia());
        values.put("NXB",sach.getNXB());
        values.put("giaBia",sach.getGiaBia());
        values.put("soLuong",sach.getSoLuong());
        try{
            if (db.insert(TABLE_SACH,null,values)<0) {
                return -1;
            }
        }
        catch (Exception ex){
            Log.e(TAG, ex.getMessage());
        }
        return 1;
    }
}
