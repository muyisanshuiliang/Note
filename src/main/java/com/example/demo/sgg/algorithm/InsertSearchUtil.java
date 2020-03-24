package com.example.demo.sgg.algorithm;

/**
 * @Author yl
 * @Date 2020/1/19 11:47
 * @description:
 */
public class InsertSearchUtil {
    public static void main(String[] args) {

        int[] array = {1, 5, 8, 8, 9};
//        int value = 10;
        int value = 8;
        int index = insertSearchValue(array, 0, array.length-1, value);
        System.out.printf("第一个 %d 的位置是：%d\n", value, index);
    }

    /**
     * 插值查找法获取查找元素
     *
     * @param array     数组（必须有序）
     * @param left      起始位置
     * @param right     结束位置
     * @param findValue 待查找的值
     */
    public static int insertSearchValue(int[] array, int left, int right, int findValue) {
        /* 1、left > right：已对整个数组进行扫描，未找到指定值
         * 2、findValue < array[0]：小于最小值，说明数组中不存在待查找值
         * 3、findValue > array[array.length - 1]：大于最大值，说明数组中不存在待查找值*/
        if (left > right || findValue < array[0] || findValue > array[array.length - 1]) {
            return -1;
        }
        /*获取中间值公式*/
        int mid = left + (right - left) * (findValue - array[left]) / (array[right] - array[left]);
        int midValue = array[mid];
        if (findValue < midValue) {
            /*待查找值小于中间值，向左递归*/
            return insertSearchValue(array, left, mid - 1, findValue);
        } else if (findValue > midValue) {
            /*待查找值大于中间值，向右递归*/
            return insertSearchValue(array, mid + 1, right, findValue);
        } else {
            return mid;
        }
    }
}
