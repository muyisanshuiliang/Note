package com.example.demo.apache.commons;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @Author yl
 * @Date 2020/2/19 18:11
 * @description:
 */
@Data
@ToString(callSuper = true)
public class User extends Parent{

    private Integer id;

    private String name;

    private String[] subject;

    private Map<String,String> contactDetails;

    private List<Book> books;
}