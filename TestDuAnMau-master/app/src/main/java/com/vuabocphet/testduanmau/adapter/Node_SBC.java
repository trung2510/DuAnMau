package com.vuabocphet.testduanmau.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vuabocphet.testduanmau.R;
import com.vuabocphet.testduanmau.model.Booksale;

import com.vuabocphet.testduanmau.holder.HolderUser;

import java.util.ArrayList;

public class Node_SBC extends RecyclerView.Adapter<HolderUser> {

   private final Context context;
    private final ArrayList<Booksale> arrayList;

    public Node_SBC(Context context, ArrayList<Booksale> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public HolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.cardview_user,parent,false);
        return new HolderUser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderUser holder, int position) {
        Booksale nguoiDung=arrayList.get(position);
       holder.txtUsername.setText("Mã sách:"+nguoiDung.getMaSach());
       holder.txtPhone.setText("Số lượng"+nguoiDung.getSL()+"");
       holder.imgEdit.setVisibility(View.GONE);
       holder.imgDelete.setVisibility(View.GONE);
       holder.imgView.setImageResource(R.drawable.money_icon);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
