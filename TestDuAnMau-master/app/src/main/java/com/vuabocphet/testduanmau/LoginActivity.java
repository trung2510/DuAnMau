package com.vuabocphet.testduanmau;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.vuabocphet.testduanmau.database.SqliteOpenHelper;
import com.vuabocphet.testduanmau.model.User;
import com.vuabocphet.testduanmau.sqlDAO.BookDao;
import com.vuabocphet.testduanmau.sqlDAO.TypeBookDAO;
import com.vuabocphet.testduanmau.sqlDAO.UserDAO;

public class LoginActivity extends AppCompatActivity{


    private EditText edtUserName,edtPassWord;
    private CheckBox ckbRememberPassword;
    private FloatingActionButton flbForMatText;
    private TextView txtForgotPassword,txtContact;
    private EditText email;
    private TextView txtMatKhau;

    private SqliteOpenHelper helper;

    private UserDAO userDAO;
    private BookDao bookDao;
    private TypeBookDAO typeBookDAO;

    private SharedPreferences sharedPreferences;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper=new SqliteOpenHelper(this);
        userDAO=new UserDAO(helper);
        bookDao=new BookDao(helper);
        typeBookDAO=new TypeBookDAO(helper);
        //them user
         User nguoiDung=new User("AnhYeu"
                 ,"1234567"
                 ,"123456"
                 ,"Nhieu");
         userDAO.insertUser(nguoiDung);
         AnhXa();
         sharedPreferences=getSharedPreferences("SaveUser",MODE_PRIVATE);
        edtUserName.setText(sharedPreferences.getString("TenNguoiDung",""));
        edtPassWord.setText(sharedPreferences.getString("MatKhau",""));
        ckbRememberPassword.setChecked(sharedPreferences.getBoolean("check",false));
        Click();


        }
    private void setCheckBox(){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if (ckbRememberPassword.isChecked()){
            editor.putString("TenNguoiDung",edtUserName.getText().toString().trim());
           editor.putString("MatKhau",edtPassWord.getText().toString().trim());
           editor.putBoolean("check",true);
           editor.apply();

        }else {
            editor.putString("TenNguoiDung","");
            editor.putString("MatKhau","");
            editor.putBoolean("check",false);
            editor.commit();
        }
    }






    private void Click(){
        edtUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtUserName.setTextSize(15f);
            }
        });

        edtPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtPassWord.setTextSize(15f);
            }
        });

        flbForMatText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edtUserName.getText().toString().trim();
                String pass=edtPassWord.getText().toString().trim();
                if (name.equals("")&& pass.equals("")){
                    edtUserName.setError(getString(R.string.error_Delete));
                    edtPassWord.setError(getString(R.string.error_Delete));
                }
                else {
                    edtUserName.setText("");
                    edtUserName.setHint("Nguyễn Văn Tình");
                    edtUserName.setTextSize(10f);
                    edtPassWord.setText("");
                    edtPassWord.setHint("***********");
                    edtPassWord.setTextSize(10f);

                }
            }
        });

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(LoginActivity.this);
               dialog.setContentView(R.layout.dialog_forgot_password);

                Button gui=dialog.findViewById(R.id.gui);
                Button huy=dialog.findViewById(R.id.huy);
                email=dialog.findViewById(R.id.maila);
                txtMatKhau=dialog.findViewById(R.id.txtMatKhau);

                gui.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String a="(\\w)+\\@((\\w)+\\.)+(\\w{2,4})";
                        String b=email.getText().toString();

                        if (b.matches(a)){
                           txtMatKhau.setText("Mật khẩu của bạn:NguyenTinh");
                        }else {
                            email.setError("Email không hợp lệ");
                        }
                    }
                });

                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });




               dialog.show();
            }
        });

        txtContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.dialog_gioithieu);
                dialog.show();
            }
        });
    }

    private void AnhXa(){
        edtUserName=findViewById(R.id.edtUserNam);
        edtPassWord=findViewById(R.id.edtPassWord);

        ckbRememberPassword =  findViewById(R.id.ckbRememberPassword);
        flbForMatText =  findViewById(R.id.flbForMatText);
        flbForMatText.setImageResource(R.drawable.ic_delete);

        txtForgotPassword=findViewById(R.id.txtForgotpassword);
        txtContact=findViewById(R.id.txtContact);




    }

    private void KiemTra(){
        String name=edtUserName.getText().toString().trim();
        String pass=edtPassWord.getText().toString().trim();

        if (name.equals("")){
            edtUserName.setError(getString(R.string.error_UserName));
            return;
        }

        String[] b={"!","~","@","#","$","%","^","&","*","*","(",")","_","-","=","+","[","]",";",":","\\","|","?","/","<",">",".",",","'"};
        //Toast.makeText(this, ""+b.length, Toast.LENGTH_SHORT).show();
        for (String aB1 : b) {
            if (name.indexOf(aB1) > -1) {
                edtUserName.setError(getString(R.string.error_Ki_Tu_Dac_Bite));
                return;
            }

        }

        if (pass.equals("")){
            edtPassWord.setError(getString(R.string.error_PassWord));
            return;
        }

        for (String aB : b) {
            if (pass.indexOf(aB) > -1) {

                edtPassWord.setError(getString(R.string.error_Ki_Tu_Dac_Bite));
                return;
            }

        }

        if (pass.length()<6){

            edtPassWord.setError(getString(R.string.error_PassWord_It_Hon_6Ki_Tu));
            return;
        }
        
        User nguoiDung=userDAO.getDataUser(name);
        
        if (nguoiDung==null){
            //Toast.makeText(this, getString(R.string.notify_wrong_username_or_password), Toast.LENGTH_SHORT).show();
            Toast.makeText(this,getString(R.string.notify_wrong_username_or_password),Toast.LENGTH_LONG).show();
        }else {
            String password=nguoiDung.getPassword();
           if (password.equals(pass)){

               startActivity(new Intent(this,HomeActivity.class));
               finish();
           }else {
               Toast.makeText(this,getString(R.string.error),Toast.LENGTH_LONG).show();
           }

            }

      

        }



    public void DangNhap(View view) {

       KiemTra();
        setCheckBox();
    }
}

