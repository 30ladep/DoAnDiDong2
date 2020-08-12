package com.example.doan_vai_ver1.GiaoDien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.doan_vai_ver1.CusTom.PhieuNhapCustom;
import com.example.doan_vai_ver1.SanPham.Nhap;
import com.example.doan_vai_ver1.R;

import java.util.ArrayList;

public class NhapActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Nhap> data = new ArrayList<>();
    PhieuNhapCustom nhapCustom;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap);
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
        actionBar.setTitle(getResources().getString(R.string.kho));

        khoitao();
        nhapCustom = new PhieuNhapCustom(this, R.layout.phieunhap_customize, data);
        listView.setAdapter(nhapCustom);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index = data.size() - position - 1;
                String str = getResources().getString(R.string.ngay) + ": 12/9/1998\n " + getResources().getString(R.string.loai_vai) + ": vải thô\n " + getResources().getString(R.string.kho) + ": tương dương\n " + getResources().getString(R.string.soluong) + ": 5000m2";
                AlertDialog.Builder builder = new AlertDialog.Builder(NhapActivity.this);
                builder.setTitle(getResources().getString(R.string.chitiet));
                builder.setMessage(str);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
    }

    private void setControl() {
        listView = findViewById(R.id.nhap_listview);
    }

    public void khoitao() {
        data.add(new Nhap("9/3/2012", "VY1", "TH23", "1500"));
        data.add(new Nhap("9/3/2012", "VY1", "TH23", "1500"));
        data.add(new Nhap("9/3/2012", "VY1", "TH23", "1500"));
        data.add(new Nhap("9/3/2012", "VY1", "TH23", "1500"));
        data.add(new Nhap("9/3/2012", "VY1", "TH23", "1500"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }
}
