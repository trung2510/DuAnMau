package com.vuabocphet.testduanmau.adapter;


import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.vuabocphet.testduanmau.R;
import com.vuabocphet.testduanmau.UserActivity;
import com.vuabocphet.testduanmau.model.User;

import com.vuabocphet.testduanmau.holder.HolderUser;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<HolderUser> {
    private final UserActivity context;
    private final ArrayList<User> arrayList;

    public UserAdapter(UserActivity context, ArrayList<User> arrayList) {
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

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull HolderUser holder, final int position) {
         final User user=arrayList.get(position);
        holder.imgView.setImageResource(R.drawable.emone);
        holder.txtUsername.setText(user.getUserName());
        holder.txtPhone.setText(user.getPhone());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (arrayList.size()<=1){
                   Toast.makeText(context,"Phải có ít nhất một người dùng",Toast.LENGTH_LONG).show();
               }else {
                   context.deleteUser(user.getUserName(),position);
               }

            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.updateUser(user.getUserName(),user.getPhone(),user.getHoTen());
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
