package com.example.doan_vai_ver1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.doan_vai_ver1.Object.Nhap;

import java.util.ArrayList;

public class NhapManager {
    DBHelper dbHelper;
    SQLiteDatabase db;
    public NhapManager(Context context){
        dbHelper = new DBHelper(context);
    }
    public ArrayList<Nhap> LayDL(){
        ArrayList<Nhap> data = new ArrayList<>();
        String sql = "select * from PHIEUNHAP order by ID desc";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if((cursor.getCount() > 0) && cursor != null){
            int i = cursor.getCount();
            do
            {
                Nhap nhap = new Nhap();
                nhap.setStt(i--);
                nhap.setNgay(cursor.getString(1));
                nhap.setMavai(cursor.getString(2));
                nhap.setSoluong(cursor.getInt(3));
                data.add(nhap);
            }
            while (cursor.moveToNext());
        }

        return data;
    }

    public int Themsp(Nhap nhap){
        db = dbHelper.getWritableDatabase();
        //kiem tra san pham có tồn tại chưa
        String sql = "select * from PHIEUNHAP where ID = '" + nhap.getStt() +"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            ContentValues values = new ContentValues();
            values.put("NGAY", nhap.getNgay());
            values.put("MA", nhap.getMavai());
            values.put("SOLUONG", nhap.getSoluong());
            db.insert("PHIEUNHAP", null, values);
        }
        return cursor.getCount(); // = 0 la them dc
    }
    public void DelProduct(Nhap nhap){
        db = dbHelper.getWritableDatabase();
        String sql = "delete from PHIEUNHAP where ID = " + nhap.getStt();
        db.execSQL(sql);
    }
}
