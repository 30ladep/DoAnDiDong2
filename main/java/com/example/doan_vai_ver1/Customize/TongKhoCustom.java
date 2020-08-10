package com.example.doan_vai_ver1.Customize;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.doan_vai_ver1.Object.TongKho;
import com.example.doan_vai_ver1.R;

import java.util.ArrayList;


public class TongKhoCustom extends ArrayAdapter {
    ArrayList<TongKho> data;
    int resource;
    Context context;
    public TongKhoCustom(Context context, int resource, ArrayList<TongKho> data) {
        super(context, resource);
        this.data = data;
        this.context = context;
        this.resource = resource;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, null);
        TextView txt_stt = view.findViewById(R.id.tongkho_stt);
        TextView txt_tenvai = view.findViewById(R.id.tongkho_tenvai);
        TextView txt_soluong = view.findViewById(R.id.tongkho_soluong);
        TongKho tongkho = data.get(position);
        txt_stt.setText(data.get(position).getStt() + "");
        txt_tenvai.setText(data.get(position).getTen());
        txt_soluong.setText(data.get(position).getSoluong() + "m2");
        return view;
    }
}
