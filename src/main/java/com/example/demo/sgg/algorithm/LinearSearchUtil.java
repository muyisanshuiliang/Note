package com.example.demo.sgg.algorithm;

import com.google.common.collect.Lists;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Author yl
 * @Date 2020/1/17 16:05
 * @description:
 */
public class LinearSearchUtil {
    public static void main(String[] args) {
        int[] array = {1, 5, 8, 3, 8, 9};
//        int value = 10;
        int value = 8;
        int index = linearSearchFirstValue(array, value);
        System.out.printf("第一个 %d 的位置是：%d\n", value, index);
        index = linearSearchLastValue(array, value);
        System.out.printf("最后一个 %d 的位置是：%d\n", value, index);
        System.out.printf("所有 %d 的位置是：%s\n", value, linearSearchAllValue(array, value).toString());
    }

    /**
     * 获取查找到的第一个元素的位置
     *
     * @param array 数组
     * @param value 要查找的值
     * @return 查找到，返回index,未查找到返回-1
     */
    private static int linearSearchFirstValue(int[] array, int value) {
        if (ObjectUtils.isEmpty(array)) {
            throw new RuntimeException("数组为空");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取查找到的最后一个元素的位置
     *
     * @param array 数组
     * @param value 要查找的值
     * @return 查找到，返回index,未查找到返回-1
     */
    private static int linearSearchLastValue(int[] array, int value) {
        if (ObjectUtils.isEmpty(array)) {
            throw new RuntimeException("数组为空");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取所有元素的值
     *
     * @param array 数组
     * @param value 要查找的值
     * @return 返回元素的所有位置，空数组表示为找到相关位置
     */
    private static List<Integer> linearSearchAllValue(int[] array, int value) {
        if (ObjectUtils.isEmpty(array)) {
            throw new RuntimeException("数组为空");
        }
        List<Integer> indexes = Lists.newArrayList();
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == value) {
                indexes.add(i);
            }
        }
        return indexes;
    }
}
