package com.ddl.designpattern.prototype;

import java.util.Date;

/**
 * 测试原型模式(浅克隆)
 */
public class Client {
    public static void main(String[] args) throws Exception {
        Date date = new Date(12312321331L);
        String sname = "少利";
        Sheep s1 = new Sheep(sname, date);
        System.out.println(s1);
        System.out.println(s1.getSname());
        System.out.println(s1.getBirthday());

        Sheep s2 = (Sheep) s1.clone();

        date.setTime(23432432423L);

        System.out.println(s1.getBirthday());


        s2.setSname(sname);
        System.out.println(s2);
        System.out.println(s2.getSname());
        System.out.println(s2.getBirthday());


    }
}
