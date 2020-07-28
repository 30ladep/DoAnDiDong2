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

import com.example.doan_vai_ver1.Customize.PhieuNhapCustom;
import com.example.doan_vai_ver1.Object.Nhap;
import com.example.doan_vai_ver1.R;

import java.util.ArrayList;

public class NhapActivity extends AppCompatActivity {

    ListView listView;
    Button btnNhap;
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
        actionBar.setTitle(getResources().getString(R.string.phieu_nhap));

        khoitao();
        nhapCustom = new PhieuNhapCustom(this, R.layout.phieunhap_customize, data);
        listView.setAdapter(nhapCustom);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                /*
                * Vì trong customize ta duyệt list từ dưới lên
                * nên khi gán index ta đảo ngược lại
                *
                * */
                index = data.size() - position - 1;
                String str = getResources().getString(R.string.ngay) + ": "+data.get(index).getNgay()+"\n "
                        + getResources().getString(R.string.loai_vai) + ": "+data.get(index).getMavai()+"\n "
                        + getResources().getString(R.string.kho) + ": "+data.get(index).getTenkho()+"\n "
                        + getResources().getString(R.string.soluong) + ": "+data.get(index).getSoluong()+"m2";
                AlertDialog.Builder builder = new AlertDialog.Builder(NhapActivity.this);
                builder.setTitle(getResources().getString(R.string.chitiet));
                builder.setMessage(str);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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
        listView = findViewById(R.id.nhap_listview);
        btnNhap = findViewById(R.id.btn_nhap_them);
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
    public void ShowDialogNhap(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog);
        dialog.setTitle(getResources().getString(R.string.phieu_nhap));

        //ánh xạ
        final EditText ngay = dialog.findViewById(R.id.txt_nhap_ngay);
        final EditText loaivai = dialog.findViewById(R.id.txt_nhap_loaivai);
        final EditText makho = dialog.findViewById(R.id.txt_nhap_makho);
        final EditText soluong = dialog.findViewById(R.id.txt_nhap_soluong);
        Button btnThem = dialog.findViewById(R.id.btn_dialogNhap_them);
        Button btnKhong = dialog.findViewById(R.id.btn_dialogNhap_khong);

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
                Nhap nhap = new Nhap();
                nhap.setNgay(ngay.getText().toString());
                nhap.setMavai(loaivai.getText().toString());
                nhap.setTenkho(makho.getText().toString());
                nhap.setSoluong(soluong.getText().toString());
                data.add(nhap);
                nhapCustom.notifyDataSetChanged();
                dialog.dismiss();
                Toast.makeText(NhapActivity.this, "sucessfully", Toast.LENGTH_SHORT).show();
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
