package com.example.doan_vai_ver1.Object;

public class Xuat {
    String ngay;
    String loaivai;
    String kho;

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

    public String getKho() {
        return kho;
    }

    public void setKho(String kho) {
        this.kho = kho;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public Xuat(String ngay, String loaivai, String kho, String soluong) {
        this.ngay = ngay;
        this.loaivai = loaivai;
        this.kho = kho;
        this.soluong = soluong;
    }

    public Xuat() {
    }

    String soluong;

}
