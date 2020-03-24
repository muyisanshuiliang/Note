package com.example.demo.sgg.datastructure;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @Author yl
 * @Date 2020/1/7 13:34
 * @description:
 */
public class StackByArrayDemo {
    public static void main(String[] args) {
        StackByArray stackByArray = new StackByArray(5);
        char key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示栈内所有元");
            System.out.println("e(exit)：退出程序");
            System.out.println("p(push)：入栈");
            System.out.println("q(pop)：出栈");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    stackByArray.show();
                    break;
                case 'p':
                    System.out.println("请输入一个数字：");
                    int value = scanner.nextInt();
                    try {
                        stackByArray.push(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'q':
                    try {
                        int temp = stackByArray.pop();
                        System.out.println("从栈中获取的数据为：" + temp);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    if (scanner != null) {
                        scanner.close();
                    }
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

@Slf4j
class StackByArray {
    /*表示栈的最大深度*/
    private int maxSize;
    /*数组用于实现栈内数据存储*/
    private int[] array;
    /*栈顶标识，默认值-1*/
    private int top = -1;

    /**
     * 构造函数
     * @param maxSize 栈的最大深度
     */
    public StackByArray(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 判断栈是否已满
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 将数据压入栈
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈已满，无法进行元素添加");
        }
        /*每存入一个数据，栈顶标识会上移一次*/
        array[++top] = value;
    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无元素");
        }
        /*每取出一个数据，栈顶会下移一次*/
        return array[top--];
    }

    /**
     * 显示栈内所有元素，类似于peek
     */
    public void show() {
        if (isEmpty()) {
            log.info("栈为空，无元素");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("array[%d] = %d \n", i, array[i]);
        }
    }
}

