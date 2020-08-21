package com.admin.springboot.adminVO;

import lombok.Data;

public class Mask {
    private String brand;
    private String type;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Mask(String brand,String type){
        this.brand = brand;
        this.type = type;
    }
}
