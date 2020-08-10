package com.example.doan_vai_ver1.Customize;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doan_vai_ver1.Object.Nhap;
import com.example.doan_vai_ver1.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PhieuNhapCustom extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Nhap> data;

    public PhieuNhapCustom(Context context, int resource, ArrayList<Nhap> data) {
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
        TextView ngay;
        TextView ma;
        TextView soluong;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource, null);
            holder = new ViewHolder();
            holder.stt = convertView.findViewById(R.id.txt_phieunhap_stt);
            holder.ngay = convertView.findViewById(R.id.txt_phieunhap_ngay);
            holder.ma = convertView.findViewById(R.id.txt_phieunhap_loaivai);
            holder.soluong = convertView.findViewById(R.id.txt_phieunhap_soluong);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final Nhap nhap = data.get(position);
        holder.stt.setText(nhap.getStt()+"");
        holder.ngay.setText(nhap.getNgay());
        holder.ma.setText(nhap.getMavai());
        holder.soluong.setText(nhap.getSoluong()+"");
        return convertView;
    }
}
