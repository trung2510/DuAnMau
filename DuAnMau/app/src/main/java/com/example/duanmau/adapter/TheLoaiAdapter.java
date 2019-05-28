package com.example.duanmau.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmau.R;
import com.example.duanmau.model.TheLoai;

import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ItemHolder> {

    Context context;
    List<TheLoai> arrTheLoai;

    public TheLoaiAdapter(Context context, List<TheLoai> arrTheLoai) {
        this.context = context;
        this.arrTheLoai = arrTheLoai;
    }

    @NonNull
    @Override
    public TheLoaiAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_the_loai,viewGroup,false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiAdapter.ItemHolder itemHolder, int i) {
        TheLoai theLoai = arrTheLoai.get(i);
        itemHolder.tvMaTheLoai.setText(theLoai.getMaTheLoai());
        itemHolder.tvTenTheLoai.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        if (arrTheLoai == null) return 0;
        return arrTheLoai.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        public ImageView ivIcon;
        public TextView tvMaTheLoai;
        public TextView tvTenTheLoai;
        public ImageView ivDelete;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            tvMaTheLoai = (TextView) itemView.findViewById(R.id.tvMaTheLoai);
            tvTenTheLoai = (TextView) itemView.findViewById(R.id.tvTenTheLoai);
            ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);

        }
    }
}
