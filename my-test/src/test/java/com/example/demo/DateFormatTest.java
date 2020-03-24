package com.example.demo;

import java.text.SimpleDateFormat;

/**
 * @Author yl
 * @Date 2019/12/12 11:28
 * @description:
 */
public class DateFormatTest {
    public static void main(String[] args) {
        String str = "yyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        System.out.println(sdf.format(1576120073147L));
    }
}
