package com.vuabocphet.testduanmau.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vuabocphet.testduanmau.R;


public class HolderUser extends RecyclerView.ViewHolder {
    public final TextView txtUsername;
    public final TextView txtPhone;
    public final ImageView imgView;
    public final ImageView imgDelete;
    public final ImageView imgEdit;
    public HolderUser(View itemView) {
        super(itemView);
        txtUsername=itemView.findViewById(R.id.txtUsername);
        txtPhone=itemView.findViewById(R.id.txtPhone);
        imgView=itemView.findViewById(R.id.imgView);
        imgDelete=itemView.findViewById(R.id.imgDelete);
        imgEdit=itemView.findViewById(R.id.imgEdit);

    }
}
