package com.example.demo.sgg.datastructure;

import com.irrigation.icl.exception.ContextRuntimeException;

import java.util.Scanner;

/**
 * @Author yl
 * @Date 2019/12/31 11:42
 * @description:
 */
public class OneTimeQueue {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(4);
        /*接收用户输入数据*/
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        Boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("a(add)：添加数据");
            System.out.println("g(get)：获取队列数据");
            System.out.println("h(head)：显示队列头部数据");
            System.out.println("e(exit)：退出程序");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    try {
                        queue.showData();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("请输入一个数字：");
                    int value = scanner.nextInt();
                    try {
                        queue.addQueue(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        Integer data = queue.getQueue();
                        System.out.println("从队列获取的数据为：" + data);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        Integer data = queue.headQueue();
                        System.out.println("队列头部数据为：" + data);
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
        System.out.println("程序退出");
    }
}

class ArrayQueue {
    /**
     * 队列最大值
     */
    private Integer maxSIze;
    /**
     * 指向队列头部前一个位置
     */
    private int front;
    /**
     * 指向队列尾部数据
     */
    private int rear;
    /**
     * 队列数值
     */
    private Integer[] data;

    public ArrayQueue(Integer maxSIze) {
        this.maxSIze = maxSIze;
        this.front = -1;
        this.rear = -1;
        data = new Integer[maxSIze];
    }

    /**
     * 判断队列是否已满
     */
    public Boolean isFull() {
        return rear == maxSIze - 1;
    }

    /**
     * 判断队列是否为空
     */
    public Boolean isEmpty() {
        return rear == front;
    }

    /**
     * 队列添加数据
     *
     * @param number
     */
    public void addQueue(Integer number) {
        if (isFull()) {
            throw new ContextRuntimeException("队列已满，无法添加数据");
        }
        data[++rear] = number;
    }

    /**
     * 从队列获取数据
     */
    public Integer getQueue() {
        if (isEmpty()) {
            throw new ContextRuntimeException("队列为空");
        }
        /*front后移获取数据*/
        Integer datum = data[++front];
        data[front] = null;
        return datum;
    }

    /**
     * 显示队列全部数据
     */
    public void showData() {
        if (isEmpty()) {
            throw new ContextRuntimeException("队列为空");
        }
        for (int i = 0; i < maxSIze; i++) {
            System.out.printf("queue[%d] = %d\t", i, data[i]);
        }
        System.out.println();
    }

    /**
     * 显示队列头部信息
     *
     * @return
     */
    public Integer headQueue() {
        if (isEmpty()) {
            throw new ContextRuntimeException("队列为空");
        }
        return data[front + 1];
    }
}