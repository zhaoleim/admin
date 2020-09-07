package com.admin.springboot.adminVO;

import lombok.Data;

@Data
public class Person {
    private String name;
    private Integer age;

    public static int  compareByAge(Person a,Person b){
        return  a.age.compareTo(b.age);
    }
}
