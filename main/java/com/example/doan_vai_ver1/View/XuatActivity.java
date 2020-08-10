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

import com.example.doan_vai_ver1.Customize.PhieuXuatCustom;
import com.example.doan_vai_ver1.Object.Vai;
import com.example.doan_vai_ver1.Object.Xuat;
import com.example.doan_vai_ver1.R;
import com.example.doan_vai_ver1.db.LoaiVaiManager;
import com.example.doan_vai_ver1.db.XuatManager;

import java.util.ArrayList;

public class XuatActivity extends AppCompatActivity {
    ListView lv;
    Button btnXuat;
    ArrayList<Xuat> data = new ArrayList<>();
    PhieuXuatCustom adapter;
    XuatManager xuatmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuat);
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
        actionBar.setTitle(getResources().getString(R.string.phieu_xuat));

        khoitao();
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String str = getResources().getString(R.string.ngay) + ": " + data.get(position).getNgay() + "\n "
                        + getResources().getString(R.string.loai_vai) + ": " + data.get(position).getLoaivai() + "\n "
                        + getResources().getString(R.string.soluong) + ": " + data.get(position).getSoluong() + "m2";
                AlertDialog.Builder builder = new AlertDialog.Builder(XuatActivity.this);
                builder.setTitle(getResources().getString(R.string.chitiet));
                builder.setMessage(str);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
        btnXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogNhap();
            }
        });
    }

    private void setControl() {
        lv = findViewById(R.id.xuat_listview);
        btnXuat = findViewById(R.id.btn_xuat_them);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }

    public void khoitao() {
        xuatmanager = new XuatManager(getApplicationContext());
        data = xuatmanager.LayDL();
        adapter = new PhieuXuatCustom(this, R.layout.xuat_customize, data);
        lv.setAdapter(adapter);
    }

    public void ShowDialogNhap() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_xuat);
        dialog.setTitle(getResources().getString(R.string.phieu_xuat));

        //ánh xạ
        final EditText ngay = dialog.findViewById(R.id.txt_xuat_ngay);
        final Spinner sp_loaivai = dialog.findViewById(R.id.spinner_xuat);
        final EditText soluong = dialog.findViewById(R.id.txt_xuat_soluong);
        Button btnThem = dialog.findViewById(R.id.btn_dialogxuat_them);
        Button btnKhong = dialog.findViewById(R.id.btn_dialogxuat_khong);

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
                Xuat xuat = new Xuat();
                xuat.setNgay(ngay.getText().toString().trim());
                xuat.setLoaivai(loaivaimanager.getMA(sp_loaivai.getSelectedItem().toString()));
                xuat.setSoluong( Integer.parseInt(soluong.getText().toString().trim()));
                int check = xuatmanager.Themsp(xuat);
                if(check == 0){
                    khoitao();
                    dialog.dismiss();
                    Toast.makeText(XuatActivity.this, "sucessfully", Toast.LENGTH_SHORT).show();
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
