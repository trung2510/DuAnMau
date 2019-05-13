package com.vuabocphet.testduanmau;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.vuabocphet.testduanmau.adapter.BilldetailAdapter;
import com.vuabocphet.testduanmau.database.SqliteOpenHelper;
import com.vuabocphet.testduanmau.model.Billdetails;
import com.vuabocphet.testduanmau.model.Book;
import com.vuabocphet.testduanmau.sqlDAO.BilldetailsDAO;

import java.util.ArrayList;

import static com.vuabocphet.testduanmau.Constant.B_COLUMN_ID;
import static com.vuabocphet.testduanmau.Constant.B_COLUMN_IDBILL;
import static com.vuabocphet.testduanmau.Constant.B_COLUMN_IDBOOK;
import static com.vuabocphet.testduanmau.Constant.B_COLUMN_POS;
import static com.vuabocphet.testduanmau.Constant.COLUMN_GIABIA;
import static com.vuabocphet.testduanmau.Constant.COLUMN_MASACH;
import static com.vuabocphet.testduanmau.Constant.COLUMN_SOLUONG;

public class BilldetailActivity extends AppCompatActivity {
    private EditText edtIDBill;
    private Spinner spMaSach;
    private EditText txtSoLuong;
    private Button btnAdd;
    // --Commented out by Inspection (10/13/2018 10:05 AM):private Button btnThanhToan;
    private TextView txtThanhTien;
    private RecyclerView recyclerviewBilldetail;
    private LinearLayoutManager linearLayoutManager;
    private SqliteOpenHelper helper;
    private BilldetailAdapter billdetailAdapter;
    private ArrayList<Billdetails> billdetailsArrayList,getBilldetailsArrayList1;
    private ArrayList<Book> bookArrayList;
    private BilldetailsDAO nBilldetailsDAO;
    private int i;
    private int vitri;
    private String mahoadon2;
    private  String mhoadon1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billdetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        anhXa();
        getdata();


        //getIDBook();
          getIDBook();
        //getDataBilldetails();
        //Toast.makeText(this, ""+i, Toast.LENGTH_SHORT).show();
        addRecyclerview();


    }

      private void anhXa(){
        helper=new SqliteOpenHelper(this);
        nBilldetailsDAO=new BilldetailsDAO(helper);
        edtIDBill = findViewById(R.id.edtIDBill);
        spMaSach = findViewById(R.id.spMaSach);
        txtSoLuong = findViewById(R.id.txtSoLuong);
        btnAdd = findViewById(R.id.btnAdd);

        txtThanhTien = findViewById(R.id.txtThanhTien);
        recyclerviewBilldetail = findViewById(R.id.recyclerviewBilldetail);
          billdetailsArrayList=new ArrayList<>();
        bookArrayList=new ArrayList<>();
        getBilldetailsArrayList1=new ArrayList<>();

        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
    }


    private void getDataBilldetails1(){
        billdetailAdapter=new BilldetailAdapter(getBilldetailsArrayList1,bookArrayList,this,i);
        Cursor cursor=helper.getData("SELECT * FROM HoaDonChiTiet");
        billdetailsArrayList.clear();
        Billdetails billdetails;
        if (cursor!=null&& cursor.moveToFirst()){
            do {
                int maHDCT=cursor.getInt(cursor.getColumnIndex(B_COLUMN_ID));
                String mahoadon=cursor.getString(cursor.getColumnIndex(B_COLUMN_IDBILL));
                String masach=cursor.getString(cursor.getColumnIndex(B_COLUMN_IDBOOK));
                int soluongmua=cursor.getInt(cursor.getColumnIndex(B_COLUMN_POS));
                billdetails=new Billdetails();
                billdetails.setId(maHDCT);
                billdetails.setIdbill(mahoadon);
                billdetails.setIdbook(masach);
                billdetails.setSoluong(soluongmua);
                billdetailsArrayList.add(billdetails);

            }while (cursor.moveToNext());
            for (int i=0;i<billdetailsArrayList.size();i++){
                if (billdetailsArrayList.get(i).getIdbill().equals(mhoadon1)){
                    Billdetails billd=billdetailsArrayList.get(i);
                    Billdetails billdetails1=new Billdetails();
                    Log.e("Tinh",""+i);
                    billdetails1.setId(billd.getId());
                    billdetails1.setIdbill(billd.getIdbill());
                    billdetails1.setIdbook(billd.getIdbook());
                    billdetails1.setSoluong(billd.getSoluong());
                    getBilldetailsArrayList1.add(billdetails1);
                }
            }

            txtThanhTien.setVisibility(View.VISIBLE);
            billdetailAdapter.notifyDataSetChanged();

        }
    }



    private void getdata(){
        Intent intent=getIntent();
         mahoadon2=intent.getStringExtra("mahoadon");
         mhoadon1=intent.getStringExtra("Tinh");

        if (mahoadon2==null){
            vitri=intent.getIntExtra("vitri",0);
            edtIDBill.setEnabled(false);
            edtIDBill.setText(mhoadon1);
            getDataBilldetails1();
        }else {
            edtIDBill.setEnabled(false);
            edtIDBill.setText(mahoadon2);
            getDataBilldetails1();
        }


    }

    private void getIDBook(){
        final ArrayList<String> strings=new ArrayList<>();
        final ArrayList<Float> floats=new ArrayList<>();
        final ArrayList<Integer> ints=new ArrayList<>();
        Cursor cursor=helper.getData("SELECT * FROM Sach");
        if (cursor!=null && cursor.moveToNext()){
            do {
                String masach=cursor.getString(cursor.getColumnIndex(COLUMN_MASACH));
                float giabia=cursor.getFloat(cursor.getColumnIndex(COLUMN_GIABIA));
                int soluong=cursor.getInt(cursor.getColumnIndex(COLUMN_SOLUONG));
                Book book=new Book();
                book.setMaSach(masach);
                book.setGiaBia(giabia);
                bookArrayList.add(book);
                strings.add(masach);
                floats.add(giabia);
                ints.add(soluong);
               }while (cursor.moveToNext());
            }
        final ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMaSach.setAdapter(adapter);

          spMaSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (txtSoLuong.getText().toString().trim().equals("")){
                            txtSoLuong.setError(getString(R.string.error_PassWord));
                            return;
                        }
                        if (ints.get(position)<Integer.parseInt(txtSoLuong.getText().toString().trim())){
                            txtSoLuong.setError(getString(R.string.error_po));
                            return;
                        }

                        Billdetails billdetails1=new Billdetails();
                        billdetails1.setIdbook(strings.get(position));
                        billdetails1.setIdbill(edtIDBill.getText().toString());
                        billdetails1.setSoluong(Integer.parseInt(txtSoLuong.getText().toString().trim()));
                        nBilldetailsDAO.insert(billdetails1);
                        txtThanhTien.setVisibility(View.VISIBLE);
                        getBilldetailsArrayList1.add(0,billdetails1);
                        addRecyclerview();
                        }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void addRecyclerview(){
        recyclerviewBilldetail.setLayoutManager(linearLayoutManager);
        recyclerviewBilldetail.setHasFixedSize(true);
        recyclerviewBilldetail.setAdapter(billdetailAdapter);
    }

    public void delete(int i,int po){
        nBilldetailsDAO.delete(String.valueOf(i));
        getBilldetailsArrayList1.remove(po);
        addRecyclerview();

    }

}
