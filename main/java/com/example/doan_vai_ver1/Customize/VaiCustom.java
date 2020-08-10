package com.example.doan_vai_ver1.Customize;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doan_vai_ver1.Object.Vai;
import com.example.doan_vai_ver1.R;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class VaiCustom extends ArrayAdapter {

    int resource;
    Context context;
    ArrayList<Vai> data;

    public VaiCustom(Context context, int resource, ArrayList<Vai> data) {
        super(context, resource);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private static class ViewHolder{
        TextView stt;
        TextView ten;
        TextView ma;
        TextView xuatxu;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource, null);
            holder = new ViewHolder();
            holder.stt = convertView.findViewById(R.id.vai_stt);
            holder.ma = convertView.findViewById(R.id.vai_ms);
            holder.ten = convertView.findViewById(R.id.vai_ten);
            holder.xuatxu = convertView.findViewById(R.id.vai_xuatxu);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final Vai vai = data.get(position);
        holder.stt.setText(vai.getStt()+"");
        holder.ma.setText(vai.getVai_ms());
        holder.ten.setText(vai.getVai_ten());
        holder.xuatxu.setText(vai.getVai_xuatxu());
        return convertView;
    }
}
