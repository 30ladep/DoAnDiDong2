package com.example.doan_vai_ver1.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.doan_vai_ver1.Customize.TongKhoCustom;
import com.example.doan_vai_ver1.Object.Nhap;
import com.example.doan_vai_ver1.Object.TongKho;
import com.example.doan_vai_ver1.Object.Vai;
import com.example.doan_vai_ver1.Object.Xuat;
import com.example.doan_vai_ver1.R;
import com.example.doan_vai_ver1.db.LoaiVaiManager;
import com.example.doan_vai_ver1.db.NhapManager;
import com.example.doan_vai_ver1.db.XuatManager;

import java.util.ArrayList;

public class TongKhoActivity extends AppCompatActivity {

    ListView lv;
    NhapManager nhapmanager;
    XuatManager xuatmanager;
    LoaiVaiManager loaivaimanager;
    ArrayList<TongKho> data = new ArrayList<>();
    TongKhoCustom adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_kho);
        setControl();
        setEvent();
    }

    private void setEvent() {
        /*
         * add button BACK in actionbar
         * */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        /*
         *CHANGE LANGUAE IN ACTIONBAR
         */
        actionBar.setTitle(getResources().getString(R.string.tong_kho));

        khoitao();
    }

    private void khoitao() {
        loaivaimanager = new LoaiVaiManager(getApplicationContext());
        nhapmanager = new NhapManager(getApplicationContext());
        xuatmanager = new XuatManager(getApplicationContext());
        ArrayList<Vai> listvai = loaivaimanager.LayDL();
        ArrayList<Nhap> listnhap = nhapmanager.LayDL();
        ArrayList<Xuat> listxuat = xuatmanager.LayDL();
        TongKho tongkho = new TongKho();
        for (int i = 0; i < listvai.size(); i++){
            tongkho.setTen(listvai.get(i).getVai_ten());
            int soluong = 0;
            for(int j = 0; j < listnhap.size(); j++){
                if(listnhap.get(j).getMavai().equals(listvai.get(i).getVai_ms())){
                    soluong += listnhap.get(j).getSoluong();
                }
            }
            for(int k = 0; k < listxuat.size(); k++){
                if(listxuat.get(k).getLoaivai().equals(listvai.get(i).getVai_ms())){
                    soluong -= listxuat.get(k).getSoluong();
                }
            }
            tongkho.setSoluong(soluong);
            data.add(tongkho);
        }
        adapter = new TongKhoCustom(this, R.layout.tongkho_custom, data);
        lv.setAdapter(adapter);
    }

    private void setControl() {
        lv = findViewById(R.id.tongkholv);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }
}
