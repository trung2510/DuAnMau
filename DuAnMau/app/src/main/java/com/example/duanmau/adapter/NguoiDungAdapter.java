package com.example.duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmau.R;
import com.example.duanmau.model.NguoiDung;

import java.util.List;

public class NguoiDungAdapter extends ArrayAdapter {

    Context context;
    int resource;
    List<NguoiDung> nguoiDungList;

    public NguoiDungAdapter(Context context, int resource, List<NguoiDung> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.nguoiDungList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_nguoi_dung,parent,false);
            holder = new ViewHolder();
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            holder.ivDelete = (ImageView) convertView.findViewById(R.id.ivDelete);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        NguoiDung nguoiDung = nguoiDungList.get(position);
        holder.tvName.setText(nguoiDung.getUserName());
        holder.tvPhone.setText(nguoiDung.getPhone());

        return convertView;
    }

    public class ViewHolder{
        private ImageView ivIcon;
        private TextView tvName;
        private TextView tvPhone;
        private ImageView ivDelete;

    }
}
