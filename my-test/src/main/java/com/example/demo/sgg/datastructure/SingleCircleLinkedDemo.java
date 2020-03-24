package com.example.demo.sgg.datastructure;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author yl
 * @Date 2020/1/7 9:29
 * @description:
 */
public class SingleCircleLinkedDemo {

    public static void main(String[] args) {
        SingleCircleLinked singleCircleLinked = new SingleCircleLinked();
        singleCircleLinked.addTotal(-1);
        singleCircleLinked.addTotal(10000);
        singleCircleLinked.show();
        singleCircleLinked.delete(1,3);
    }
}

@Slf4j
class SingleCircleLinked {

    private final String NAME = "test_";
    private Person first = null;

    /**
     * 根据数量进行链表的添加
     * @param num 需要添加的节点总数
     */
    public void addTotal(int num) {
        if (num < 1) {
            log.info("添加用户的数不能小于1");
            return;
        }
        /*中间指针，用于数据操作使用*/
        Person cur = null;
        /*新节点，用于数据节点添加*/
        Person person;
        /*如果当前链表是空链表*/
        if (first == null) {
            for (int i = 1; i <= num; i++) {
                person = new Person(i, NAME + i, null);
                /*如果是第一个节点，first指向节点本身*/
                if (i == 1) {
                    first = person;
                    first.setNext(person);
                    cur = first;
                    /*如果不是第一个，以cur作为节点转移，进行节点数据的处理*/
                } else {
                    cur.setNext(person);
                    person.setNext(first);
                    cur = person;
                }
            }
            return;
        }
        /*如果当前链表不是空链表*/
        /*找到cur节点,初始化当前节点为首节点*/
        cur = first;
        /*遍历链表，找到链表的末端*/
        while (true) {
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();
        }
        /*循环添加*/
        for (int i = 1; i <= num; i++) {
            person = new Person(i, NAME + i, null);
            cur.setNext(person);
            person.setNext(first);
            /*转移当前节点在尾部*/
            cur = person;
        }
    }

    /**
     * 链表的打印
     */
    public void show() {
        if (first == null) {
            log.info("链表为空");
            return;
        }
        /*中间指针用于数据操作，默认指向第一个节点*/
        Person cur = first;
        /*遍历列表*/
        while (true) {
            System.out.println(cur);
            /*如果当前节点的next指向first,说明环形链表已遍历完毕*/
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();
        }
    }

    /**
     * 从指定开始节点按间隔进行链表节点的删除
     *
     * @param startNo            开始节点
     * @param num                删除间隔
     */
    public void delete(int startNo, int num ) {
        if (first == null) {
            log.info("链表为空");
            return;
        }
        if (startNo < 1) {
            log.info("开始编号不能小于1");
            return;
        }
        if (startNo > this.length()) {
            log.info("开始编号不能大于链表总长度");
            return;
        }
        /*循环遍历，让当前节点指向first的前一个节点*/
        Person cur = first;
        while (true) {
            /*说明已指向first的前一个节点*/
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();
        }
        /*开始前，将first、cur移动到开始节点*/
        for (int i = 0; i < startNo - 1; i++) {
            /*当前节点想前移动一位*/
            cur = cur.getNext();
            /*开始节点向前移动一位*/
            first = first.getNext();
        }
        while (true) {
            if (cur == first) {
                break;
            }
            for (int i = 0; i < num - 1; i++) {
                /*当前节点想前移动一位*/
                cur = cur.getNext();
                /*开始节点向前移动一位*/
                first = first.getNext();
            }
            System.out.printf("编号 = %d,姓名= %s 的人员出局\n",first.getNo(),first.getName());
            first = first.getNext();
            cur.setNext(first);
        }
        System.out.printf("最后剩下的人：编号 = %d,姓名= %s \n",first.getNo(),first.getName());
    }

    /**
     * 获取链表的长度
     */
    public int length() {
        int i = 0;
        if (first == null) {
            return i;
        }
        Person cur = first;
        while (true) {
            i++;
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();
        }
        return i;
    }
}

@Data
class Person {

    private int no;
    private String name;
    private Person next;

    public Person(int no, String name, Person next) {
        this.no = Math.abs(no);
        this.name = name;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Person{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

}
