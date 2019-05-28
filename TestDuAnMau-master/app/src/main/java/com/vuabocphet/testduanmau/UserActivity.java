package com.vuabocphet.testduanmau;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vuabocphet.testduanmau.adapter.UserAdapter;
import com.vuabocphet.testduanmau.database.SqliteOpenHelper;
import com.vuabocphet.testduanmau.model.User;
import com.vuabocphet.testduanmau.sqlDAO.UserDAO;

import java.util.ArrayList;

import static com.vuabocphet.testduanmau.database.SqliteOpenHelper.COLUMN_HOTEN;
import static com.vuabocphet.testduanmau.database.SqliteOpenHelper.COLUMN_PASSWORD;
import static com.vuabocphet.testduanmau.database.SqliteOpenHelper.COLUMN_PHONE;
import static com.vuabocphet.testduanmau.database.SqliteOpenHelper.COLUMN_USER;

public class UserActivity extends AppCompatActivity {
    private RecyclerView recyclerviewNguoiDung;
    private LinearLayoutManager linearLayoutManager;
    private UserAdapter node_nguoiDung;
    private ArrayList<User> arrayList;
    private SqliteOpenHelper sqLiteOpenHelper;
    private FloatingActionButton floatingActionButton;
    private Cursor cursor;
    private EditText edtUserNameNew;
    private EditText edtPassWordNew;
    private EditText edtPhone;
    private EditText edtHoTen;
    private Button btnAdd;
    private Button btnCancel;
    private UserDAO userDAO;
    // --Commented out by Inspection (10/13/2018 10:05 AM):private TextView txtLogo;
    private EditText edtConfirmPassWordNew;
    private Button btnList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        anhXa();
        onclick();
        addRecyclerview();
        getData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void anhXa() {

        sqLiteOpenHelper = new SqliteOpenHelper(this);
        recyclerviewNguoiDung = findViewById(R.id.recyclerview_NguoiDung);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        floatingActionButton = findViewById(R.id.flbAdd);
        arrayList = new ArrayList<>();
        userDAO=new UserDAO(sqLiteOpenHelper);



    }


    private void getData() {
        cursor = sqLiteOpenHelper.getData("SELECT * FROM User");
        arrayList.clear();
        if (cursor != null && cursor.moveToFirst()) {
            do {

                String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USER));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
                String hoten = cursor.getString(cursor.getColumnIndex(COLUMN_HOTEN));
                User user = new User();
                user.setUserName(user_name);
                user.setPassword(password);
                user.setPhone(phone);
                user.setHoTen(hoten);
                arrayList.add(user);

            } while (cursor.moveToNext());

            node_nguoiDung.notifyDataSetChanged();
        }
    }

    private void addRecyclerview() {
        node_nguoiDung = new UserAdapter(this, arrayList);
        recyclerviewNguoiDung.setLayoutManager(linearLayoutManager);
        recyclerviewNguoiDung.setHasFixedSize(true);
        recyclerviewNguoiDung.setAdapter(node_nguoiDung);

    }

    private void onclick() {
        floatingActionButton.setImageResource(R.drawable.add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(UserActivity.this);
                dialog.setContentView(R.layout.dialog_add_user);
                edtUserNameNew = dialog.findViewById(R.id.edtUserNameNew);
                edtPassWordNew = dialog.findViewById(R.id.edtPassWordNew);
                edtPhone = dialog.findViewById(R.id.edtPhone);
                edtHoTen = dialog.findViewById(R.id.edtHoTen);
                btnAdd = dialog.findViewById(R.id.btnAdd);
                btnCancel = dialog.findViewById(R.id.btnCancel);
                edtConfirmPassWordNew = dialog.findViewById(R.id.edtConfirmPassWordNew);
                btnList = dialog.findViewById(R.id.btnList);

                btnList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });


                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String usernamenew = edtUserNameNew.getText().toString().trim();
                        String passwordnew = edtPassWordNew.getText().toString().trim();
                        String confirmpasswordnew = edtConfirmPassWordNew.getText().toString().trim();
                        String phonenew = edtPhone.getText().toString().trim();
                        String namenew = edtHoTen.getText().toString().trim();
                        Log.e("Tình",confirmpasswordnew);
                        Log.e("Tình",passwordnew);
                        if (usernamenew.equals("")) {
                            edtUserNameNew.setError(getString(R.string.error_PassWord));

                            return;
                        }
                        String[] b = {"!", "~", "@", "#", "$", "%", "^", "&", "*", "*", "(", ")", "_", "-", "=", "+", "[", "]", ";", ":", "\\", "|", "?", "/", "<", ">", ".", ",", "'"};
                        //Toast.makeText(this, ""+b.length, Toast.LENGTH_SHORT).show();
                        for (String aB1 : b) {
                            if (usernamenew.indexOf(aB1) > -1) {
                                edtUserNameNew.setError(getString(R.string.error_Ki_Tu_Dac_Bite));
                                return;
                            }

                        }

                        boolean test = false;
                        for (int i = 0; i < arrayList.size(); i++) {
                            User user = arrayList.get(i);

                            if (usernamenew.equals(user.getUserName())) {
                                edtUserNameNew.setError("Tên người dùng đã tồn tại");
                                test = true;
                                break;
                            }

                        }

                        if (passwordnew.equals("")) {
                            edtPassWordNew.setError(getString(R.string.error_PassWord));
                            return;
                        }

                        for (String aB : b) {
                            if (passwordnew.indexOf(aB) > -1) {

                                edtPassWordNew.setError(getString(R.string.error_Ki_Tu_Dac_Bite));
                                return;
                            }

                        }

                        if (passwordnew.length() < 6) {

                            edtPassWordNew.setError(getString(R.string.error_PassWord_It_Hon_6Ki_Tu));
                            return;
                        }

                        if (confirmpasswordnew.equals("")){
                            edtConfirmPassWordNew.setError(getString(R.string.error_PassWord));
                            return;
                        }
                        if (!confirmpasswordnew.equals(passwordnew)){
                            edtConfirmPassWordNew.setError(getString(R.string.error_ConfirmPassWord));
                            return;
                        }

                        if (phonenew.equals("")) {
                            edtPhone.setError(getString(R.string.error_PassWord));
                            return;
                        }

                        if (namenew.equals("")) {
                            edtHoTen.setError(getString(R.string.error_PassWord));
                            return;
                        }

                        if (!test) {
                            User user = new User(usernamenew, passwordnew, phonenew, namenew);
                            userDAO.insertUser(user);
                            getData();
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
        });
    }

    public void deleteUser(final String username, final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có muốn xóa người dùng " + username + " không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userDAO.deleteUsername(username);
                arrayList.remove(i);
                getData();
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

    @SuppressLint("ResourceAsColor")
    public void updateUser(final String username, final String phone, String name) {

        final Dialog dialog = new Dialog(UserActivity.this);
        dialog.setContentView(R.layout.activity_change_password_);

         final EditText edtChangeUser;
         final EditText edtChangePass;
         final EditText edtChangeConfirmPass;
         final EditText edtChangePhone;
         final EditText edtChangeName;
         Button btnChange;
         Button btnClose;

        edtChangeUser =  dialog.findViewById(R.id.edtChangeUser);
        edtChangePass = dialog.findViewById(R.id.edtChangePass);
        edtChangeConfirmPass =  dialog.findViewById(R.id.edtChangeConfirmPass);
        edtChangePhone =  dialog.findViewById(R.id.edtChangePhone);
        edtChangeName =  dialog.findViewById(R.id.edtChangeName);
        btnChange =  dialog.findViewById(R.id.btnChange);
        btnClose = dialog.findViewById(R.id.btnClose);

         edtChangeUser.setEnabled(false);
         edtChangeUser.setText(username);
         edtChangeUser.setTextColor(R.color.gray);

         edtChangePhone.setText(phone);
         edtChangeName.setText(name);


        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernamenew = edtChangeUser.getText().toString().trim();
                String passwordnew = edtChangePass.getText().toString().trim();
                String confirmpasswordnew = edtChangeConfirmPass.getText().toString().trim();
                String phonenew = edtChangePhone.getText().toString().trim();
                String namenew = edtChangeName.getText().toString().trim();

                if (passwordnew.equals("")) {
                    edtChangePass.setError(getString(R.string.error_PassWord));
                    return;
                }
                String[] b = {"!", "~", "@", "#", "$", "%", "^", "&", "*", "*", "(", ")", "_", "-", "=", "+", "[", "]", ";", ":", "\\", "|", "?", "/", "<", ">", ".", ",", "'"};


                for (String aB : b) {
                    if (passwordnew.indexOf(aB) > -1) {

                        edtChangePass.setError(getString(R.string.error_Ki_Tu_Dac_Bite));
                        return;
                    }
                }

                    if (passwordnew.length() < 6) {

                        edtChangePass.setError(getString(R.string.error_PassWord_It_Hon_6Ki_Tu));
                        return;
                    }

                    if (confirmpasswordnew.equals("")){
                        edtChangeConfirmPass.setError(getString(R.string.error_PassWord));
                        return;
                    }
                    if (!confirmpasswordnew.equals(passwordnew)){
                        edtChangeConfirmPass.setError(getString(R.string.error_ConfirmPassWord));
                        return;
                    }

                  if (phonenew.equals("")) {
                    edtChangePhone.setError(getString(R.string.error_PassWord));
                    return;
                }

                if (namenew.equals("")) {
                     edtChangeName.setError(getString(R.string.error_PassWord));
                    return;
                }



                User user = new User();

                user.setUserName(usernamenew);

                user.setPassword(passwordnew);

                user.setPhone(phonenew);

                user.setHoTen(namenew);

                userDAO.updateUsername(user, username);

                getData();

                addRecyclerview();

                dialog.dismiss();


            }
        });


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });




        dialog.show();
    }


}
