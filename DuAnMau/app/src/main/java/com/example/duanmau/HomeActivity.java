package com.example.duanmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout layoutNguoiDung;
    private LinearLayout layoutSach;
    private LinearLayout layoutSachBanChay;
    private LinearLayout layoutLoaiSach;
    private LinearLayout layoutHoaDon;
    private LinearLayout layoutThongKe;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        anhxa();
        onclick();
    }

    private void onclick() {
        layoutNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent = new Intent(HomeActivity.this, ListNguoiDungActivity.class));

            }
        });
        layoutSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent = new Intent(HomeActivity.this, SachActivity.class));
            }
        });
        layoutSachBanChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent = new Intent(HomeActivity.this, SachBanChayActivity.class));
            }
        });
        layoutLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent = new Intent(HomeActivity.this, ListTheLoaiActivity.class));
            }
        });
        layoutHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent = new Intent(HomeActivity.this, HoaDonActivity.class));
            }
        });
        layoutThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent = new Intent(HomeActivity.this, ThongKeActivity.class));
            }
        });

    }

    private void anhxa() {
        layoutNguoiDung = (LinearLayout) findViewById(R.id.layout_NguoiDung);
        layoutSach = (LinearLayout) findViewById(R.id.layout_Sach);
        layoutSachBanChay = (LinearLayout) findViewById(R.id.layout_SachBanChay);
        layoutLoaiSach = (LinearLayout) findViewById(R.id.layout_LoaiSach);
        layoutHoaDon = (LinearLayout) findViewById(R.id.layout_HoaDon);
        layoutThongKe = (LinearLayout) findViewById(R.id.layoutThongKe);
    }


}
