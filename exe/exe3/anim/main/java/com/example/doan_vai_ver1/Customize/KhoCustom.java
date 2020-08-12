package com.example.doan_vai_ver1.Customize;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.res.Resources;
import android.widget.Toast;

import com.example.doan_vai_ver1.Object.Kho;
import com.example.doan_vai_ver1.R;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.LayoutRes;

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

        TextView txt_stt = view.findViewById(R.id.kho_stt);
        TextView txt_ms = view.findViewById(R.id.kho_ms);
        TextView txt_ten = view.findViewById(R.id.kho_ten);
        TextView txt_diachi = view.findViewById(R.id.kho_diachi);

        Kho kho = data.get(getCount() - position - 1);
        txt_stt.setText(getCount() - position + "");

        txt_ms.setText(context.getResources().getString(R.string.Makho) + ": " + kho.getMakho());
        txt_ten.setText(context.getResources().getString(R.string.TenKho) + ": " + kho.getTenkho());
        txt_diachi.setText(context.getResources().getString(R.string.diachi) + ": " + kho.getDiachi());
        return view;
    }
}
