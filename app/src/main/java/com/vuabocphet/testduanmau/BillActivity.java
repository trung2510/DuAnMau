package com.vuabocphet.testduanmau;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.vuabocphet.testduanmau.adapter.BillAdapter;
import com.vuabocphet.testduanmau.database.SqliteOpenHelper;
import com.vuabocphet.testduanmau.model.Bill;
import com.vuabocphet.testduanmau.sqlDAO.BillDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BillActivity extends AppCompatActivity implements Constant{
    private RecyclerView recyclerviewHD;
    private LinearLayoutManager linearLayoutManager;
    private BillAdapter billAdapter;
    private ArrayList<Bill> billList;
    private FloatingActionButton floatingActionButton;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private EditText txtMaHoaDon;
    private EditText txtNgayMua;
    private Button btnChon;
    private  Button btnAdd;
    private BillDao billDao;
    private SqliteOpenHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AnhXa();
        addRecyclerview();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBill();
            }
        });
    }
    private void AnhXa(){
        recyclerviewHD =findViewById(R.id.recyclerviewHD);
        simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        helper=new SqliteOpenHelper(this);
        billDao=new BillDao(helper);
        floatingActionButton=findViewById(R.id.floatAdd);
        floatingActionButton.setImageResource(R.drawable.add);
        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        billList=new ArrayList<>();
        billAdapter=new BillAdapter(billList,this);
        billList.clear();

        Cursor cursor=helper.getData("SELECT * FROM HoaDon");

        if (cursor!=null && cursor.moveToFirst()){

            do {
                String mahoadon=cursor.getString(cursor.getColumnIndex(BILL_COLUMN_ID));
                long ngaymua=cursor.getLong(cursor.getColumnIndex(BILL_COLUMN_DATE));
                Bill bill=new Bill(mahoadon,ngaymua);
                billList.add(bill);
                }while (cursor.moveToNext());

        }

         billAdapter.notifyDataSetChanged();

    }

    private void addRecyclerview(){

        recyclerviewHD.setLayoutManager(linearLayoutManager);
        recyclerviewHD.setHasFixedSize(true);
        recyclerviewHD.setAdapter(billAdapter);
    }

    private void addBill(){

        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_bill);

        txtMaHoaDon = dialog.findViewById(R.id.txtMaHoaDon);
        txtNgayMua =  dialog.findViewById(R.id.txtNgayMua);
        btnChon =  dialog.findViewById(R.id.btnChon);
        btnAdd = dialog.findViewById(R.id.btnAdd);
        txtNgayMua.setEnabled(false);
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date();
                txtNgayMua.setEnabled(true);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String mahoadon=txtMaHoaDon.getText().toString().trim();
               String ngaymua=txtNgayMua.getText().toString().trim();
               if (mahoadon.equals("")){
                   txtMaHoaDon.setError(getString(R.string.error_PassWord));
                   return;
               }
               if (mahoadon.length()>7){
                   txtMaHoaDon.setError(getString(R.string.errorlengmaxseven));
                   return;
               }
               if (ngaymua.equals("")){
                   txtNgayMua.setError(getString(R.string.error_date));
                   return;
               }
                boolean test = false;
                for (int i = 0; i < billList.size(); i++) {
                    Bill bill = billList.get(i);

                    if (mahoadon.equals(bill.getMaHoaDon())) {
                        txtMaHoaDon.setError("Mã hóa đơn đã tồn tại");
                        test = true;
                        break;
                    }

                }

                if (!test) {
                    Bill bill=new Bill(mahoadon,calendar.getTimeInMillis());
                    billList.add(0,bill);
                    billDao.insertBill(bill);
                    addRecyclerview();
                    Intent intent=new Intent(BillActivity.this,BilldetailActivity.class);
                    intent.putExtra("mahoadon",mahoadon);
                    startActivity(intent);
                    dialog.dismiss();
                }



               }
        });

        dialog.show();


    }

    private void date(){
        calendar=Calendar.getInstance();
        int ngay=calendar.get(Calendar.DATE);
        int thang=calendar.get(Calendar.MONTH);
        int nam=calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                txtNgayMua.setText(simpleDateFormat.format(calendar.getTimeInMillis())+"");
            }
        },nam,thang,ngay);
        datePickerDialog.show();

    }

    public void deleteBill(Bill bill,int i){
        billList.remove(i);
        billDao.deleteBill(bill.getMaHoaDon());
        addRecyclerview();

    }

    public void updateBill(Bill bill){
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_bill);
        TextView txt;
        txtMaHoaDon = dialog.findViewById(R.id.txtMaHoaDon);
        txtNgayMua =  dialog.findViewById(R.id.txtNgayMua);
        txt =  dialog.findViewById(R.id.txt);
        btnChon =  dialog.findViewById(R.id.btnChon);
        btnAdd = dialog.findViewById(R.id.btnAdd);
        txtNgayMua.setEnabled(false);
        txt.setText("Sửa hóa đơn");
        txtMaHoaDon.setText(bill.getMaHoaDon());
        txtMaHoaDon.setEnabled(false);
        txtNgayMua.setText(simpleDateFormat.format(bill.getNgayMua()));
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date();
                txtNgayMua.setEnabled(true);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mahoadon=txtMaHoaDon.getText().toString().trim();
                String ngaymua=txtNgayMua.getText().toString().trim();if (mahoadon.length()>7){
                    txtMaHoaDon.setError(getString(R.string.errorlengmaxseven));
                    return;
                }
                if (ngaymua.equals("")){
                    txtNgayMua.setError(getString(R.string.error_date));
                    return;
                }


                Bill bill=new Bill(mahoadon,calendar.getTimeInMillis());
                billDao.updateBill(bill);
                addRecyclerview();
                dialog.dismiss();
            }
        });

        dialog.show();




    }

    public void startActivity(String mahoadon,int i){
        Intent intent=new Intent(BillActivity.this,BilldetailActivity.class);
        intent.putExtra("Tinh" ,mahoadon);
        intent.putExtra("vitri",i);
        startActivity(intent);


    }

}
