package com.example.duanmau;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUserName;
    private EditText edtPassWord;
    private CheckBox ckbRememberPassword;
    private Button btnDangNhap;
    private TextView txtForgotpassword;
    private TextView txtContact;
    private FloatingActionButton flbForMatText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        onClick();
    }

    private void onClick() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            }
        });
    }

    private void AnhXa() {
        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtPassWord = (EditText) findViewById(R.id.edtPassWord);
        ckbRememberPassword = (CheckBox) findViewById(R.id.ckbRememberPassword);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        txtForgotpassword = (TextView) findViewById(R.id.txtForgotpassword);
        txtContact = (TextView) findViewById(R.id.txtContact);
        flbForMatText = (FloatingActionButton) findViewById(R.id.flbForMatText);

    }


}
