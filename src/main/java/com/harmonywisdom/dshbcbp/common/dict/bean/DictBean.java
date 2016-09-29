package com.harmonywisdom.dshbcbp.common.dict.bean;

import java.util.HashMap;

/**
 * Created by hotleave on 15-3-9.
 */
public class DictBean extends HashMap<String, String> implements Comparable<DictBean> {
    public static final String CODE = "code";
    public static final String NAME = "name";

    private double serial;

    public String getCode() {
        return get(CODE);
    }

    public void setCode(String code) {
        put(CODE, code);
    }

    public String getName() {
        return get(NAME);
    }

    public void setName(String name) {
        put(NAME, name);
    }

    public double getSerial() {
        return serial;
    }

    public void setSerial(double serial) {
        this.serial = serial;
    }

    @Override
    public int compareTo(DictBean o) {
        double target = o.getSerial();
        return serial > target ? 1 : (serial < target ? -1 : 0);
    }
}
