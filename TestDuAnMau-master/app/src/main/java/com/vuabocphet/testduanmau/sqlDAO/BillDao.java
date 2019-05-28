package com.vuabocphet.testduanmau.sqlDAO;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.vuabocphet.testduanmau.Constant;
import com.vuabocphet.testduanmau.database.SqliteOpenHelper;
import com.vuabocphet.testduanmau.model.Bill;

public class BillDao implements Constant {
    private final SqliteOpenHelper helper;

    public BillDao(SqliteOpenHelper helper) {
        this.helper = helper;
    }

    public void insertBill(Bill bill){
        SQLiteDatabase sqLiteDatabase=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(BILL_COLUMN_ID,bill.getMaHoaDon());
        values.put(BILL_COLUMN_DATE,bill.getNgayMua());

        long a=sqLiteDatabase.insert(TABLE_BILL,null,values);
        sqLiteDatabase.close();
    }

    public void updateBill(Bill bill){
        SQLiteDatabase sqLiteDatabase=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(BILL_COLUMN_DATE,bill.getNgayMua());
        long a=sqLiteDatabase.update(TABLE_BILL,values,BILL_COLUMN_ID + "=?",new String[]{String.valueOf(bill.getMaHoaDon())});
        sqLiteDatabase.close();
    }

    public void deleteBill(String id){
        SQLiteDatabase sqLiteDatabase=helper.getWritableDatabase();
        long a=sqLiteDatabase.delete(TABLE_BILL,BILL_COLUMN_ID + "=?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }
}
