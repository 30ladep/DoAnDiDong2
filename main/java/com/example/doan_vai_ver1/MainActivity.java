package com.example.doan_vai_ver1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.doan_vai_ver1.View.LoaiVaiActivity;
import com.example.doan_vai_ver1.View.NhapActivity;
import com.example.doan_vai_ver1.View.TongKhoActivity;
import com.example.doan_vai_ver1.View.XuatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btn_loaivai, btn_Phieunhap, btn_Phieuxuat, btn_Tongkho;
    private Menu menu;
    /*
    * Create pref and lang to save previous version
    * */
    SharedPreferences pref;
    String lang = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        * SET LANGUAGE IN MEMORY
        * */
        loadLocale();
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("setting", Activity.MODE_PRIVATE);
        lang = pref.getString("my_lang", "");

        setControl();
        setEvent();

    }

    private void setEvent() {
        /*
        *CHANGE LANGUAE IN ACTIONBAR
        */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        //khoitaoDATA();


        btn_loaivai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoaiVaiActivity.class));
            }
        });
        btn_Phieunhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NhapActivity.class));
            }
        });
        btn_Phieuxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, XuatActivity.class));
            }
        });
        btn_Tongkho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TongKhoActivity.class));
            }
        });
    }

    private void khoitaoDATA() {

    }

    private void setControl() {

        btn_loaivai = findViewById(R.id.btnLoaiVai);
        btn_Phieunhap = findViewById(R.id.btnPhieunhap);
        btn_Phieuxuat = findViewById(R.id.btn_phieuxuat);
        btn_Tongkho = findViewById(R.id.btnTongKho);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.menu = menu;
        MenuItem menuItem = menu.findItem(R.id.mnLang);

        if(lang.equals("") || lang.equals("vi")){
            menuItem.setIcon(R.drawable.vn);
        }
        else if(lang.equals("en")){
            menuItem.setIcon(R.drawable.us);
        }
        else if(lang.equals("ja")){
            menuItem.setIcon(R.drawable.ja);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnLang:
                ShowChangeLanguageDialog();
                break;
            case R.id.mnExit:
                showQuestionExit();
                break;
            default:
                showQuestionExit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    * Show Alert "Do you want to exit??"
    * */
    private void showQuestionExit() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(getResources().getString(R.string.lang));
        builder.setMessage(getResources().getString(R.string.mess_exit));
        builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /*
    *show Alert choose language
    */
    private void ShowChangeLanguageDialog(){
        //create array language
        final String[] lang = {"Việt Nam", "日本", "English"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose language..");
        builder.setSingleChoiceItems(lang, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(i == 0){
                    setLocale("vi");
                    recreate();
                }
                else if(i == 1){
                    setLocale("ja");
                    recreate();
                }
                else if(i == 2){
                    setLocale("en");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    /*
    * Set locale with input language
    * */
    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        //save lang to shared
        SharedPreferences.Editor editor = getSharedPreferences("setting",MODE_PRIVATE).edit();
        editor.putString("my_lang", language);
        editor.apply();
    }

    /*
    * load locale before show MainActivity
    * */
    public void loadLocale(){
        pref = getSharedPreferences("setting", Activity.MODE_PRIVATE);
        lang = pref.getString("my_lang", "");
        setLocale(lang);
    }

}
