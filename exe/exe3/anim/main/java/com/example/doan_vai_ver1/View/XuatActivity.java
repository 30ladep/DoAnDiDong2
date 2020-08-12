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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doan_vai_ver1.Customize.PhieuXuatCustom;
import com.example.doan_vai_ver1.Object.Xuat;
import com.example.doan_vai_ver1.R;

import java.util.ArrayList;

public class XuatActivity extends AppCompatActivity {
    ListView listView;
    Button btnXuat;
    ArrayList<Xuat> data = new ArrayList<>();
    PhieuXuatCustom adapter;
    int index = -1;

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
        adapter = new PhieuXuatCustom(this, R.layout.xuat_customize, data);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                /*
                 * Vì trong customize ta duyệt list từ dưới lên
                 * nên khi gán index ta đảo ngược lại
                 *
                 * */
                index = data.size() - position - 1;
                String str = getResources().getString(R.string.ngay) + ": " + data.get(index).getNgay() + "\n "
                        + getResources().getString(R.string.loai_vai) + ": " + data.get(index).getLoaivai() + "\n "
                        + getResources().getString(R.string.kho) + ": " + data.get(index).getKho() + "\n "
                        + getResources().getString(R.string.soluong) + ": " + data.get(index).getSoluong() + "m2";
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
        listView = findViewById(R.id.xuat_listview);
        btnXuat = findViewById(R.id.btn_xuat_them);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }

    public void khoitao() {
        data.add(new Xuat("12/2/2020", "VT02", "TD", "150"));
        data.add(new Xuat("12/2/2020", "VT02", "TD", "150"));
        data.add(new Xuat("12/2/2020", "VT02", "TD", "150"));
        data.add(new Xuat("12/2/2020", "VT02", "TD", "150"));
        data.add(new Xuat("12/2/2020", "VT02", "TD", "150"));
        data.add(new Xuat("12/2/2020", "VT02", "TD", "150"));
        data.add(new Xuat("12/2/2020", "VT02", "TD", "150"));
        data.add(new Xuat("12/2/2020", "VT02", "TD", "150"));
    }

    public void ShowDialogNhap() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_xuat);
        dialog.setTitle(getResources().getString(R.string.phieu_xuat));

        //ánh xạ
        final EditText ngay = dialog.findViewById(R.id.txt_xuat_ngay);
        final EditText loaivai = dialog.findViewById(R.id.txt_xuat_loaivai);
        final EditText makho = dialog.findViewById(R.id.txt_xuat_kho);
        final EditText soluong = dialog.findViewById(R.id.txt_xuat_soluong);
        Button btnThem = dialog.findViewById(R.id.btn_dialogxuat_them);
        Button btnKhong = dialog.findViewById(R.id.btn_dialogxuat_khong);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ngay.getText().toString().equals("")) {
                    ngay.setError(getResources().getString(R.string.error_null));
                    return;
                }
                if (loaivai.getText().toString().equals("")) {
                    loaivai.setError(getResources().getString(R.string.error_null));
                    return;
                }
                if (makho.getText().toString().equals("")) {
                    makho.setError(getResources().getString(R.string.error_null));
                    return;
                }
                if (soluong.getText().toString().equals("")) {
                    soluong.setError(getResources().getString(R.string.error_null));
                    return;
                }
                Xuat xuat = new Xuat();
                xuat.setNgay(ngay.getText().toString());
                xuat.setLoaivai(loaivai.getText().toString());
                xuat.setKho(makho.getText().toString());
                xuat.setSoluong(soluong.getText().toString());
                data.add(xuat);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
                Toast.makeText(XuatActivity.this, "sucessfully", Toast.LENGTH_SHORT).show();
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
