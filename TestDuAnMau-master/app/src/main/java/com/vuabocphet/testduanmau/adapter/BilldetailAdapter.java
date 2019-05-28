package com.vuabocphet.testduanmau.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vuabocphet.testduanmau.BilldetailActivity;

import com.vuabocphet.testduanmau.R;
import com.vuabocphet.testduanmau.holder.HolderBilldetail;
import com.vuabocphet.testduanmau.model.Billdetails;
import com.vuabocphet.testduanmau.model.Book;

import java.util.ArrayList;

public class BilldetailAdapter extends RecyclerView.Adapter<HolderBilldetail> {

   private final ArrayList<Billdetails> billdetails;
   private final ArrayList<Book> bookArrayList;
   private final BilldetailActivity context;
private final int i;


    public BilldetailAdapter(ArrayList<Billdetails> billdetails, ArrayList<Book> bookArrayList, BilldetailActivity context, int i) {
        this.billdetails = billdetails;
        this.bookArrayList = bookArrayList;
        this.context = context;
        this.i = i;
    }

    @NonNull
    @Override
    public HolderBilldetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.cardview_billdetail,parent,false);
        return new HolderBilldetail(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderBilldetail holder, final int position) {
             final Billdetails bill=billdetails.get(position);
             int pos=-1;
        for (int i = 0; i < billdetails .size(); i++){
            if (!bookArrayList.isEmpty()){
                Book book=bookArrayList.get(i);
                if (book.getMaSach().equalsIgnoreCase(bill.getIdbook())){
                    pos = i;
                    break;
                }
            }
            else {
                holder.txtSoLuong.setText("Số lượng:?");
                holder.txtMaSach.setText("Mã sách:Không tồn tại");
                holder.txtGiaBia.setText("Giá bìa:?");
                holder.txtYhanhTien.setText("Thành tiền:?");
                return;
            }
        }

        Book book=bookArrayList.get(pos);



           holder.txtSoLuong.setText("Số lượng:" + bill.getSoluong());
            holder.txtMaSach.setText("Mã sách:" + bill.getIdbook());
            holder.txtGiaBia.setText("Giá bìa:" + book.getGiaBia() + "\tVND");
            holder.txtYhanhTien.setText("Thành tiền:" + bill.getSoluong() * book.getGiaBia() + "\tVND");


           holder.imageView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
              context.delete(bill.getId(),position);
               }
           });





    }

    @Override
    public int getItemCount() {
        return billdetails.size();
    }

}
