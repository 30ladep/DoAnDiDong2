package com.example.doan_vai_ver1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "QLV.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qlvCreateLoaiVai = "Create table LOAIVAI(ID INTEGER PRIMARY KEY AUTOINCREMENT, MA TEXT, TEN TEXT, XUATXU TEXT)";
        String qlvCreateNhap = "Create table PHIEUNHAP(ID INTEGER PRIMARY KEY AUTOINCREMENT, NGAY TEXT, MA TEXT, SOLUONG INTEGER)";
        String qlvCreateXuat = "Create table PHIEUXUAT(ID INTEGER PRIMARY KEY AUTOINCREMENT, NGAY TEXT, MA TEXT, SOLUONG INTEGER)";
        db.execSQL(qlvCreateLoaiVai);
        db.execSQL(qlvCreateNhap);
        db.execSQL(qlvCreateXuat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
