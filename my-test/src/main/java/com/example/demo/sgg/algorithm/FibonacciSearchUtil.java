package com.example.demo.sgg.algorithm;

import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * @Author yl
 * @Date 2020/1/19 14:27
 * @description:
 */
public class FibonacciSearchUtil {

    private static int maxSize = 20;

    public static void main(String[] args) {
        int[] array = new int[maxSize];
        long startTime = System.nanoTime();
        fibByRecursive(array, 0, maxSize);
        System.out.println("递归方式完成数列所需时间：" + (System.nanoTime() - startTime));
        System.out.println(Arrays.toString(array));
        startTime = System.nanoTime();
        array = fib();
        System.out.println("非递归方式完成数列所需时间：" + (System.nanoTime() - startTime));
        System.out.println(Arrays.toString(array));
        array = new int[]{1, 5, 7, 9, 18, 35};
        fibSearch(array, 7);
    }

    /**
     * 递归获取fibonacci数列
     *
     * @param array 数组
     * @param index 索引值，起始值始终为0
     * @param size  数组长度
     */
    private static void fibByRecursive(int[] array, int index, int size) {
        if (index >= size) {
            return;
        }
        if (index == 0 || index == 1) {
            array[index] = 1;
        } else {
            array[index] = array[index - 1] + array[index - 2];
        }
        index++;
        fibByRecursive(array, index, size);
    }

    /**
     * 非递归方式获取fibonacci数列
     */
    private static int[] fib() {
        int[] array = new int[maxSize];
        for (int index = 0; index < array.length; index++) {
            if (index == 0 || index == 1) {
                array[index] = 1;
            } else {
                array[index] = array[index - 1] + array[index - 2];
            }
        }
        return array;
    }

    /**
     * 斐波拉契查找方法
     *
     * @param array     数列
     * @param findValue 待查找的值
     * @return 返回查找值的位置，-1表示未找到
     */
    private static int fibSearch(int[] array, int findValue) {
        if(array == null || array.length == 0){
            return -1;
        }
        int low = 0;
        int high = array.length - 1;
        /*表示斐波拉契数列数列的分隔点*/
        int fibKey = 0;
        /*存放mid值*/
        int mid ;
        int[] fibArray = fib();
        System.out.println(Arrays.toString(fibArray));
        /*在数组中，起始 index == 0 ,故需对斐波拉契数列中对应的取值进行 -1 操作*/
        /*获取斐波拉契数列数列分割值的下标*/
        while (high > fibArray[fibKey] - 1) {
            fibKey++;
        }
        System.out.println("斐波拉契数列分割点：" + fibKey);

        /*因为 fibArray[fibKey] 值可能大于 array的长度，因此我们需要构造一个新的数组，指向array
         * 不够的补0*/
        int[] temp = Arrays.copyOf(array, fibArray[fibKey]);
        System.out.println("新数组：" + Arrays.toString(temp));
        /*使用array数组最后值填充数组*/
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = temp[high];
        }
        System.out.println("用最后一个值填充后的数组：" + Arrays.toString(temp));

        /*使用while来循环处理，找到我们的数findValue*/
        while (low <= high) {
            mid = low + fibArray[fibKey] - 1;
            /*小于，向左侧继续查找*/
            if (findValue < temp[mid]) {
                high = mid - 1;
                /*说明：
                 * 1、全部元素 = 前面的元素 + 后面的元素
                 * 2、fibArray[fibKey] = fibArray[fibKey-1] + fibArray[fibKey-2]
                 * 3、前面的 fibArray[fibKey-1] 个元素可以继续拆分成 fibArray[fibKey-1] = fibArray[fibKey-2] + fibArray[fibKey-3]
                 *    即在 fibArray[fibKey-1] 的前部继续查找，所以 fibKey--
                 *    即下次循环的 mid = fibArray[fibKey-1-1]-1*/
                fibKey--;
                /*大于，向右侧继续查找*/
            } else if (findValue > temp[mid]) {
                low = mid + 1;
                /*说明：
                 * 1、全部元素 = 前面的元素 + 后面的元素
                 * 2、fibArray[fibKey] = fibArray[fibKey-1] + fibArray[fibKey-2]
                 * 3、因为后面有 fibArray[fibKey-2]，所以我们可以继续拆分 fibArray[fibKey-1] = fibArray[fibKey-3] + fibArray[fibKey-4]
                 *    即在 fibArray[fibKey-2] 的前面进行查找 k-=2
                 *    即下次循环 mid = fibArray[fibKey-1-2]-1*/
                fibKey -= 2;
                /*找到*/
            } else {
                /*确定需要返回的值：返回较小值*/
                if (mid <= high) {
                    return mid;
                } else {
                    /*mid的值已经大于high,进入扩展数组的填充部分,即最后一个数就是要查找的数。*/
                    return high;
                }
            }
        }
        return -1;
    }

}
