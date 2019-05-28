package com.vuabocphet.testduanmau.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vuabocphet.testduanmau.R;


public class HolderBill extends RecyclerView.ViewHolder {
   private final ImageView imgView;
    public final TextView txtID;
    public final TextView txtDATE;
    public final ImageView imgEdit;
    public final ImageView imgDelete;



    public HolderBill(View itemView) {
        super(itemView);

        imgView = itemView.findViewById(R.id.imgView);
        txtID = itemView.findViewById(R.id.txtID);
        txtDATE = itemView.findViewById(R.id.txtDATE);
        imgEdit =  itemView.findViewById(R.id.imgEdit);
        imgDelete =  itemView.findViewById(R.id.imgDelete);
    }
}
