package com.example.duanmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.dao.NguoiDungDAO;
import com.example.duanmau.model.NguoiDung;

public class NguoiDungActivity extends AppCompatActivity {

    private EditText edtUserName,edtPassWord,edtRePassWord,edtPhone,edtName;
    private Button btnSave,btnCancel,btnListUser;

    NguoiDungDAO nguoiDungDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        AnhXa();

    }

    private void AnhXa() {

        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtPassWord = (EditText) findViewById(R.id.edtPassWord);
        edtRePassWord = (EditText) findViewById(R.id.edtRePassWord);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtName = (EditText) findViewById(R.id.edtName);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnListUser = (Button) findViewById(R.id.btnListUser);

    }

    public void addUser(View view){
        nguoiDungDAO = new NguoiDungDAO(NguoiDungActivity.this);
        String userName = edtUserName.getText().toString();
        String password = edtPassWord.getText().toString();
        String Repassword = edtRePassWord.getText().toString();
        String phone = edtPhone.getText().toString();
        String name = edtName.getText().toString();

        if (userName.equals("")){
            edtUserName.setError("Tên không được để trống");
        }
        else if (password.equals("")){
            edtPassWord.setError("Password không được để trống");
        }
        else if (!Repassword.equals(password)){
            edtRePassWord.setError("Phải trùng với password");
        }
        else if (phone.equals("")){
            edtPhone.setError("SĐT không được để trống");
        }
        else if (name.equals("")){
            edtName.setError("Tên không được để trống");
        }


        NguoiDung nguoiDung = new NguoiDung(userName,password,phone,name);
        if(nguoiDungDAO.insertNguoiDung(nguoiDung)==1){
            Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Them that bai", Toast.LENGTH_SHORT).show();
        }

    }

    public void openListTheLoai(View view){
        startActivity(new Intent(NguoiDungActivity.this,ListTheLoaiActivity.class));
    }

}

