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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, null);
        //-------
        ImageView imageView = view.findViewById(R.id.vai_img);
        TextView txt_stt = view.findViewById(R.id.vai_stt);
        TextView txt_ms = view.findViewById(R.id.vai_ms);
        TextView txt_ten = view.findViewById(R.id.vai_ten);
        TextView txt_xuatxu = view.findViewById(R.id.vai_xuatxu);
        //---------------------
        Vai vai = data.get(getCount() - position - 1);
        imageView.setImageResource(R.drawable.vai);
        txt_stt.setText(getCount() - position + "");
        txt_ms.setText(context.getResources().getString(R.string.mavai) + ": " + vai.getVai_ms());
        txt_ten.setText(context.getResources().getString(R.string.tenvai) + ": " + vai.getVai_ten());
        txt_xuatxu.setText(context.getResources().getString(R.string.xuatxu) + ": " + vai.getVai_xuatxu());
        return view;
    }
}
