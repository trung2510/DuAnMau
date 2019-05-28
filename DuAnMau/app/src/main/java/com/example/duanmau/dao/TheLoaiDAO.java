package com.example.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    public static final String SQL_THELOAI = "CREATE TABLE TheLoai (" +
            " maTheLoai text primary key," +
            " tenTheLoai text," +
            " moTa text," +
            " viTri text" +
            ");";
    public static final String TABLE_THELOAI = "TheLoai";
    public static final String TAG = "TheLoaiDAO: ";

    public TheLoaiDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }
    public int insertTheLoai(TheLoai theLoai){
        ContentValues values = new ContentValues();
        values.put("maTheLoai",theLoai.getMaTheLoai());
        values.put("tenTheLoai",theLoai.getTenTheLoai());
        values.put("moTa",theLoai.getMoTa());
        values.put("viTri",theLoai.getViTri());
        try{
            if (db.insert(TABLE_THELOAI,null,values) <0) {
                return -1;
            }
        }
        catch (Exception ex){
            Log.e(TAG, ex.getMessage());
        }
        return 1;
    }

    public List<TheLoai> getAllTheLoai(){
        ArrayList<TheLoai> listTheLoai = new ArrayList<>();
        Cursor cursor = db.query(TABLE_THELOAI,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                TheLoai theLoai = new TheLoai();
                theLoai.setMaTheLoai(cursor.getString(0));
                theLoai.setTenTheLoai(cursor.getString(1));
                theLoai.setMoTa(cursor.getString(2));
                theLoai.setViTri(cursor.getString(3));
            }
            while (cursor.moveToNext());
        }
        db.close();
    return listTheLoai;
    }

    public  List<TheLoai> getAll (){
        ArrayList<TheLoai> listTheLoai = new ArrayList<>();
        String dem = "SELECT * FROM " +TABLE_THELOAI;

        Cursor cursor = db.rawQuery(dem,null);
        if (cursor.moveToFirst()){
            do {
                TheLoai theLoai = new TheLoai();
                theLoai.setMaTheLoai(cursor.getString(0));
                theLoai.setTenTheLoai(cursor.getString(1));
                theLoai.setMoTa(cursor.getString(2));
                theLoai.setViTri(cursor.getString(3));
            }
            while (cursor.moveToNext());
        }
        db.close();
        return listTheLoai;
    }
}
