package com.example.doan_vai_ver1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doan_vai_ver1.Object.Xuat;

import java.util.ArrayList;

public class XuatManager {
    DBHelper dbHelper;
    SQLiteDatabase db;
    public XuatManager(Context context){
        dbHelper = new DBHelper(context);
    }
    public ArrayList<Xuat> LayDL(){
        ArrayList<Xuat> data = new ArrayList<>();
        String sql = "select * from PHIEUXUAT order by ID desc";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if((cursor.getCount() > 0) && cursor != null){
            int i = cursor.getCount();
            do
            {
                Xuat xuat = new Xuat();
                xuat.setStt(i--);
                xuat.setNgay(cursor.getString(1));
                xuat.setLoaivai(cursor.getString(2));
                xuat.setSoluong(cursor.getInt(3));
                data.add(xuat);
            }
            while (cursor.moveToNext());
        }

        return data;
    }

    public int Themsp(Xuat XUAT){
        db = dbHelper.getWritableDatabase();
        //kiem tra san pham có tồn tại chưa
        String sql = "select * from PHIEUXUAT where ID = '" + XUAT.getStt() +"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            ContentValues values = new ContentValues();
            values.put("NGAY", XUAT.getNgay());
            values.put("MA", XUAT.getLoaivai());
            values.put("SOLUONG", XUAT.getSoluong());
            db.insert("PHIEUXUAT", null, values);
        }
        return cursor.getCount(); // = 0 la them dc
    }
}
