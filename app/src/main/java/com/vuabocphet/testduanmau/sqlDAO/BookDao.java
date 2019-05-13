package com.vuabocphet.testduanmau.sqlDAO;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.vuabocphet.testduanmau.Constant;
import com.vuabocphet.testduanmau.database.SqliteOpenHelper;
import com.vuabocphet.testduanmau.model.Book;

public class BookDao implements Constant {
      private final SqliteOpenHelper helper;

    public BookDao(SqliteOpenHelper helper) {
        this.helper = helper;
    }

    public void insertSach(Book book) {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MASACH, book.getMaSach());
        values.put(COLUMN_MATHELOAI, book.getMaTheLoai());
        values.put(COLUMN_TACGIA, book.getTacGia());
        values.put(COLUMN_NXB, book.getNXB());
        values.put(COLUMN_GIABIA, book.getGiaBia());
        values.put(COLUMN_SOLUONG, book.getSoLuong());
        sqLiteDatabase.insert(TABLE_SACH, null, values);
        sqLiteDatabase.close();

    }

    public void updateTypeBook(Book book){
        SQLiteDatabase sqLiteOpenHelper = helper.getWritableDatabase();

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MASACH, book.getMaSach());
        values.put(COLUMN_MATHELOAI, book.getMaTheLoai());
        values.put(COLUMN_TACGIA, book.getTacGia());
        values.put(COLUMN_NXB, book.getNXB());
        values.put(COLUMN_GIABIA, book.getGiaBia());
        values.put(COLUMN_SOLUONG, book.getSoLuong());
        long result= sqLiteOpenHelper.update(helper.TABLE_SACH,values, COLUMN_MASACH+"=?",new String[]{String.valueOf(book.getMaSach())});
        sqLiteOpenHelper.close();
    }

    public void deleteTypeBook(String maID){
        SQLiteDatabase sqLiteOpenHelper = helper.getWritableDatabase();
        long delete=sqLiteOpenHelper.delete(TABLE_SACH, COLUMN_MASACH+"=?",new String[]{String.valueOf(maID)});
        sqLiteOpenHelper.close();
    }

// --Commented out by Inspection START (10/13/2018 10:05 AM):
//    public Book getDataSach(String book){
//        Book book1=null;
//        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
//        Cursor cursor=sqLiteDatabase.query(TABLE_SACH,new String[]{
//                        COLUMN_MASACH,
//                        COLUMN_MATHELOAI,
//                        COLUMN_TACGIA,
//                        COLUMN_NXB,
//                        COLUMN_GIABIA,
//                        COLUMN_SOLUONG},
//                COLUMN_MASACH + "=?"
//                ,new String[]{book},null,null,null);
//        if (cursor!=null && cursor.moveToFirst()){
//            String ma_sach=cursor.getString(cursor.getColumnIndex(COLUMN_MASACH));
//            String ma_the_loai=cursor.getString(cursor.getColumnIndex(COLUMN_MATHELOAI));
//            String tac_gia=cursor.getString(cursor.getColumnIndex(COLUMN_TACGIA));
//            String nxb=cursor.getString(cursor.getColumnIndex(COLUMN_NXB));
//            float gia_bia=cursor.getFloat(cursor.getColumnIndex(COLUMN_GIABIA));
//            int so_luong=cursor.getInt(cursor.getColumnIndex(COLUMN_SOLUONG));
//            book1=new Book();
//            book1.setMaSach(ma_sach);
//            book1.setMaTheLoai(ma_the_loai);
//            book1.setTacGia(tac_gia);
//            book1.setNXB(nxb);
//            book1.setGiaBia(gia_bia);
//            book1.setSoLuong(so_luong);
//        }
//
//
//        return book1;
//    }
// --Commented out by Inspection STOP (10/13/2018 10:05 AM)
}
