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

import com.example.doan_vai_ver1.Customize.KhoCustom;
import com.example.doan_vai_ver1.Object.Kho;
import com.example.doan_vai_ver1.R;

import java.util.ArrayList;

public class KhoActivity extends AppCompatActivity {

    //-----------
    EditText edt1, edt2, edt3;
    Button btnThem, btnSua, btnXoa;
    //******************************
    ListView lw;
    ArrayList<Kho> data = new ArrayList<>();
    KhoCustom adapter;
    //------------------------------------
    /*
     * variable use to check position
     * */
    int check_position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kho);

        setControl();
        setEvent();
    }

    private void setControl() {
        lw = findViewById(R.id.kho_listview);
        edt1 = findViewById(R.id.txt_kho_ms);
        edt2 = findViewById(R.id.txt_kho_Ten);
        edt3 = findViewById(R.id.txt_kho_diachi);
        //------------------------------------------
        btnThem = findViewById(R.id.btn_kho_them);
        btnSua = findViewById(R.id.btn_kho_sua);
        btnXoa = findViewById(R.id.btn_kho_xoa);
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
        adapter = new KhoCustom(this, R.layout.kho_customize, data);
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
                Kho kho = new Kho();
                kho.setMakho(edt1.getText().toString());
                kho.setTenkho(edt2.getText().toString());
                kho.setDiachi(edt3.getText().toString());
                data.add(kho);
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
                int index = data.size() - position - 1;
                edt1.setText(data.get(index).getMakho());
                edt2.setText(data.get(index).getTenkho());
                edt3.setText(data.get(index).getDiachi());
                check_position = index;
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
                data.get(check_position).setMakho(edt1.getText().toString());
                data.get(check_position).setTenkho(edt2.getText().toString());
                data.get(check_position).setDiachi(edt3.getText().toString());
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

    public boolean onOptionsItemSelected(MenuItem item) {

        onBackPressed();
        return true;

    }


    private void khoitao() {
        data.add(new Kho("TD1", "Tương Dương", "Q1"));
        data.add(new Kho("TD2", "Tương Dương", "Q1"));
        data.add(new Kho("TD3", "Tương Dương", "Q1"));
        data.add(new Kho("TD4", "Tương Dương", "Q1"));
        data.add(new Kho("TD5", "Tương Dương", "Q1"));
        data.add(new Kho("TD6", "Tương Dương", "Q1"));
        data.add(new Kho("TD7", "Tương Dương", "Q1"));
        data.add(new Kho("TD8", "Tương Dương", "Q1"));
        data.add(new Kho("TD9", "Tương Dương", "Q1"));
        data.add(new Kho("TD10", "Tương Dương", "Q1"));
    }

}
