package com.example.demo.sgg.datastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.Stack;

/**
 * @Author yl
 * @Date 2020/1/3 15:57
 * @description:
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode4 = new HeroNode2(4, "公孙胜", "入云龙");
        DoubleLinkedList doubleList = new DoubleLinkedList();
        System.out.println("添加前的链表信息================");
        doubleList.list();
        doubleList.add(heroNode1);
        doubleList.add(heroNode2);
        doubleList.add(heroNode3);
        doubleList.add(heroNode4);
        System.out.println("添加后的链表信息================");
        doubleList.list();
        HeroNode2 heroNode5 = new HeroNode2(5, "关胜", "大刀");
        HeroNode2 heroNode6 = new HeroNode2(6, "林冲", "豹子头");
        HeroNode2 heroNode7 = new HeroNode2(7, "秦明", "霹雳火");
        HeroNode2 heroNode8 = new HeroNode2(8, "呼延灼", "双鞭将");
        DoubleLinkedList addList = new DoubleLinkedList();
        addList.addByOrder(heroNode6);
        addList.addByOrder(heroNode8);
        addList.addByOrder(heroNode5);
        addList.addByOrder(heroNode7);
//        addList.add(heroNode6);
//        addList.add(heroNode8);
//        addList.add(heroNode5);
//        addList.add(heroNode7);
        System.out.println("待添加的链表信息================");
        addList.list();
        doubleList.addList(addList);
//        doubleList.addListByOrder(addList);
        System.out.println("添加链表后的链表信息================");
        doubleList.list();
        System.out.println("反向打印链表================");
        doubleList.reversePrint();
        System.out.println("反转后的链表================");
        doubleList.reverse();
        doubleList.list();
    }
}

@Slf4j
class DoubleLinkedList {
    /*头节点，不做存储数据使用*/
    private HeroNode2 headNode = new HeroNode2(0, "", "");

    /**
     * 获取指定编号的节点信息
     *
     * @param no
     */
    public HeroNode2 getByNo(int no) {
        if (ObjectUtils.isEmpty(headNode.next)) {
            log.info("链表为空");
            return null;
        }
        HeroNode2 cur = headNode.next;
        /*根据节点编号获取节点信息*/
        while (cur != null) {
            if (cur.no == no) {
                return cur;
            }
        }
        return null;
    }

    /**
     * 遍历链表
     */
    public void list() {
        if (headNode.next == null) {
            log.info("链表信息为空");
        }
        HeroNode2 cur = headNode.next;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }

    /**
     * 添加节点
     *
     * @param addNode
     */
    public void add(HeroNode2 addNode) {
        /*将节点直接加在链表的尾部*/
        if (ObjectUtils.isEmpty(addNode)) {
            log.info("待添加节点为空");
            return;
        }
        HeroNode2 cur = headNode;
        /*遍历链表，直到链表末端*/
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = addNode;
        addNode.pre = cur;
    }

    /**
     * 批量添加链表节点
     *
     * @param addList
     */
    public void addList(DoubleLinkedList addList) {
        if (addList.length() == 0) {
            log.info("待添加的链表为空");
            return;
        }
        /*获取待添加链表的首端元素*/
        HeroNode2 addCur = addList.headNode.next;
        HeroNode2 cur = headNode.next;
        /*找到添加链表的末端元素*/
        while (cur.next != null) {
            cur = cur.next;
        }
        /*将添加链表的末端元素的next指向待添加元素的首端元素*/
        cur.next = addCur;
        /*将待添加元素首端位置的pre域指向添加元素的末端元素*/
        addCur.pre = cur;
    }

    /**
     * 按顺序添加节点
     *
     * @param addNode
     */
    public void addByOrder(HeroNode2 addNode) {
        if (ObjectUtils.isEmpty(addNode)) {
            log.info("待添加的节点内容为空");
            return;
        }
        /*中间变量指向头节点*/
        HeroNode2 temp = headNode;
        /*标志位，添加的数据编号是否存在，默认不存在*/
        boolean flag = false;
        while (true) {
            /*如果中间节点的next域为空，说明已达链表的末端*/
            if (temp.next == null) {
                break;
            }
            /*如果添加的节点的编号和中间节点next域中的编号相同，证明存在数据重复现象*/
            if (temp.next.no == addNode.no) {
                flag = true;
                break;
                /*如果中间节点的next域中的编号大于添加节点的编号，说明已找到节点的添加位置*/
            } else if (temp.next.no > addNode.no) {
                break;
            }
            /*如果以上条件都不存在，说明还未找到添加位置，需将指针下移*/
            temp = temp.next;
        }
        if (flag) {
            log.info("准备插入的英雄编号{}已存在，不能重复添加", addNode.no);
            return;
        }
        /*找到位置之后，添加节点的next域指向中间节点的next域*/
        addNode.next = temp.next;
        /*待添加节点的pre域指向当前节点*/
        addNode.pre = temp;
        /*如不是链表末端，则当前节点next域中的pre指向待添加节点*/
        if (temp.next != null) {
            temp.next.pre = addNode;
        }
        /*当前节点的next域指向待添加节点*/
        temp.next = addNode;
    }

    /**
     * 按顺序添加一个链表
     * @param addNodes
     */
    public void addListByOrder(DoubleLinkedList addNodes) {
        /*循环列表，知道遍历完链表的所有节点为止*/
        HeroNode2 cur = addNodes.headNode.next;
        HeroNode2 temp ;
        while (cur != null) {
            /*中间传递值，用于进行添加操作*/
            temp = cur;
            /*按顺序添加节点*/
            addByOrder(temp);
            /*节点位置下移*/
            cur = cur.next;
        }
    }

    /**
     * 更新链表信息
     *
     * @param updateNode
     */
    public void update(HeroNode2 updateNode) {
        /*根据节点编号进行数据更新*/
        if (ObjectUtils.isEmpty(headNode)) {
            log.info("待添加节点为空");
            return;
        }
        /*如果链表为空，则无可更新的数据信息*/
        if (headNode.next == null) {
            log.info("链表为空");
        }
        int updateNo = updateNode.no;
        /*标志位，用于标志链表中是否包含待更新的数据信息*/
        boolean flag = false;
        HeroNode2 cur = headNode.next;
        while (cur != null) {
            if (cur.no == updateNo) {
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (!flag) {
            log.error("未找到待更新的节点");
        }
        /*更新节点的姓名、昵称信息*/
        cur.name = updateNode.name;
        cur.nickName = updateNode.nickName;
    }

    /**
     * 根据数据编号删除链表信息
     *
     * @param no
     */
    public void deleteByNo(int no) {
        /*如果链表为空，则无可更新的数据信息*/
        if (headNode.next == null) {
            log.info("链表为空");
            return;
        }
        /*标志位，用于标志链表中是否包含待删除的数据信息*/
        boolean flag = false;
        HeroNode2 cur = headNode.next;
        while (cur != null) {
            if (cur.no == no) {
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (!flag) {
            log.error("未找到待删除的节点");
            return;
        }
        /*则修改当前节点的pre域内节点的next域指向当前节点的next域,当前节点的next域可能为null,也可能有值*/
        cur.pre.next = cur.next;
        /*如果当前节点的next域存在，则直接将当前节点next域内节点的pre域指向当前节点的pre*/
        if (cur.next != null) {
            cur.next.pre = cur.pre;
        }
    }

    /**
     * 获取链表长度
     *
     * @return
     */
    public int length() {
        int i = 0;
        HeroNode2 cur = headNode.next;
        /*当前节点不为空，则链表长度+1*/
        while (cur != null) {
            i++;
            /*每循环一次，指针下移一个位置*/
            cur = cur.next;
        }
        return i;
    }

    /**
     * 反转链表（改变数据结果）
     */
    public void reverse(){
        /*链表的反转方法,改变链表的数据结构*/
        /*如果链表为空或链表只有一个节点直接返回，反转后即是本身*/
        if (headNode.next == null || headNode.next.next == null) {
            return;
        }
        /*定义一个中间头节点*/
        HeroNode2 tempHead = new HeroNode2(0, "", "");
        /*定义该节点指向当前节点的下一个节点，中间存储*/
        HeroNode2 next = null;
        /*当前节点*/
        HeroNode2 cur = headNode.next;
        while (cur != null) {
            /*获取当前节点的下一个节点,保存节点便于下一次循环*/
            next = cur.next;
            /*如果头节点的next域不为空，那么next域中值的pre域指向当前节点*/
            if(tempHead.next != null){
                tempHead.next.pre = cur;
            }
            /*当前节点的next域指向中间头节点的next域*/
            cur.next = tempHead.next;
            /*当前节点的pre指向头节点*/
            cur.pre = tempHead;
            /*头节点的next指向当前节点，完成当前节点的插入操作*/
            tempHead.next = cur;
            /*cur指向下一个节点，进行下一次循环*/
            cur = next;
        }
        /*原链表的头节点的next域指向中间节点的next域，改变原链表的数据结构*/
        headNode.next = tempHead.next;
        /*中间头节点的pre域指向中间节点的头节点，改变原链表的数据结构*/
        tempHead.pre = headNode;
    }

    /**
     * 反转链表操作（不改变数据结构）
     */
    public void reversePrint(){
        if(headNode.next == null){
            log.info("链表为空");
            return;
        }
        Stack<HeroNode2> stack = new Stack<>();
        HeroNode2 cur = headNode.next;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向链表节点的后一个节点
    public HeroNode2 pre;//指向链表节点的前一个节点

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }
}

