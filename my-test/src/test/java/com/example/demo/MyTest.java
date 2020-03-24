package com.example.demo;

import com.example.demo.mybatisplus.model.User;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StopWatch;

import java.math.BigInteger;
import java.util.Optional;

public class MyTest {
    public static void main(String[] args) {

//        AvailableWaterResourceUpdatePO availableWaterResourceUpdatePO = new AvailableWaterResourceUpdatePO();
//        availableWaterResourceUpdatePO.setFebruaryAmount(21.4);
//        System.out.println(availableWaterResourceUpdatePO);


        System.out.println(Integer.toHexString(20));
        User user = new User();
        user.setNumber(1);
        System.out.println(System.identityHashCode(user));
        System.out.println(user.hashCode());
        System.out.println(ObjectUtils.getIdentityHexString(user));

        System.out.println(ObjectUtils.getDisplayString(user));
        System.out.println(ObjectUtils.nullSafeClassName(user));
        user = null;
        System.out.println(ObjectUtils.getDisplayString(user));
        System.out.println(ObjectUtils.nullSafeClassName(user));

        Integer[] param = null;
        System.out.println(ObjectUtils.nullSafeToString(param));
//        System.out.println(param.length);
//        System.out.println(param.toString());
//        String s = ObjectUtils.nullSafeToString(param);
//        System.out.println(s);
//
//        char[] chars = {'1','q'};
//        System.out.println(ObjectUtils.nullSafeToString(chars));
//
//        Boolean[] booleans = {true,false,true,false};
//        System.out.println(ObjectUtils.nullSafeToString(booleans));
//
//
        User[] users = new User[1000000];
        for (int i = 0; i < 1000000; i++) {
            user = new User();
            user.setNumber(i);
            users[i] = user;
        }
        System.out.println(ObjectUtils.nullSafeToString(users));
        System.out.println(users.getClass().isArray());

//        user = null;
//        System.out.println(Optional.ofNullable(user).map(User::getNumber).orElse(0));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println(Optional.ofNullable(user).map(User::getNumber).orElse(0));

        stopWatch.stop();

        System.out.println(stopWatch.getLastTaskTimeMillis());
//        System.out.println(N(5));


//        for (int i = 0;i<100;i++){
//            if(i == 96){
//                System.out.println(i);
//            }
//            continue;
//        }
    }


    public static BigInteger N(int n) {
        if (n == 0 || n == 1) {
            return BigInteger.valueOf(1);
        } else {
            return BigInteger.valueOf(n).multiply(N(n - 1));
        }
    }
}