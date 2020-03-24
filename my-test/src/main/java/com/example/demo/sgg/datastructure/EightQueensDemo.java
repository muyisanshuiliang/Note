package com.example.demo.sgg.datastructure;

/**
 * @Author yl
 * @Date 2020/1/9 15:57
 * @description:
 */
public class EightQueensDemo {

    /**
     * 定义一个max，表示一共有多少个皇后
     */
    private final static int Max = 8;

    /**
     * 定义一个数组array，保存皇后放置位置的结果。下标 +1 表示皇后所在的行，值代表皇后所在的列
     * eg:array ={0,4,7,5,2,6,1,3}
     * array[1] = 4: 表示第 2 个皇后在第 2 行第 4 列（下标是从0 开始）
     * array[3] = 5: 表示第 4 个皇后在第 4 行第 5 列
     */
    int[] array = new int[Max];

    /**
     * 统计共有多少种解答方法
     */
    private static int count = 0;

    /**
     * 统计共回溯多少次
     */
    private static int countJude = 0;

    public static void main(String[] args) {
        EightQueensDemo eightQueensDemo = new EightQueensDemo();
        eightQueensDemo.check(0);
        System.out.printf("一共有 %d 种解法\n",count);
        System.out.printf("一共判断 %d 次\n",countJude);
    }

    /**
     * 皇后放置方法
     *
     * @param n 放置第n个皇后，n从0开始
     */
    private void check(int n) {
        /*如果n == 8,说明已经在开始放置第9个皇后，证明前面8个皇后已放置完毕*/
        /*故皇后放置结束，打印结果*/
        if (n == Max) {
            printQueue();
            return;
        }
        /*从0开始依次将皇后放置在第n行的1-8列*/
        for (int i = 0; i < Max; i++) {
            array[n] = i;
            /*判断所放位置的皇后与前面已经放好的皇后是否存在冲突*/
            if (judge(n)) {
                /*如果不存在，就开始放置下一个皇后*/
                check(n + 1);
            }
            /*如果存在，则将第n个皇后的列位置下移一次，直到皇后位置放置正确*/
        }
    }

    /**
     * 当我们放置第 n 个皇后的时候，判断是否与前面的 n-1 个皇后的位置是否发生冲突
     * （任两个皇后不能出现在同一列、一行以及斜线上）
     *
     * @param n 第 n个皇后，n从0开始
     * @return
     */
    private boolean judge(int n) {
        countJude++;
        for (int i = 0; i < n; i++) {
            /**
             * array[i] == array[n]: 表示第n个皇后所处的位置与前面第i个皇后处于同一列
             * Math.abs(n - i) == Math.abs(array[n] - array[i])：判断两个皇后是否处于同一斜线上
             * 数组内下标不可能相同，故毋需进行是否处于同一行进行判断
             */
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印数组
     */
    private void printQueue() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
        System.out.println();
        count++;
    }
}
