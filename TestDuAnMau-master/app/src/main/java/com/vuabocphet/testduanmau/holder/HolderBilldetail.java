package com.vuabocphet.testduanmau.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vuabocphet.testduanmau.R;


public class HolderBilldetail extends RecyclerView.ViewHolder {
    public final TextView txtMaSach;
    public final TextView txtSoLuong;
    public final TextView txtGiaBia;
    public final TextView txtYhanhTien;
    public final ImageView imageView;

      public HolderBilldetail(View itemView) {
        super(itemView);
        txtMaSach = itemView.findViewById(R.id.txtMaSach);
        txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
        txtGiaBia = itemView.findViewById(R.id.txtGiaBia);
        txtYhanhTien = itemView.findViewById(R.id.txtYhanhTien);
        imageView=itemView.findViewById(R.id.imgDelete);
    }
}
