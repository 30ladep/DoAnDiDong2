package com.example.doan_vai_ver1.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.example.doan_vai_ver1.CusTom.VaiCustom;
import com.example.doan_vai_ver1.SanPham.Vai;
import com.example.doan_vai_ver1.R;

import java.util.ArrayList;

import static com.example.doan_vai_ver1.R.id.txt_vai_xuatxu;

public class LoaiVaiActivity extends AppCompatActivity {

    //-----------
    EditText edt1, edt2, edt3;
    Button btnThem, btnSua, btnXoa;
    //******************************
    ListView lw;
    ArrayList<Vai> data = new ArrayList<>();
    VaiCustom adapter;
    //------------------------------------
    /*
     * variable use to check position
     * */
    int check_position = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_vai);
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
         *CHANGE LANGUAE IN actionbar
         */
        actionBar.setTitle(getResources().getString(R.string.loai_vai));

        khoitao();
        adapter = new VaiCustom(this, R.layout.loaivai_customize, data);
        lw.setAdapter(adapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt1.getText().toString().equals("")) {
                    edt1.setError(getResources().getString(R.string.error_null));
                    return;
                }
                if (edt2.getText().toString().equals("")) {
                    edt2.setError(getResources().getString(R.string.error_null));
                    return;
                }
                if (edt3.getText().toString().equals("")) {
                    edt3.setError(getResources().getString(R.string.error_null));
                    return;
                }
                Vai vai = new Vai();
                vai.setVai_ms(edt1.getText().toString());
                vai.setVai_ten(edt2.getText().toString());
                vai.setVai_xuatxu(edt3.getText().toString());
                data.add(vai);
                adapter.notifyDataSetChanged();
            }
        });
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0) {
                    btnSua.setEnabled(true);
                    btnXoa.setEnabled(true);
                }
                edt1.setText(data.get(position).getVai_ms());
                edt2.setText(data.get(position).getVai_ten());
                edt3.setText(data.get(position).getVai_xuatxu());
                check_position = position;
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt1.getText().toString().equals("")) {
                    edt1.setError(getResources().getString(R.string.error_null));
                    return;
                }
                if (edt2.getText().toString().equals("")) {
                    edt2.setError(getResources().getString(R.string.error_null));
                    return;
                }
                if (edt3.getText().toString().equals("")) {
                    edt3.setError(getResources().getString(R.string.error_null));
                    return;
                }
                data.get(check_position).setVai_ms(edt1.getText().toString());
                data.get(check_position).setVai_ten(edt2.getText().toString());
                data.get(check_position).setVai_xuatxu(edt3.getText().toString());
                adapter.notifyDataSetChanged();

                //-------------------------------
                edt1.setText("");
                edt2.setText("");
                edt3.setText("");
                //-----------------------
                btnSua.setEnabled(false);
                btnXoa.setEnabled(false);
                check_position = -1;
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(check_position);
                adapter.notifyDataSetChanged();
                //-------------------------------
                edt1.setText("");
                edt2.setText("");
                edt3.setText("");
                //-----------------------
                btnSua.setEnabled(false);
                btnXoa.setEnabled(false);
                check_position = -1;
            }
        });
    }

    private void setControl() {
        lw = findViewById(R.id.vaiLW);
        edt1 = findViewById(R.id.txt_vai_ms);
        edt2 = findViewById(R.id.txt_vai_Ten);
        edt3 = findViewById(txt_vai_xuatxu);
        //------------------------------------------
        btnThem = findViewById(R.id.btn_vai_them);
        btnSua = findViewById(R.id.btn_vai_sua);
        btnXoa = findViewById(R.id.btn_vai_xoa);
    }
    private void khoitao() {
        data.add(new Vai("V1", "Nhung", "Q1"));
        data.add(new Vai("V1", "Nhung", "Q1"));
        data.add(new Vai("V1", "Nhung", "Q1"));
        data.add(new Vai("V1", "Nhung", "Q1"));
        data.add(new Vai("V1", "Nhung", "Q1"));
        data.add(new Vai("V1", "Nhung", "Q1"));
        data.add(new Vai("V1", "Nhung", "Q1"));

    }
    public boolean onOptionsItemSelected(MenuItem item) {

        onBackPressed();
        return true;

    }
}
