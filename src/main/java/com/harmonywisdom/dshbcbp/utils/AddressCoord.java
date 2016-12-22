package com.harmonywisdom.dshbcbp.utils;

/**
 * 记录地址编码或poi搜索找到的地址
 * Created by sunzuoquan on 14-4-16.
 */
public class AddressCoord {
    private String address;
    private double x;
    private double y;

    public AddressCoord(String address,double x,double y)
    {
        this.address =address;
        this.x = x;
        this.y = y;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

