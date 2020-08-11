package com.example.doan_vai_ver1.Object;

public class TongKho {
    int stt;
    String ten;
    int soluong;

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public TongKho() {
    }

    public TongKho(int stt, String ten, int soluong) {
        this.stt = stt;
        this.ten = ten;
        this.soluong = soluong;
    }
}
