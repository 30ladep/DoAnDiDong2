package com.example.doan_vai_ver1.Object;

public class Xuat {
    String ngay;
    String loaivai;
    int soluong;
    int stt;

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getLoaivai() {
        return loaivai;
    }

    public void setLoaivai(String loaivai) {
        this.loaivai = loaivai;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public Xuat() {
    }

    public Xuat(String ngay, String loaivai, int soluong, int stt) {
        this.ngay = ngay;
        this.loaivai = loaivai;
        this.soluong = soluong;
        this.stt = stt;
    }
}
