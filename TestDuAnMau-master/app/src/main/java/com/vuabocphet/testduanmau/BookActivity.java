package com.vuabocphet.testduanmau;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import com.vuabocphet.testduanmau.adapter.BookAdapter;
import com.vuabocphet.testduanmau.database.SqliteOpenHelper;
import com.vuabocphet.testduanmau.model.Book;
import com.vuabocphet.testduanmau.sqlDAO.BookDao;

import java.util.ArrayList;

import static com.vuabocphet.testduanmau.Constant.COLUMN_GIABIA;
import static com.vuabocphet.testduanmau.Constant.COLUMN_MASACH;
import static com.vuabocphet.testduanmau.Constant.COLUMN_MATHELOAI;
import static com.vuabocphet.testduanmau.Constant.COLUMN_NXB;
import static com.vuabocphet.testduanmau.Constant.COLUMN_SOLUONG;
import static com.vuabocphet.testduanmau.Constant.COLUMN_TACGIA;
import static com.vuabocphet.testduanmau.Constant.TB_COLUMN_NAME;

public class BookActivity extends AppCompatActivity {


    private RecyclerView recyclerviewBook;
    private LinearLayoutManager linearLayoutManager;
    private BookAdapter bookAdapter;
    private ArrayList<Book> bookArrayList;
    private SqliteOpenHelper helper;
    private BookDao bookDao;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        floatingActionButton=findViewById(R.id.flAddBook);
        helper=new SqliteOpenHelper(this);
        bookDao=new BookDao(helper);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        Book book=new Book("1"
//                ,"1234567"
//                ,"123456"
//                ,"123456",10f,5);
//        bookDao.insertSach(book);

        anhXa();
        addRecyclerview();
        floatingActionButton.setImageResource(R.drawable.add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });

    }

    private void anhXa() {
        recyclerviewBook = findViewById(R.id.recyclerviewS);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        bookArrayList = new ArrayList<>();
        bookArrayList.clear();
        Cursor cursor = helper.getData("SELECT * FROM Sach");
        if (cursor != null && cursor.moveToFirst()) {

            do {
                String masach = cursor.getString(cursor.getColumnIndex(COLUMN_MASACH));
                String matheloai = cursor.getString(cursor.getColumnIndex(COLUMN_MATHELOAI));
                String tacgia = cursor.getString(cursor.getColumnIndex(COLUMN_TACGIA));
                String nhaxuatban = cursor.getString(cursor.getColumnIndex(COLUMN_NXB));
                String giabia = cursor.getString(cursor.getColumnIndex(COLUMN_GIABIA));
                String soluong = cursor.getString(cursor.getColumnIndex(COLUMN_SOLUONG));
                Book book=new Book();
                book.setMaSach(masach);
                book.setMaTheLoai(matheloai);
                book.setNXB(nhaxuatban);
                book.setTacGia(tacgia);
                book.setGiaBia(Float.parseFloat(giabia));
                book.setSoLuong(Integer.parseInt(soluong));
                bookArrayList.add(book);
                } while (cursor.moveToNext());

            }



    }
    private void addRecyclerview(){
        bookAdapter=new BookAdapter(bookArrayList,this);
        recyclerviewBook.setLayoutManager(linearLayoutManager);
        recyclerviewBook.setHasFixedSize(true);
        recyclerviewBook.setAdapter(bookAdapter);
    }

    private void addBook(){
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_book);
         TextView txtLogo;
         final EditText edtMaSach;
         final Spinner spBook;
         final EditText edtTacGia;
         final EditText edtNXB;
         final EditText edtGiaBia;
         final EditText edtSoLuong;
         final Button btnAdd;
         Button btnCancel;

        txtLogo = dialog.findViewById(R.id.txtLogo);
        edtMaSach = dialog.findViewById(R.id.edtMaSach);
        spBook = dialog.findViewById(R.id.spBook);
        edtTacGia = dialog.findViewById(R.id.edtTacGia);
        edtNXB = dialog.findViewById(R.id.edtNXB);
        edtGiaBia = dialog.findViewById(R.id.edtGiaBia);
        edtSoLuong = dialog.findViewById(R.id.edtSoLuong);
        btnAdd = dialog.findViewById(R.id.btnAdd);
        btnCancel = dialog.findViewById(R.id.btnCancel);
        Cursor cursor=helper.getData("SELECT * FROM typeBook");
        final ArrayList<String> spinnerTenLoais=new ArrayList<>();
        int i=0;
               spinnerTenLoais.clear();
         if (cursor.moveToFirst()&&cursor!=null){
             do {
                 i++;
                 String tentheloai = cursor.getString(cursor.getColumnIndex(TB_COLUMN_NAME));
                 spinnerTenLoais.add(i+"|"+tentheloai);

                 }while (cursor.moveToNext());

         }
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinnerTenLoais);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spBook.setDropDownVerticalOffset(android.R.layout.simple_spinner_dropdown_item);
          spBook.setAdapter(adapter);

        spBook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String masach=edtMaSach.getText().toString().trim();
                        String tacgia=edtTacGia.getText().toString().trim();
                        String nxb=edtNXB.getText().toString().trim();
                        String giabia=edtGiaBia.getText().toString().trim();
                        String soluong=edtSoLuong.getText().toString().trim();
                        if (masach.equals("")){
                            edtMaSach.setError(getString(R.string.error_PassWord));
                            return;
                        }
                        if (masach.length()>5){
                            edtMaSach.setError("Tối đa 5 kí tự");
                        }


                        boolean test = false;
                        for (int i = 0; i < bookArrayList.size(); i++) {
                            Book book = bookArrayList.get(i);

                            if (masach.equals(book.getMaSach())) {
                                edtMaSach.setError("Mã sách đã tồn tại");
                                test = true;
                                break;
                            }

                        }
                        if (tacgia.equals("")){
                            edtTacGia.setError(getString(R.string.error_PassWord));
                            return;
                        }

                        if (nxb.equals("")){
                            edtNXB.setError(getString(R.string.error_PassWord));
                            return;
                        }

                        if (giabia.equals("")){
                            edtNXB.setError(getString(R.string.error_PassWord));
                            return;
                        }

                        if (soluong.equals("")){
                            edtNXB.setError(getString(R.string.error_PassWord));
                            return;
                        }
                          if (!test){

                              Book book=new Book();
                              book.setMaSach(masach);
                              book.setMaTheLoai(spinnerTenLoais.get(position));
                              book.setTacGia(tacgia);
                              book.setNXB(nxb);
                              book.setGiaBia(Float.parseFloat(giabia));
                              book.setSoLuong(Integer.parseInt(soluong));
                              bookDao.insertSach(book);
                              bookArrayList.add(0,book);
                              addRecyclerview();
                              dialog.dismiss();
                          }
                        }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });









        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();

    }

    public void deleteBook(final String masach, final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có muốn xóa sách với mã" + masach + " không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bookDao.deleteTypeBook(masach);
                bookArrayList.remove(i);
                anhXa();
                addRecyclerview();


            }
        });


        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();

    }

    public void updateBook(String masach,final String maloai, final String tacgia, String nxb,float giabia,int soluong) {

        final Dialog dialog = new Dialog(BookActivity.this);

        dialog.setContentView(R.layout.dialog_edit_book);
         TextView txtLogo;
         final EditText edtMaSach;
         final Spinner spBook;
         final EditText edtTacGia;
         final EditText edtNXB;
         final EditText edtGiaBia;
         final EditText edtSoLuong;
         final Button btnAdd;
         Button btnCancel;

        txtLogo =  dialog.findViewById(R.id.txtLogo);
        edtMaSach =  dialog.findViewById(R.id.edtMaSach);
        spBook =  dialog.findViewById(R.id.spBook);
        edtTacGia =  dialog.findViewById(R.id.edtTacGia);
        edtNXB =  dialog.findViewById(R.id.edtNXB);
        edtGiaBia =  dialog.findViewById(R.id.edtGiaBia);
        edtSoLuong =  dialog.findViewById(R.id.edtSoLuong);
        btnAdd =  dialog.findViewById(R.id.btnAdd);
        btnCancel =  dialog.findViewById(R.id.btnCancel);
        txtLogo.setText("Sửa sách");
        edtMaSach.setText(masach);
        edtMaSach.setEnabled(false);
        edtNXB.setText(nxb);
        edtGiaBia.setText((int) giabia+"");
        edtSoLuong.setText(soluong+"");
        edtTacGia.setText(tacgia);
        btnAdd.setText("Thay đổi");

        
        Cursor cursor=helper.getData("SELECT * FROM typeBook");
        final ArrayList<String> spinnerTenLoais=new ArrayList<>();
        int i=0;
        spinnerTenLoais.add(0,maloai);

        spinnerTenLoais.clear();
        if (cursor.moveToFirst()&&cursor!=null){
            do {
                i++;
                String tentheloai = cursor.getString(cursor.getColumnIndex(TB_COLUMN_NAME));
                spinnerTenLoais.add(i+"|"+tentheloai);

            }while (cursor.moveToNext());

        }
        final ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinnerTenLoais);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBook.setAdapter(adapter);

        spBook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String masach=edtMaSach.getText().toString().trim();
                        String tacgia=edtTacGia.getText().toString().trim();
                        String nxb=edtNXB.getText().toString().trim();
                        String giabia=edtGiaBia.getText().toString().trim();
                        String soluong=edtSoLuong.getText().toString().trim();
                        if (masach.equals("")){
                            edtMaSach.setError(getString(R.string.error_PassWord));
                            return;
                        }
                        if (masach.length()>5){
                            edtMaSach.setError(getString(R.string.error_pos));
                        }


                        if (tacgia.equals("")){
                            edtTacGia.setError(getString(R.string.error_PassWord));
                            return;
                        }

                        if (nxb.equals("")){
                            edtNXB.setError(getString(R.string.error_PassWord));
                            return;
                        }

                        if (giabia.equals("")){
                            edtNXB.setError(getString(R.string.error_PassWord));
                            return;
                        }

                        if (soluong.equals("")){
                            edtNXB.setError(getString(R.string.error_PassWord));
                            return;
                        }


                               Book book=new Book();
                               book.setMaSach(masach);
                               book.setMaTheLoai(spinnerTenLoais.get(position));
                               book.setTacGia(tacgia);
                               book.setGiaBia(Float.parseFloat(giabia));
                               book.setNXB(nxb);
                               book.setSoLuong(Integer.parseInt(soluong));
                               bookDao.updateTypeBook(book);
                               anhXa();
                               addRecyclerview();
                               dialog.dismiss();


                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });



        dialog.show();
    }
}
