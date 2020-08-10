package com.example.doan_vai_ver1.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doan_vai_ver1.Customize.PhieuNhapCustom;
import com.example.doan_vai_ver1.Customize.VaiCustom;
import com.example.doan_vai_ver1.Object.Nhap;
import com.example.doan_vai_ver1.Object.Vai;
import com.example.doan_vai_ver1.R;
import com.example.doan_vai_ver1.db.LoaiVaiManager;
import com.example.doan_vai_ver1.db.NhapManager;

import java.util.ArrayList;

public class NhapActivity extends AppCompatActivity {

    ListView lv;
    Button btnNhap;

    ArrayList<Nhap> data = new ArrayList<>();
    PhieuNhapCustom adapter;
    NhapManager nhapmanager;
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
        actionBar.setTitle(getResources().getString(R.string.phieu_nhap));

        khoitao();

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                nhapmanager.DelProduct(data.get(position));
                khoitao();
                return true;
            }
        });
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogNhap();
            }
        });
    }

    private void setControl() {
        lv = findViewById(R.id.nhap_listview);
        btnNhap = findViewById(R.id.btn_nhap_them);
    }

    public void khoitao() {
        nhapmanager = new NhapManager(getApplicationContext());
        data = nhapmanager.LayDL();
        adapter = new PhieuNhapCustom(this, R.layout.phieunhap_customize, data);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }
    public void ShowDialogNhap(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog);
        dialog.setTitle(getResources().getString(R.string.phieu_nhap));

        //ánh xạ
        final EditText ngay = dialog.findViewById(R.id.txt_nhap_ngay);
        final Spinner sp_loaivai = dialog.findViewById(R.id.spinner_nhap);
        final EditText soluong = dialog.findViewById(R.id.txt_nhap_soluong);
        Button btnThem = dialog.findViewById(R.id.btn_dialogNhap_them);
        Button btnKhong = dialog.findViewById(R.id.btn_dialogNhap_khong);

        final LoaiVaiManager loaivaimanager = new LoaiVaiManager(getApplicationContext());
        ArrayList<Vai> listvai = loaivaimanager.LayDL();
        ArrayList<String> arr_sp = new ArrayList<>();
        for (int i = 0; i < listvai.size(); i++){
            arr_sp.add(listvai.get(i).getVai_ten());
        }// co list data spinner
        ArrayAdapter<String> sp_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr_sp);
        sp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_loaivai.setAdapter(sp_adapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ngay.getText().toString().equals("")) {
                    ngay.setError(getResources().getString(R.string.error_null));
                    return;
                }

                if (soluong.getText().toString().equals("")) {
                    soluong.setError(getResources().getString(R.string.error_null));
                    return;
                }

                Nhap nhap = new Nhap();
                nhap.setNgay(ngay.getText().toString().trim());
                nhap.setMavai(loaivaimanager.getMA(sp_loaivai.getSelectedItem().toString()));
                nhap.setSoluong( Integer.parseInt(soluong.getText().toString().trim()));
                int check = nhapmanager.Themsp(nhap);
                if(check == 0){
                    khoitao();
                    dialog.dismiss();
                    Toast.makeText(NhapActivity.this, "sucessfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnKhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
