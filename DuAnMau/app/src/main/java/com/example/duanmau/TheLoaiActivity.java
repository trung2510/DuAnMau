package com.example.duanmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.dao.TheLoaiDAO;
import com.example.duanmau.model.TheLoai;

public class TheLoaiActivity extends AppCompatActivity {

    private EditText edtIdTypeBook;
    private EditText edtNameTypeBook;
    private EditText edtViTri;
    private EditText edtMoTa;
    private Button btnSave;
    private Button btnCancel;
    private Button btnShow;
    TheLoaiDAO theLoaiDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        AnhXa();
        AddTheLoai();
        showList();
    }

    private void showList() {
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TheLoaiActivity.this,ListTheLoaiActivity.class));
            }
        });
    }

    private void AddTheLoai() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theLoaiDAO = new TheLoaiDAO(TheLoaiActivity.this);

                String id = edtIdTypeBook.getText().toString();
                String name = edtNameTypeBook.getText().toString();
                String mt = edtMoTa.getText().toString();
                String vt = edtViTri.getText().toString();

                if (id.equals("")){
                    edtIdTypeBook.setError("Nhập id");
                }
                if (name.equals("")){
                    edtNameTypeBook.setError("Nhập thể loại sách");
                }
                if (vt.equals("")){
                    edtViTri.setError("Nhập vị trí");
                }

                TheLoai theLoai = new TheLoai(id,name,mt,vt);

                if (theLoaiDAO.insertTheLoai(theLoai) > 0) {
                    Toast.makeText(TheLoaiActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(TheLoaiActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AnhXa() {

        edtIdTypeBook = (EditText) findViewById(R.id.edtIdTypeBook);
        edtNameTypeBook = (EditText) findViewById(R.id.edtNameTypeBook);
        edtViTri = (EditText) findViewById(R.id.edtViTri);
        edtMoTa = (EditText) findViewById(R.id.edtMoTa);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnShow = (Button) findViewById(R.id.btnShow);
    }


}
