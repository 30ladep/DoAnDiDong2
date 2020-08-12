package com.example.doan_vai_ver1.CusTom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doan_vai_ver1.SanPham.Kho;
import com.example.doan_vai_ver1.R;

import java.util.ArrayList;

public class KhoCustom extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Kho> data;

    public KhoCustom(Context context, int resource, ArrayList<Kho> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, null);

        ImageView imageView = view.findViewById(R.id.kho_img);
        TextView txt_stt = view.findViewById(R.id.kho_stt);
        TextView txt_ms = view.findViewById(R.id.kho_ms);
        TextView txt_ten = view.findViewById(R.id.kho_ten);
        TextView txt_diachi = view.findViewById(R.id.kho_diachi);

        Kho kho = data.get(getCount() - position - 1);
//        imageView.setImageResource(R.drawable.house);
        txt_stt.setText(getCount() - position + "");

        txt_ms.setText(context.getResources().getString(R.string.Makho) + ": " + kho.getMakho());
        txt_ten.setText(context.getResources().getString(R.string.TenKho) + ": " + kho.getTenkho());
        txt_diachi.setText(context.getResources().getString(R.string.diachi) + ": " + kho.getDiachi());
        return view;
    }
}
