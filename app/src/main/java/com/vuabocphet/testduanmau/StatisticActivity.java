package com.vuabocphet.testduanmau;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.vuabocphet.testduanmau.database.SqliteOpenHelper;
import com.vuabocphet.testduanmau.model.Statistic;

import java.util.ArrayList;

import static com.vuabocphet.testduanmau.Constant.TABLE_BILL;
import static com.vuabocphet.testduanmau.Constant.TABLE_HOADONCHITIET;
import static com.vuabocphet.testduanmau.Constant.TABLE_SACH;

public class StatisticActivity extends AppCompatActivity {
    private ArrayList<Statistic> statistics;
    private SqliteOpenHelper helper;
    private double sumDay, sumMonth, sumYear;

    private TextView sDay;
    private TextView sMonth;
    private TextView sYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sDay = findViewById(R.id.sumDay);
        sMonth = findViewById(R.id.sumMonth);
        sYear = findViewById(R.id.sumYear);
        anhXa();
        thongke();


    }

    private void anhXa() {
        statistics = new ArrayList<>();
        helper = new SqliteOpenHelper(this);

        String testSUMDAY = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.SoLuong) as 'tongtien' " +
                "" + "from " + TABLE_BILL +
                "" + " INNER JOIN " + TABLE_HOADONCHITIET + " on " + " HoaDon.MaHoaDon = HoaDonChiTiet.MaHoaDon  " +
                "" + " INNER JOIN " + TABLE_SACH + " on " + " Sach.MaSach = HoaDonChiTiet.MaSach  " +
                "" + " WHERE strftime(\"%Y-%m-%d\", HoaDon.NgayMua / 1000, 'unixepoch') = strftime(\"%Y-%m-%d\",'now') " +
                "" + " GROUP BY HoaDonChiTiet.MaSach " +
                ")";
        Cursor cursor = helper.getData(testSUMDAY);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                sumDay = cursor.getDouble(0);
                Log.e("sumDay", sumDay + " " + cursor.getCount());
            }
        }

        String testSUMMONTH = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.SoLuong) as 'tongtien' " +
                "" + "from " + TABLE_BILL +
                "" + " INNER JOIN " + TABLE_HOADONCHITIET + " on " + " HoaDon.MaHoaDon = HoaDonChiTiet.MaHoaDon  " +
                "" + " INNER JOIN " + TABLE_SACH + " on " + " Sach.MaSach = HoaDonChiTiet.MaSach  " +
                "" + " WHERE strftime(\"%Y-%m\", HoaDon.NgayMua / 1000, 'unixepoch') = strftime(\"%Y-%m\") " +
                "" + " GROUP BY HoaDonChiTiet.MaSach " +
                ")";
        Cursor cursor1 = helper.getData(testSUMMONTH);
        if (cursor1 != null) {
            if (cursor1.getCount() > 0) {
                cursor1.moveToFirst();
                sumMonth = cursor1.getDouble(0);
                Log.e("sumMonth", sumMonth + " " + cursor1.getCount());
            }
        }

        String testSUMYEAR = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.SoLuong) as 'tongtien' " +
                "" + "from " + TABLE_BILL +
                "" + " INNER JOIN " + TABLE_HOADONCHITIET + " on " + " HoaDon.MaHoaDon = HoaDonChiTiet.MaHoaDon  " +
                "" + " INNER JOIN " + TABLE_SACH + " on " + " Sach.MaSach = HoaDonChiTiet.MaSach  " +
                "" + " WHERE strftime(\"%Y\", HoaDon.NgayMua / 1000, 'unixepoch') = strftime(\"%Y\") " +
                "" + " GROUP BY HoaDonChiTiet.MaSach " +
                ")";
        Cursor cursor2 = helper.getData(testSUMYEAR);
        if (cursor2 != null) {
            if (cursor2.getCount() > 0) {
                cursor2.moveToFirst();
                sumYear = cursor2.getDouble(0);
                Log.e("sumYear", sumYear + " " + cursor2.getCount());
            }
        }
        statistics.clear();
        statistics.add(new Statistic(sumDay, sumMonth, sumYear));
    }

    private void thongke() {
        sDay.setText("Hôm nay:" + statistics.get(0).getStaDay() + "\tVND");
        sMonth.setText("Tháng này:" + statistics.get(0).getStaMonth() + "\tVND");
        sYear.setText("Năm nay:" + statistics.get(0).getStaYear() + "\tVND");
    }

}
