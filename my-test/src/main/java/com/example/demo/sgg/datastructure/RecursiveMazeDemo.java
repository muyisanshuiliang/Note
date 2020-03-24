package com.example.demo.sgg.datastructure;

import org.springframework.util.ObjectUtils;

/**
 * @Author yl
 * @Date 2020/1/9 14:23
 * @description:
 */
public class RecursiveMazeDemo {
    public static void main(String[] args) {
        int[][] mazeMap = initMazeMap(22, 22);
        System.out.println("=================初始化地图====================");
        printMazeMap(mazeMap);
        setWay(mazeMap, 1, 1);
        System.out.println("=================行走后的地图====================");
        printMazeMap(mazeMap);
    }

    /**
     * 约定如下：
     * 1、指定开始点的横纵坐标
     * 2、如果最右下角点的值为2，则说明已找到出口
     * 3、为0，表示该点还没有走过，
     * 4、为1 点表示墙，无法继续前进，前进按照上→右→下→左的顺序进行
     * 4、为2，表示该点已经走过
     * 5、为3，表示该点走过，但是走不通
     *
     * @param mazeMap 迷宫地图
     * @param x       起点横坐标
     * @param x       起点纵坐标
     * @return
     */
    private static boolean setWay(int[][] mazeMap, int x, int y) {
        /*如果出墙的最右下角点的值为2，证明已找到出口*/
        if (mazeMap[mazeMap.length - 2][mazeMap[0].length - 2] == 2) {
            return true;
        }
        if (mazeMap[x][y] == 0) {
            /*假定改点可以走通*/
            mazeMap[x][y] = 2;
            /*按照上→右→下→左的顺序，顺序可以任意更改*/
            if (setWay(mazeMap, x - 1, y)) {
                return true;
            } else if (setWay(mazeMap, x, y + 1)) {
                return true;
            } else if (setWay(mazeMap, x + 1, y)) {
                return true;
            } else if (setWay(mazeMap, x, y - 1)) {
                return true;
            } else {
                /*如果朝四个方向都无法走通，证明该点是死路，置3*/
                mazeMap[x][y] = 3;
            }
            /*每次只能朝上下左右的一个方向行走*/
            return false;
        }
        /*如果该点不为0，说明为1，2，3,表示改点要么走过，要么走不通，要么是墙，故直接返回false*/
        return false;
    }

    /**
     * 根据行、列初始化迷宫
     *
     * @param row 行
     * @param col 列
     * @return
     */
    private static int[][] initMazeMap(int row, int col) {
        int[][] mazeMap = new int[row][col];
        for (int i = 0; i < row; i++) {
            mazeMap[i][0] = 1;
            mazeMap[i][col - 1] = 1;
        }

        for (int i = 1; i < col - 1; i++) {
            mazeMap[0][i] = 1;
            mazeMap[row - 1][i] = 1;
        }

        int numRow = row / 3;
        int numCol = col / 2;
        for (int i = 0; i <= numRow; i++) {
            mazeMap[numCol][i] = 1;
            mazeMap[numRow][col - 1 - i] = 1;
        }
        return mazeMap;
    }

    /**
     * 打印迷宫地图
     *
     * @param mazeMap
     */
    private static void printMazeMap(int[][] mazeMap) {
        if (ObjectUtils.isEmpty(mazeMap)) {
            return;
        }
        int row = mazeMap.length;
        int col;
        for (int i = 0; i < row; i++) {
            col = mazeMap[i].length;
            for (int j = 0; j < col; j++) {
                System.out.print(mazeMap[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
