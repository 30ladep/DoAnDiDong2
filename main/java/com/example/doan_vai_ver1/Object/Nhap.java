package com.example.doan_vai_ver1.Object;

public class Nhap {
    int stt;
    String ngay;
    String mavai;
    int soluong;

    public Nhap(int stt, String ngay, String mavai, int soluong) {
        this.stt = stt;
        this.ngay = ngay;
        this.mavai = mavai;
        this.soluong = soluong;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getMavai() {
        return mavai;
    }

    public void setMavai(String mavai) {
        this.mavai = mavai;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Nhap() {
    }
}
