package com.example.duanmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.duanmau.adapter.TheLoaiAdapter;
import com.example.duanmau.dao.TheLoaiDAO;
import com.example.duanmau.model.TheLoai;

import java.util.List;

public class ListTheLoaiActivity extends AppCompatActivity {

    private Button bntThem;
    private RecyclerView recyclerView;
    TheLoaiDAO theLoaiDAO;
    List<TheLoai> listTheLoai;

    TheLoaiAdapter theLoaiAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_the_loai);

        bntThem = (Button) findViewById(R.id.bntThem);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        theLoaiDAO = new TheLoaiDAO(ListTheLoaiActivity.this);
        listTheLoai = theLoaiDAO.getAll();

        Log.e("sss",listTheLoai.size()+"");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        theLoaiAdapter = new TheLoaiAdapter(this,listTheLoai);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(theLoaiAdapter);

    }

    public void addTheLoai(View view) {
        startActivity(new Intent(ListTheLoaiActivity.this,TheLoaiActivity.class));
    }
}
