package com.example.demo.sgg.datastructure;

import lombok.Data;

import java.util.List;

/**
 * @Author yl
 * @Date 2020/1/19 16:49
 * @description:
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        Student student = new Student(5, "张三", "男", "四川省成都市高新区");
        hashTable.add(student);
        student = new Student(7, "李四", "男", "四川省成都市高新区");
        hashTable.add(student);
        student = new Student(21, "王五", "男", "四川省成都市高新区");
        hashTable.add(student);
        student = new Student(37, "王五", "男", "四川省成都市高新区");
        hashTable.add(student);
        System.out.println("删除前的hashTable:");
        hashTable.show();
//        System.out.println(hashTable.findById(33));
        System.out.println("删除后的hashTable:");
        hashTable.removeById(21);
        hashTable.show();
    }
}

class HashTable {
    private StudentLinkedList[] studentLinkedListArray;
    private int size = 16;

    public HashTable() {
        studentLinkedListArray = new StudentLinkedList[size];
        innitHashTable(studentLinkedListArray);
    }

    public HashTable(int size) {
        this.size = size;
        studentLinkedListArray = new StudentLinkedList[size];
        innitHashTable(studentLinkedListArray);
    }

    public void add(Student student) {
        if (student == null) {
            return;
        }
        studentLinkedListArray[hashFun(student.getId())].add(student);
    }

    public Student findById(int no) {
        return studentLinkedListArray[hashFun(no)].findById(no);
    }

    public void removeById(int no) {
        studentLinkedListArray[hashFun(no)].remove(no);
    }

    /**
     * 初始化hashTable链表的数值的第一个元素(head节点)，不然会报NPE
     *
     * @param studentLinkedListArray
     */
    private void innitHashTable(StudentLinkedList[] studentLinkedListArray) {
        if (studentLinkedListArray == null || studentLinkedListArray.length == 0) {
            return;
        }
        for (int i = 0; i < studentLinkedListArray.length; i++) {
            studentLinkedListArray[i] = new StudentLinkedList();
        }
    }

    public void show() {
        System.out.println("链表下的元素为：");
        for (int i = 0; i < size; i++) {
            System.out.printf("第 %d 条链表 =>:", i);
            studentLinkedListArray[i].show();
        }
    }

    /**
     * 获取散列数值
     *
     * @param id
     * @return
     */
    private int hashFun(int id) {
        return id % size;
    }
}

/**
 * 学生信息类
 */
@Data
class Student {
    private int id;
    private String name;
    private String sex;
    private String address;
    private Student next;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Student(int id, String name, String sex, String address) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.address = address;
    }
}

/**
 * 学生链表类
 */
class StudentLinkedList {

    /*头节点*/
    private Student head;

    /**
     * 添加节点：整个过程默认将节点添加到链表的最后
     *
     * @param student
     */
    public void add(Student student) {
        if (student == null) {
            return;
        }
        /*如果链表为空，直接将节点添加到链表的头节点即可*/
        if (isEmpty()) {
            head = student;
            return;
        }
        Student temp = head;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
        temp.setNext(student);
    }

    /**
     * 显示链表下的元素
     */
    public void show() {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        Student temp = head;
        while (true) {
            System.out.println(temp);
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * 根据编号获取链表节点信息
     *
     * @param no
     * @return
     */
    public Student findById(int no) {
        if (isEmpty()) {
            System.out.println("当前链表为空");
            return null;
        }
        Student temp = head;
        while (true) {
            if (temp.getId() == no) {
                break;
            }
            if (temp.getNext() == null) {
                temp = null;
                break;
            }
            temp = temp.getNext();
        }
        return temp;
    }

    /**
     * 根据编号删除编号对应节点
     *
     * @param id
     */
    public void remove(int id) {
        if (isEmpty()) {
            return;
        }
        /*如果头节点即为待删除的节点，直接修改头节点即可*/
        if (head.getId() == id) {
            head = head.getNext();
            return;
        }
        Student temp = head;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.getNext().getId() == id) {
                temp.setNext(temp.getNext().getNext());
                break;
            }
            temp = temp.getNext();
        }

    }

}
