package com.example.demo.sgg.algorithm;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Author yl
 * @Date 2020/1/19 10:44
 * @description:
 */
public class BinarySearchUtil {
    public static void main(String[] args) {

        int[] array = {1, 5, 8, 8, 8, 9};
//        int findValue = 10;
        int findValue = 8;
        int index = binarySearchFirstValue(array, 0, array.length, findValue);
        System.out.printf("第一个 %d 的位置是：%d\n", findValue, index);
//        index = binarySearchFirstValue(array, value);
//        System.out.printf("最后一个 %d 的位置是：%d\n", value, index);
        System.out.printf("所有 %d 的位置是：%s\n", findValue, binarySearchAllValue(array, 0, array.length, findValue).toString());
    }

    /**
     * 二分查找获取数组内第一个或最后一个指定元素的位置
     *
     * @param array     有序数组，如果无序，需对数组先进行排序
     * @param left      左起始值
     * @param right     右结束值
     * @param findValue 要查找的值
     * @return 找到返回指定元素的index, 未找到，返回-1
     */
    private static int binarySearchFirstValue(int[] array, int left, int right, int findValue) {
        /*当left值大于right值时，说明遍历整个数组，未找到指定值*/
        if (left > right) {
            return -1;
        }
        int mid = (0 + array.length) / 2;
        /*如果中间值大于要查找的值，需要向左递归查找*/
        if (array[mid] > findValue) {
            return binarySearchFirstValue(array, 0, mid - 1, findValue);
            /*如果中间值小于要查找的值，需要向右递归查找*/
        } else if (array[mid] < findValue) {
            return binarySearchFirstValue(array, mid + 1, right, findValue);
        } else {
            /*如果不是需要查找第一个元素的位置，直接返回当前mid即可*/
            return mid;
            /*如果是查找第一个元素的位置，向左检索，看是否有元素与查找值相等，直到不等，返回mid即可*/
//            for (int i = mid - 1; i >= 0; i--) {
//                if (array[i] == findValue) {
//                    mid = i;
//                } else {
//                    break;
//                }
//            }
//            return mid;

            /*如果是查找最后一个元素的位置，向右检索，看是否有元素与查找值相等，直到不等，返回mid即可*/
//            for (int i = mid + 1; i < array.length; i++) {
//                if (array[i] == findValue) {
//                    mid = i;
//                } else {
//                    break;
//                }
//            }
//            return mid;
        }
    }

    /**
     * 查找数组内所有指定元素的值
     *
     * @param array     数组
     * @param left      起始位置
     * @param right     结束位置
     * @param findValue 待查找值
     * @return 全部元素索引集合
     */
    private static List<Integer> binarySearchAllValue(int[] array, int left, int right, int findValue) {
        /*当left值大于right值时，说明遍历整个数组，未找到指定值*/
        if (left > right) {
            return Lists.newArrayList();
        }
        int mid = (0 + array.length) / 2;
        /*如果中间值大于要查找的值，需要向左递归查找*/
        if (array[mid] > findValue) {
            return binarySearchAllValue(array, 0, mid - 1, findValue);
            /*如果中间值小于要查找的值，需要向右递归查找*/
        } else if (array[mid] < findValue) {
            return binarySearchAllValue(array, mid + 1, right, findValue);
        } else {
            List<Integer> indexes = Lists.newArrayList();
            /*向左循环，将满足条件值的index添加到列表*/
            for (int i = mid - 1; i >= 0; i--) {
                if (array[i] == findValue) {
                    indexes.add(i);
                } else {
                    break;
                }
            }
            /*将中值添加到列表*/
            indexes.add(mid);
            /*向右循环，将满足条件值的index添加到列表*/
            for (int i = mid + 1; i < array.length; i++) {
                if (array[i] == findValue) {
                    indexes.add(i);
                } else {
                    break;
                }
            }
            return indexes;
        }
    }
}
