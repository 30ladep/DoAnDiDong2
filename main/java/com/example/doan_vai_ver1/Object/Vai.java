package com.example.doan_vai_ver1.Object;

public class Vai {
    String vai_ms;
    String vai_ten;
    String vai_xuatxu;

    public String getVai_ms() {
        return vai_ms;
    }

    public void setVai_ms(String vai_ms) {
        this.vai_ms = vai_ms;
    }

    public String getVai_ten() {
        return vai_ten;
    }

    public void setVai_ten(String vai_ten) {
        this.vai_ten = vai_ten;
    }

    public String getVai_xuatxu() {
        return vai_xuatxu;
    }

    public void setVai_xuatxu(String vai_xuatxu) {
        this.vai_xuatxu = vai_xuatxu;
    }

    public Vai(String vai_ms, String vai_ten, String vai_xuatxu) {
        this.vai_ms = vai_ms;
        this.vai_ten = vai_ten;
        this.vai_xuatxu = vai_xuatxu;
    }

    public Vai() {
    }
}
