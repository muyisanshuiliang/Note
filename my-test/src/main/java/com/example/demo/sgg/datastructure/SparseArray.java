package com.example.demo.sgg.datastructure;

import org.springframework.util.ObjectUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @Author yl
 * @Date 2019/12/31 9:23
 * @description: 稀疏数组
 */
public class SparseArray {
    private final static int DEFAULT_VALUE = 0;
    /*初始化数组的行值*/
    private final static int ROW = 11;
    /*初始化数组的列值*/
    private final static int COL = 11;
    /*数组行开始值*/
    private final static int START_ROW = 0;
    /*文件的地址*/
    private final static String FILE_PATH = "/sparseData.txt";

    public static void main(String[] args) {
        /*1、定义初始数组*/
        int[][] array1 = new int[ROW][COL];
        /*2、为初始数组赋值*/
        setArray(array1);
        /*3、打印初始数组*/
        System.out.println("========原始数组=======");
        printArray(array1);
        /*5、统计数组中非默认值的数量*/
        int sum = getNotDefaultNumber(array1);
        /*6、初始化稀疏数组，首行存二维数组的行、列、非默认值的总数
         * 其余行存放行、列及对应坐标的实际值
         * */
        int[][] sparseArray = new int[sum + 1][3];
        /*7、为稀疏数组赋值*/
        setSparseArray(array1, sparseArray, sum);
        /*8、打印稀疏数组*/
        System.out.println("========稀疏数组(入文件前)=======");
        printArray(sparseArray);
        /*9、稀疏数组存入文档*/
        writeToFile(sparseArray);
        /*10、从文档读取稀疏数组*/
        int[][] sparseArray1 = loadFromFile();
        System.out.println("========稀疏数组(从文件读取的稀疏数组)=======");
        printArray(sparseArray1);
        /*11、稀疏数组还原成而为数组*/
        int[][] array2 = new int[ROW][COL];
        reductionArray(array2, sparseArray1);
        /*12、打印还原后的二维数组*/
        System.out.println("========还原后数组=======");
        printArray(array2);
        /*13、判断还原前后数组是否相等*/
        boolean equal = equal(array1, array2);
        if (equal) {
            System.out.println("两个数组相等");
        } else {
            System.out.println("两个数组不相等");
        }
    }

    private static int[][] loadFromFile() {
        FileInputStream inputStream = null;
        Scanner sc = null;
        int[][] array = null;
        List<Integer[]> list = new ArrayList<>();
        try {
            inputStream = new FileInputStream(FILE_PATH);
            sc = new Scanner(inputStream, "utf-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                int s = 0;
                String[] arr = line.split(",");
                Integer[] dArr = new Integer[arr.length];
                for (String ss : arr) {
                    if (ss != null) {
                        dArr[s++] = Integer.valueOf(ss);
                    }
                }
                list.add(dArr);
            }
            int max = 0;
            for (int i = 0; i < list.size(); i++) {
                if (max < list.get(i).length) {
                    max = list.get(i).length;
                }
            }
            array = new int[list.size()][max];
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < list.get(i).length; j++) {
                    array[i][j] = list.get(i)[j];
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (sc != null) {
                sc.close();
            }
        }
        return array;
    }

    private static void writeToFile(int[][] sparseArray) {
        if (ObjectUtils.isEmpty(sparseArray)) {
            return;
        }
        File file = new File(FILE_PATH);  //存放数组数据的文件
        int row = sparseArray.length;
        int col = sparseArray[START_ROW].length;
        FileWriter out = null;  //文件写入流
        try {
            out = new FileWriter(file);
            //将数组中的数据写入到文件中。每行各数据之间逗号间隔
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    out.write(sparseArray[i][j] + ",");
                }
                out.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setArray(int[][] array) {
        Random random = new Random();
        int sum = random.nextInt(COL * ROW);
        for (int i = 0; i < sum; i++) {
            int row = random.nextInt(ROW - 1);
            int col = random.nextInt(COL - 1);
            if (i % 2 == 0) {
                array[row][col] = 2;
            } else {
                array[row][col] = 1;
            }
        }
    }

    private static void printArray(int[][] array) {
        if (ObjectUtils.isEmpty(array)) {
            return;
        }
        int row = array.length;
        int col;
        for (int i = 0; i < row; i++) {
            col = array[i].length;
            for (int j = 0; j < col; j++) {
                System.out.printf("%d\t", array[i][j]);
            }
            System.out.println();
        }
    }

    private static int getNotDefaultNumber(int[][] array) {
        int sum = 0;
        if (ObjectUtils.isEmpty(array)) {
            return sum;
        }
        int row = array.length;
        int col;
        for (int i = 0; i < row; i++) {
            col = array[i].length;
            for (int j = 0; j < col; j++) {
                if (array[i][j] != DEFAULT_VALUE) {
                    sum += 1;
                }
            }
        }
        return sum;
    }

    private static void setSparseArray(int[][] array, int[][] sparseArray, int sum) {
        if (ObjectUtils.isEmpty(array)) {
            return;
        }
        int row = array.length;
        int col = array[START_ROW].length;
        sparseArray[START_ROW][0] = row;
        sparseArray[START_ROW][1] = col;
        sparseArray[START_ROW][2] = sum;
        int count = 1;
        for (int i = 0; i < row; i++) {
            col = array[i].length;
            for (int j = 0; j < col; j++) {
                if (array[i][j] != DEFAULT_VALUE) {
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array[i][j];
                    count++;
                }
            }
        }
    }

    private static void reductionArray(int[][] array, int[][] sparseArray) {
        if (ObjectUtils.isEmpty(array)) {
            return;
        }
        int row = sparseArray.length;
        for (int i = 1; i < row; i++) {
            array[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return;
    }

    private static boolean equal(int[][] array1, int[][] array2) {
        /*1、两个数组都不是null,返回false*/
        if (ObjectUtils.isEmpty(array1) && !ObjectUtils.isEmpty(array2)) {
            return false;
        }
        if (ObjectUtils.isEmpty(array2) && !ObjectUtils.isEmpty(array1)) {
            return false;
        }
        /*2、行不等返回false*/
        int row1 = array1.length;
        int row2 = array2.length;
        if (row1 != row2) {
            return false;
        }
        /*3、列不等返回false*/
        int col1 = array1[START_ROW].length;
        int col2 = array2[START_ROW].length;
        if (col1 != col2) {
            return false;
        }
        /*4、行列对应的数值不相等，返回false*/
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                if (array1[i][j] != array2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}
