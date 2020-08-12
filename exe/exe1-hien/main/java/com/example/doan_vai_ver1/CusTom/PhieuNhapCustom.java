package com.example.doan_vai_ver1.CusTom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.doan_vai_ver1.SanPham.Nhap;
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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(resource, null);

        TextView edt1 = view.findViewById(R.id.txt_phieunhap_stt);
        TextView edt2 = view.findViewById(R.id.txt_phieunhap_ngay);
        TextView edt3 = view.findViewById(R.id.txt_phieunhap_loaivai);
        TextView edt4 = view.findViewById(R.id.txt_phieunhap_makho);
        TextView edt5 = view.findViewById(R.id.txt_phieunhap_soluong);

        Nhap nhap = data.get(getCount() - position - 1);
        edt1.setText(getCount() - position + "");
        edt2.setText(nhap.getNgay());
        edt3.setText(nhap.getMavai());
        edt4.setText(nhap.getTenkho());
        edt5.setText(nhap.getSoluong() + "m2");

        return view;
    }
}
