package com.example.demo.sgg.datastructure.tree;

/**
 * @Author yl
 * @Date 2020/2/3 10:22
 * @description:
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        System.out.println("前序遍历：");
        arrayBinaryTree.preOrder();
        System.out.println();
        System.out.println("中序遍历：");
        arrayBinaryTree.midOrder();
        System.out.println();
        System.out.println("后序遍历：");
        arrayBinaryTree.postOrder();
        System.out.println();
    }
}

class ArrayBinaryTree {
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    public void midOrder() {
        this.midOrder(0);
    }
    public void postOrder() {
        this.postOrder(0);
    }
    /**
     * 前序遍历数组
     *
     * @param index
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        System.out.printf("%d\t", arr[index]);
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    /**
     * 中序遍历数组
     *
     * @param index
     */
    public void midOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        if ((2 * index + 1) < arr.length) {
            midOrder(2 * index + 1);
        }
        System.out.printf("%d\t", arr[index]);
        if ((2 * index + 2) < arr.length) {
            midOrder(2 * index + 2);
        }
    }

    /**
     * 中序遍历数组
     *
     * @param index
     */
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        if ((2 * index + 1) < arr.length) {
            postOrder(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            postOrder(2 * index + 2);
        }
        System.out.printf("%d\t", arr[index]);
    }
}
