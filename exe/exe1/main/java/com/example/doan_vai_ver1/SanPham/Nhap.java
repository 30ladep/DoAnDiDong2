package com.example.doan_vai_ver1.SanPham;

public class Nhap {
    String ngay;
    String mavai;
    String tenkho;
    String soluong;

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

    public String getTenkho() {
        return tenkho;
    }

    public void setTenkho(String tenkho) {
        this.tenkho = tenkho;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public Nhap(String ngay, String mavai, String tenkho, String soluong) {
        this.ngay = ngay;
        this.mavai = mavai;
        this.tenkho = tenkho;
        this.soluong = soluong;
    }

    public Nhap() {
    }
}
