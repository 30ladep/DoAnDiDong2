package com.example.doan_vai_ver1.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doan_vai_ver1.Customize.VaiCustom;
import com.example.doan_vai_ver1.Object.Vai;
import com.example.doan_vai_ver1.R;
import com.example.doan_vai_ver1.db.LoaiVaiManager;

import java.util.ArrayList;

public class LoaiVaiActivity extends AppCompatActivity {

    LoaiVaiManager loaivaimanager;
    //-----------
    EditText edt1, edt2, edt3;
    Button btnThem, btnSua, btnXoa, btnReset;
    //******************************
    ListView lw;
    ArrayList<Vai> data = new ArrayList<>();
    VaiCustom adapter;
    //------------------------------------
    /*
     * variable use to check position
     * */
    ///Khai bao lop database
   // DBHelper loaiHP;
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
                int check = loaivaimanager.Themsp(vai);
                if(check == 0){
                    Toast.makeText(LoaiVaiActivity.this, "thanh cong", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoaiVaiActivity.this, "nonono", Toast.LENGTH_SHORT).show();
                    return;
                }
                khoitao();
            }
        });
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edt1.setEnabled(false);
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
                Vai vai = new Vai();
                vai.setStt(data.get(check_position).getStt());
                vai.setVai_ms(edt1.getText().toString().trim());
                vai.setVai_ten(edt2.getText().toString().trim());
                vai.setVai_xuatxu(edt3.getText().toString().trim());
                loaivaimanager.Sua(vai);
                khoitao();
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
                Vai vai = new Vai();
                vai.setStt(data.get(check_position).getStt());
                vai.setVai_ms(data.get(check_position).getVai_ms());
                vai.setVai_ten(data.get(check_position).getVai_ten());
                vai.setVai_xuatxu(data.get(check_position).getVai_xuatxu());
                int check = loaivaimanager.DelProduct(vai.getVai_ms());
                if (check == -1){
                    Toast.makeText(getApplication(), "Khong xoa duoc!!", Toast.LENGTH_SHORT).show();
                }
                khoitao();
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
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setEnabled(true);
                edt1.setText("");
                edt2.setText("");
                edt3.setText("");
                btnSua.setEnabled(false);
                btnXoa.setEnabled(false);
            }
        });
    }

    private void setControl() {
        lw = findViewById(R.id.vaiLW);
        edt1 = findViewById(R.id.txt_vai_ms);
        edt2 = findViewById(R.id.txt_vai_Ten);
        edt3 = findViewById(R.id.txt_vai_xuatxu);
        //------------------------------------------
        btnThem = findViewById(R.id.btn_vai_them);
        btnSua = findViewById(R.id.btn_vai_sua);
        btnXoa = findViewById(R.id.btn_vai_xoa);
        btnReset = findViewById(R.id.btn_vai_reset);
    }
    private void khoitao() {
        loaivaimanager = new LoaiVaiManager(getApplicationContext());
        data = loaivaimanager.LayDL();
        adapter = new VaiCustom(this, R.layout.loaivai_customize, data);
        lw.setAdapter(adapter);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        onBackPressed();
        return true;

    }
}
