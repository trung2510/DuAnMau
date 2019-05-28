package com.example.duanmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.duanmau.adapter.NguoiDungAdapter;
import com.example.duanmau.dao.NguoiDungDAO;
import com.example.duanmau.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class ListNguoiDungActivity extends AppCompatActivity {

    Intent intent;
    public static List<NguoiDung> dsNguoiDung = new ArrayList<NguoiDung>();
    NguoiDungAdapter nguoiDungAdapter = null;
    public ListView lvView;
    NguoiDungDAO nguoiDungDAO;
    private Button btnAddUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nguoi_dung);
        lvView = (ListView) findViewById(R.id.lvView);
        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        nguoiDungDAO = new NguoiDungDAO(ListNguoiDungActivity.this); //Quên đéo khởi tạo cái ml này nên nó nullpointExeption
        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();
        setNguoiDungAdapter();
    }

    public void startThemNguoiDung(View view) {
        intent = new Intent(ListNguoiDungActivity.this, NguoiDungActivity.class);
        startActivity(intent);
    }

    public void setNguoiDungAdapter(){
        if (nguoiDungAdapter == null) {
            nguoiDungAdapter = new NguoiDungAdapter(this,R.layout.item_nguoi_dung,dsNguoiDung);

        }
        lvView.setAdapter(nguoiDungAdapter);
    }
}
