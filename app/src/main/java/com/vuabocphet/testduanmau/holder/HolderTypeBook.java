package com.vuabocphet.testduanmau.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.vuabocphet.testduanmau.R;


public class HolderTypeBook extends RecyclerView.ViewHolder {
    private final ImageView imgView;
    public final TextView txtMaLoai;
    public final TextView txttypeName;
    public final ImageView imgEdit;
    public final ImageView imgDelete;
    public HolderTypeBook(View itemView) {
        super(itemView);

        imgView =  itemView.findViewById(R.id.imgView);
        txtMaLoai = itemView.findViewById(R.id.txtMaLoai);
        txttypeName = itemView.findViewById(R.id.txttypeName);
        imgEdit =  itemView.findViewById(R.id.imgEdit);
        imgDelete =  itemView.findViewById(R.id.imgDelete);

    }
}
