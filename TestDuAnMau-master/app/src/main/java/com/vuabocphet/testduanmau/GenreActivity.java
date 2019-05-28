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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vuabocphet.testduanmau.adapter.GenreAdapter;
import com.vuabocphet.testduanmau.database.SqliteOpenHelper;
import com.vuabocphet.testduanmau.model.TypeBook;
import com.vuabocphet.testduanmau.sqlDAO.TypeBookDAO;

import java.util.ArrayList;

import static com.vuabocphet.testduanmau.Constant.TB_COLUMN_DES;
import static com.vuabocphet.testduanmau.Constant.TB_COLUMN_ID;
import static com.vuabocphet.testduanmau.Constant.TB_COLUMN_NAME;
import static com.vuabocphet.testduanmau.Constant.TB_COLUMN_POS;

public class GenreActivity extends AppCompatActivity {

    private RecyclerView recyclerviewLS;
    private LinearLayoutManager linearLayoutManager;
    private GenreAdapter genreAdapter;
    private ArrayList<TypeBook> typeBookArrayList;
    private Cursor cursor;
    private TypeBookDAO typeBookDAO;
    private SqliteOpenHelper sqLiteOpenHelper;
    private FloatingActionButton flAddTypeBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sqLiteOpenHelper = new SqliteOpenHelper(this);
        typeBookDAO=new TypeBookDAO(sqLiteOpenHelper);
        TypeBook typeBook=new TypeBook("1"
                ,"1234567"
                ,"123456"
                ,"123456");
        typeBookDAO.insertTypeBook(typeBook);
        anhXa();
        addRecyclerview();
        flAddTypeBook.setImageResource(R.drawable.add);
        flAddTypeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTypeBook();
            }
        });
    }

    private void anhXa(){
        flAddTypeBook=findViewById(R.id.flAddType);
        recyclerviewLS =findViewById(R.id.recyclerviewLS);
        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        typeBookArrayList=new ArrayList<>();
        typeBookArrayList.clear();
        cursor = sqLiteOpenHelper.getData("SELECT * FROM typeBook");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String matheloai = cursor.getString(cursor.getColumnIndex(TB_COLUMN_ID));
                String tentheloai = cursor.getString(cursor.getColumnIndex(TB_COLUMN_NAME));
                String mota = cursor.getString(cursor.getColumnIndex(TB_COLUMN_DES));
                String vitri = cursor.getString(cursor.getColumnIndex(TB_COLUMN_POS));
                TypeBook typeBook=new TypeBook();
                typeBook.setMaTheLoai(matheloai);
                typeBook.setTentheloai(tentheloai);
                typeBook.setMota(mota);
                typeBook.setVitri(vitri);
                typeBookArrayList.add(typeBook);


                } while (cursor.moveToNext());


        }


    }

    private void addRecyclerview(){
        genreAdapter=new GenreAdapter(typeBookArrayList,this);
        recyclerviewLS.setLayoutManager(linearLayoutManager);
        recyclerviewLS.setHasFixedSize(true);
        recyclerviewLS.setAdapter(genreAdapter);
    }

    public void deleteTypeBook(final String maloai, final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có muốn xóa loại sách với mã" + maloai + " không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                typeBookDAO.deleteTypeBook(maloai);
                typeBookArrayList.remove(i);
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

    private void insertTypeBook(){
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit_typebook);

         final EditText edtMaLoaSach;
         final EditText edtTenTheLoai;
         final EditText edtMoTaLoaiSach;
         final EditText edtViTri;
         Button btnAdd;
         Button btnCancel;edtMaLoaSach = dialog.findViewById(R.id.edtMaLoaSach);
        edtTenTheLoai =  dialog.findViewById(R.id.edtTenTheLoai);
        edtMoTaLoaiSach =  dialog.findViewById(R.id.edtMoTaLoaiSach);
        edtViTri =  dialog.findViewById(R.id.edtViTri);
        btnAdd =  dialog.findViewById(R.id.btnAdd);
        btnCancel = dialog.findViewById(R.id.btnCancel);

         btnAdd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String maloaisach=edtMaLoaSach.getText().toString().trim();
        String tenloaisach=edtTenTheLoai.getText().toString().trim();
        String mota=edtMoTaLoaiSach.getText().toString().trim();
        String vitri=edtViTri.getText().toString().trim();
        if (maloaisach.equals("")) {
            edtMaLoaSach.setError(getString(R.string.error_PassWord));
            return;
        }
        if (maloaisach.length() > 5) {
            edtMaLoaSach.setError(getString(R.string.error_length));
            return;
        }

        boolean test = false;
        for (int i = 0; i < typeBookArrayList.size(); i++) {
            TypeBook typeBook = typeBookArrayList.get(i);
            if (maloaisach.equals(typeBook.getMaTheLoai())) {
                edtMaLoaSach.setError("Loại sách đã tồn tại");
                test = true;
                break;
            }
        }

        if (tenloaisach.equals("")) {
            edtTenTheLoai.setError(getString(R.string.error_length));
            return;
        }
        if (tenloaisach.length() > 50) {
            edtTenTheLoai.setError(getString(R.string.error_length1));
            return;
        }
        if (mota.equals("")) {
            edtMoTaLoaiSach.setError(getString(R.string.error_length));
            return;
        }
        if (vitri.equals("")) {
            edtViTri.setError(getString(R.string.error_length));
            return;
        }

        if (!test) {
            TypeBook typebook = new TypeBook(maloaisach, tenloaisach, mota, vitri);
            typeBookDAO.insertTypeBook(typebook);
            typeBookArrayList.add(0,typebook);

            addRecyclerview();
            dialog.dismiss();
        }
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

    public void updateTypeBook(String maloai,final String tenloai, final String mota1, String vitri) {

        final Dialog dialog = new Dialog(GenreActivity.this);

          dialog.setContentView(R.layout.dialog_edit_typebook);
        final EditText edtMaLoaSach;
        final EditText edtTenTheLoai;
        final EditText edtMoTaLoaiSach;
        final EditText edtViTri;
        final TextView txtLogo;
        Button btnAdd;
        Button btnCancel;
        edtMaLoaSach = dialog.findViewById(R.id.edtMaLoaSach);
        edtTenTheLoai =  dialog.findViewById(R.id.edtTenTheLoai);
        edtMoTaLoaiSach =  dialog.findViewById(R.id.edtMoTaLoaiSach);
        edtViTri =  dialog.findViewById(R.id.edtViTri);
        btnAdd =  dialog.findViewById(R.id.btnAdd);
        btnCancel = dialog.findViewById(R.id.btnCancel);
        txtLogo=dialog.findViewById(R.id.txtLogo);
        txtLogo.setText("Sửa loại sách");
        edtMaLoaSach.setText(maloai);
        edtMoTaLoaiSach.setText(mota1);
        edtTenTheLoai.setText(tenloai);
        edtViTri.setText(vitri);
        Toast.makeText(this, ""+vitri, Toast.LENGTH_SHORT).show();

        edtMaLoaSach.setEnabled(false);
        btnAdd.setText("Thay đổi");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maloaisach=edtMaLoaSach.getText().toString().trim();
                String tenloaisach=edtTenTheLoai.getText().toString().trim();
                String mota=edtMoTaLoaiSach.getText().toString().trim();
                String vitri=edtViTri.getText().toString().trim();


                if (tenloaisach.equals("")) {
                    edtTenTheLoai.setError(getString(R.string.error_length));
                    return;
                }
                if (tenloaisach.length() > 50) {
                    edtTenTheLoai.setError(getString(R.string.error_length1));
                    return;
                }
                if (mota.equals("")) {
                    edtMoTaLoaiSach.setError(getString(R.string.error_length));
                    return;
                }
                if (vitri.equals("")) {
                    edtViTri.setError(getString(R.string.error_length));
                    return;
                }


                    TypeBook typebook = new TypeBook(maloaisach, tenloaisach, mota, vitri);
                    typeBookDAO.updateTypeBook(typebook);
                    anhXa();
                    addRecyclerview();
                    dialog.dismiss();

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
