package com.example.doan_vai_ver1.Customize;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.doan_vai_ver1.Object.Xuat;
import com.example.doan_vai_ver1.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PhieuXuatCustom extends ArrayAdapter {
    ArrayList<Xuat> data;
    int resource;
    Context context;

    public PhieuXuatCustom(Context context, int resource, ArrayList<Xuat> data) {
        super(context, resource);
        this.data = data;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, null);
        TextView txt_stt = view.findViewById(R.id.txt_xuat_stt);
        TextView txt_ngay = view.findViewById(R.id.txt_xuat_ngay);
        TextView txt_loaivai = view.findViewById(R.id.txt_xuat_loaivai);
        TextView txt_kho = view.findViewById(R.id.txt_xuat_makho);
        TextView txt_soluong = view.findViewById(R.id.txt_xuat_soluong);
        Xuat xuat = data.get(getCount() - position - 1);
        txt_stt.setText(getCount() - position + "");
        txt_ngay.setText(xuat.getNgay());
        txt_loaivai.setText(xuat.getLoaivai());
        txt_kho.setText(xuat.getKho());
        txt_soluong.setText(xuat.getSoluong() + "m2");

        return view;
    }
}
