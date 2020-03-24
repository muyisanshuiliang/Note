package com.example.demo.sgg;

import com.google.common.collect.Lists;
import com.irrigation.icl.utils.ObjectUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Stack;

/**
 * @Author yl
 * @Date 2019/12/30 17:23
 * @description:
 */
public class MyTest {

    public static void main(String[] args) {
//        List<String> testList = Lists.newArrayList("1", "2", "3", "4", "5", "a", "b", "c", "d", "e");
//        System.out.println("原列表：" + testList);
//        reverseList1(testList);
//        reverseList(testList);
//        System.out.println("反转后的列表：" + testList);
//        String str = "123456 345";
//        str = strReverseWithRecursive(str);
//        System.out.println(str);
//
//        int tempInt = Integer.parseInt("156",16);
//        byte tempByte = (byte) tempInt;
//        System.out.println(tempByte);

//        System.out.println(-78 %10);

        double a = 5.36;
        double b = 2.15648;
        System.out.println(getSum(null));
        List<BigDecimal> bigDecimals = Lists.newArrayList();
        System.out.println(getSum(bigDecimals));
        bigDecimals.add(new BigDecimal(Double.toString(a)));
        bigDecimals.add(new BigDecimal(Double.toString(b)));
        bigDecimals.add(new BigDecimal(Double.toString(6.253)));
        System.out.println(getSum(bigDecimals));


        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        instance.set(year,0,1,0,0,0);
        Date time = instance.getTime();
        System.out.println(time);
        long timeInMillis = instance.getTimeInMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(0L);
        System.out.println("格式化出来的时间："+format);

    }

    public static BigDecimal getSum(List<BigDecimal> values) {
        if (ObjectUtils.isEmpty(values)) {
            return BigDecimal.ZERO;
        }
        BigDecimal reduce = values.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return reduce;
    }

    /**
     * 列表翻转
     *
     * @param list
     */
    private static void reverseList(List<String> list) {
        if (list == null || list.size() == 0 || list.size() == 1) {
            return;
        }
        int size = list.size();
        /*交换列表中的数据*/
        String temp;
        for (int i = 0, mid = size / 2; i < mid; i++) {
            temp = list.get(size - 1 - i);
            list.set(size - 1 - i, list.get(i));
            list.set(i, temp);
        }
    }

    private static void reverseList1(List<String> list) {
        if (list == null || list.size() == 0 || list.size() == 1) {
            return;
        }
        /*利用栈作为中间存储*/
        Stack<String> strings = new Stack<>();
        for (String item : list) {
            strings.push(item);
        }
        int i = 0;
        while (strings.size() > 0) {
            list.set(i++, strings.pop());
        }
    }

    /**
     * 字符串翻转
     *
     * @param str
     */
    public static String strReverseWithRecursive(String str) {
        if (str == null || str.length() == 0 || str.length() == 1) {
            return str;
        }
        return strReverseWithRecursive(str.substring(1)) + str.charAt(0);
    }

}
