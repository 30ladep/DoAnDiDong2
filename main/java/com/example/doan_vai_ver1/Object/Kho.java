package com.example.doan_vai_ver1.Object;

public class Kho {

    public Kho() {
    }

    String makho;
    String tenkho;
    String diachi;

    public Kho(String makho, String tenkho, String diachi) {
        this.makho = makho;
        this.tenkho = tenkho;
        this.diachi = diachi;
    }

    public String getMakho() {
        return makho;
    }

    public void setMakho(String makho) {
        this.makho = makho;
    }

    public String getTenkho() {
        return tenkho;
    }

    public void setTenkho(String tenkho) {
        this.tenkho = tenkho;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
