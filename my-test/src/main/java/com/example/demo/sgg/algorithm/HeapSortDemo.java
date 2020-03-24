package com.example.demo.sgg.algorithm;

import java.util.Arrays;

/**
 * @Author yl
 * @Date 2020/2/18 21:40
 * @description:
 */
public class HeapSortDemo {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        /**
         * 1)、将无序数组构建成一个堆，根据升序降序需求选择大顶堆或小顶堆；
         */
        int k = 1;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
            System.out.printf("%d = %s\n", k++, Arrays.toString(arr));
        }
        /**
         * 2)、将堆顶元素与末尾元素交换，将最大的元素“沉”到数组末端；
         * 3)、重新调整结构，使其满足堆定义，继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
         */
        int temp;
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        System.out.printf("排序完成后的数组：%s\n", Arrays.toString(arr));
    }

    /**
     * 功能：将以i对应的非叶子节点的树调整为大顶堆（明白什么是顺序存储二叉树）
     * 举例：int[] arr = {4, 6, 8, 5, 9} => i=1 => adjustHeap => {4,9,8,5,6}
     * 如果我们再次调用adjustHeap 传入的是 i=0 => {9,6,8,5,4}
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素继续调整，length是在逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        /*取出当前元素的值，保存在临时变量*/
        int temp = arr[i];
        /**
         * k = i * 2 + 1: 表示以i为根节点的左子节点
         */
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            /**
             * k+1: 表示与k节点相对的右子节点
             * arr[k] < arr[k + 1]：左子节点的值小于右子节点的值
             */
            if ((k + 1) < length && arr[k] < arr[k + 1]) {
                /*K指向右子节点*/
                k++;
            }
            if (arr[k] > temp) {// 如果子节点的值大于父节点的值
                arr[i] = arr[k]; // 把较大节点的值赋给当前节点
                i = k;// !!! i指向k,继续循环比较
            } else {
                break;
            }
        }
        /*当for循环结束后，我们已将以i为父节点的树的最大值放到了最顶（局部）*/
        /*将temp的值放到调整后的位置*/
        arr[i] = temp;
    }
}
