package com.vuabocphet.testduanmau.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vuabocphet.testduanmau.Constant;


public class SqliteOpenHelper extends SQLiteOpenHelper implements Constant {

    public SqliteOpenHelper(Context context) {
        super(context, "BookManager", null, 1);

    }

    public Cursor getData(String sql){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        return sqLiteDatabase.rawQuery(sql,null);
    }





    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_SACH);
        db.execSQL(CREATE_TABLE_TYPE_BOOK);
        db.execSQL(CREATE_TABLE_BILL_BOOK);
        db.execSQL(CREATE_TABLE_HOADONCHITIET);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SACH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE_BOOK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOADONCHITIET);
        onCreate(db);
    }
}
