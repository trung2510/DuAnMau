package com.vuabocphet.testduanmau.adapter;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vuabocphet.testduanmau.BillActivity;
import com.vuabocphet.testduanmau.database.SqliteOpenHelper;
import com.vuabocphet.testduanmau.holder.HolderBill;
import com.vuabocphet.testduanmau.model.Bill;
import com.vuabocphet.testduanmau.R;
import com.vuabocphet.testduanmau.model.Billdetails;
import com.vuabocphet.testduanmau.sqlDAO.BilldetailsDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.vuabocphet.testduanmau.Constant.B_COLUMN_ID;
import static com.vuabocphet.testduanmau.Constant.B_COLUMN_IDBILL;
import static com.vuabocphet.testduanmau.Constant.B_COLUMN_IDBOOK;
import static com.vuabocphet.testduanmau.Constant.B_COLUMN_POS;

public class BillAdapter extends RecyclerView.Adapter<HolderBill> {

    private final ArrayList<Bill> bills;
    private final BillActivity context;
    private SqliteOpenHelper helper;
    private ArrayList<Billdetails> billdetailsArrayList;
    private ArrayList<Billdetails> getBilldetailsArrayList1;
    private BilldetailsDAO billdetailsDAO;

    public BillAdapter(ArrayList<Bill> bills, BillActivity context) {
        this.bills = bills;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderBill onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cardview_bill, parent, false);

        return new HolderBill(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderBill holder, final int position) {
        helper = new SqliteOpenHelper(context);
        billdetailsArrayList = new ArrayList<>();
        getBilldetailsArrayList1 = new ArrayList<>();
        billdetailsDAO=new BilldetailsDAO(helper);
        final Bill bill = bills.get(position);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            holder.txtID.setText(bill.getMaHoaDon());
            //String date= new Date(simpleDateFormat.format(bill.getNgayMua())).toString();
            holder.txtDATE.setText(simpleDateFormat.format(bill.getNgayMua()) + "");
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.deleteBill(bill, position);
                    Cursor cursor = helper.getData("SELECT * FROM HoaDonChiTiet");
                    Billdetails billdetails;
                    if (cursor != null && cursor.moveToFirst()) {
                        do {
                            int maHDCT = cursor.getInt(cursor.getColumnIndex(B_COLUMN_ID));
                            String mahoadon = cursor.getString(cursor.getColumnIndex(B_COLUMN_IDBILL));
                            String masach = cursor.getString(cursor.getColumnIndex(B_COLUMN_IDBOOK));
                            int soluongmua = cursor.getInt(cursor.getColumnIndex(B_COLUMN_POS));
                            billdetails = new Billdetails();
                            billdetails.setId(maHDCT);
                            billdetails.setIdbill(mahoadon);
                            billdetails.setIdbook(masach);
                            billdetails.setSoluong(soluongmua);
                            billdetailsArrayList.add(billdetails);

                        } while (cursor.moveToNext());
                        for (int i = 0; i < billdetailsArrayList.size(); i++) {
                            if (billdetailsArrayList.get(i).getIdbill().equals(bill.getMaHoaDon())) {
                                billdetailsDAO.delete1(billdetailsArrayList.get(i).getIdbill());
                                Log.e("i",i+"");

                            }
                        }


                    }
                }
            });

            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.updateBill(bill);
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(bill.getMaHoaDon(), position);

                }
            });
        }


        @Override
        public int getItemCount () {
            return bills.size();
        }

}
