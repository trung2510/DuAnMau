package com.vuabocphet.testduanmau.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vuabocphet.testduanmau.R;


public class HolderBook extends RecyclerView.ViewHolder {
  private final ImageView imgView;
    public final TextView txtNameBook;
    public final TextView txtPOS;
    public final ImageView imgEdit;
    public final ImageView imgDelete;



    public HolderBook(View itemView) {
        super(itemView);
        imgView = itemView.findViewById(R.id.imgView);
        txtNameBook = itemView.findViewById(R.id.txtNameBook);
        txtPOS = itemView.findViewById(R.id.txtPOS);
        imgEdit = itemView.findViewById(R.id.imgEdit);
        imgDelete = itemView.findViewById(R.id.imgDelete);
    }
}
