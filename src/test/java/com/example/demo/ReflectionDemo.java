package com.example.demo;

import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author yl
 * @Date 2019/12/17 17:23
 * @description:
 */
public class ReflectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> sonClass = Class.forName("com.example.demo.Son");
        /*1、获取所有的属性(private,public)，但不包括父类的*/
        System.out.println("==获取所有的属性(private,public)，但不包括父类的==");
        Field[] declaredFields = sonClass.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(item -> System.out.println(item.getName()));
        /*2、获取所有的属性(包括父类)，但不包括privat*/
        System.out.println("==获取所有的属性(包括父类的)，但不包括private==");
        Field[] fields = sonClass.getFields();
        Arrays.stream(fields).forEach(item -> System.out.println(item.getName()));
        /*3、获取所有的方法(private,public)，但不包括父类的*/
        System.out.println("==获取所有的方法(private,public)，但不包括父类的==");
        Method[] declaredMethods = sonClass.getDeclaredMethods();
        Arrays.stream(declaredMethods).forEach(item -> System.out.println(item.getName()));
        /*4、获取所有的方法(包括父类的)，但不包括private*/
        System.out.println("==获取所有的方法(包括父类的)，但不包括private==");
        Method[] methods = sonClass.getMethods();
        Arrays.stream(methods).forEach(item -> System.out.println(item.getName()));
        Son son = (Son)sonClass.newInstance();
        son.setAge(35);
        son.setName("zhangsan");
        System.out.println(son);
        Class<? extends Son> aClass = son.getClass();
        Method setAge = aClass.getMethod("setAge", Integer.class);
        setAge.invoke(son,22);
        System.out.println(son);

    }
}

@Data
class Father {
    private String wife;
    public String son;
    private void printFatherWife(){
        System.out.println("My wife is "+ this.wife);
    }
    public void printFatherSon(){
        System.out.println("My son is "+ this.son);
    }
}

@Data
class Son extends Father {
    public String name;
    private Integer age;
    private void printSonName(){
        System.out.println("My name is "+ this.name);
    }
    public void printSonAge(){
        System.out.println("My age is "+ this.age);
    }
}