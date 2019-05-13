package com.vuabocphet.testduanmau.sqlDAO;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vuabocphet.testduanmau.Constant;
import com.vuabocphet.testduanmau.database.SqliteOpenHelper;
import com.vuabocphet.testduanmau.model.Billdetails;

public class BilldetailsDAO implements Constant {

    private final SqliteOpenHelper database;

    public BilldetailsDAO(SqliteOpenHelper database) {
        this.database = database;
    }

    public void insert(Billdetails billdetails){
        SQLiteDatabase sqLiteDatabase=database.getWritableDatabase();
        ContentValues values=new ContentValues();
            values.put(B_COLUMN_IDBILL,billdetails.getIdbill());
            values.put(B_COLUMN_IDBOOK,billdetails.getIdbook());
            values.put(B_COLUMN_POS,billdetails.getSoluong());

            long a=sqLiteDatabase.insert(TABLE_HOADONCHITIET,null,values);
            sqLiteDatabase.close();
        Log.e("Tinh",a+"");
    }


            public void delete(String id){
                SQLiteDatabase sqLiteDatabase=database.getWritableDatabase();
               long a= sqLiteDatabase.delete(TABLE_HOADONCHITIET,B_COLUMN_ID + "=?",new String[]{id});
               Log.e("Tinhx",a+"");
            }

    public void delete1(String idBill){
        SQLiteDatabase sqLiteDatabase=database.getWritableDatabase();
        long a= sqLiteDatabase.delete(TABLE_HOADONCHITIET,B_COLUMN_IDBILL + "=?",new String[]{idBill});
        Log.e("Tinhx",a+"");
    }
}
