package com.example.demo.sgg.datastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.Stack;

/**
 * @Author yl
 * @Date 2020/1/2 13:29
 * @description:
 */

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "公孙胜", "入云龙");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        /*普通添加*/
//        singleLinkedList.add(heroNode1);
//        singleLinkedList.add(heroNode3);
//        singleLinkedList.add(heroNode4);
//        singleLinkedList.add(heroNode2);

        /*按顺序添加*/
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode2);
        /*重复添加*/
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode4);
        /*打印链表*/
        System.out.println("添加链表前==========");
        singleLinkedList.list();

        HeroNode heroNode5 = new HeroNode(5, "关胜", "大刀");
        HeroNode heroNode6 = new HeroNode(6, "林冲", "豹子头");
        HeroNode heroNode7 = new HeroNode(7, "秦明", "霹雳火");
        HeroNode heroNode8 = new HeroNode(8, "呼延灼", "双鞭将");
        SingleLinkedList addList = new SingleLinkedList();
        addList.add(heroNode6);
        addList.add(heroNode5);
        addList.add(heroNode8);
        addList.add(heroNode7);
        System.out.println("带添加的链表==========");
        addList.list();
        /*测试不带顺序添加链表*/
//        singleLinkedList.addList(addList);
        /*测试带顺序添加链表*/
        singleLinkedList.addListByOrder(addList);
        System.out.println("添加链表后==========");
        singleLinkedList.list();
        /*测试反转*/
        System.out.println("反转后的链表============");
        /*反转链表，链表的结构发生了该表*/
        singleLinkedList.reverse();
        singleLinkedList.list();
        System.out.println("反转打印链表===============");
        singleLinkedList.reversePrint();
        System.out.println("链表的有效数据长度：" + singleLinkedList.length());
        heroNode4 = new HeroNode(4, "林冲11", "豹子头11");
        singleLinkedList.update(heroNode4);
        System.out.println("修改后的链表：");
        singleLinkedList.list();
        singleLinkedList.delete(heroNode4);
        singleLinkedList.delete(1);
        singleLinkedList.delete("1111");
        singleLinkedList.delete(234.56);
        System.out.println("删除后的链表：");
        singleLinkedList.list();
        System.out.println("链表的有效数据长度：" + singleLinkedList.length());
        /*0的时候返回倒数第一个节点*/
        HeroNode heroNode = singleLinkedList.inDexNode(0);
        System.out.println("倒数第一个节点：" + heroNode);
        heroNode = singleLinkedList.inDexNode(1);
        System.out.println("第一个节点：" + heroNode);
    }
}

// 定义SingleLinkedList管理我们的链表
@Slf4j
class SingleLinkedList {

    /*定义头节点，不存放任何数据*/
    private HeroNode headNode = new HeroNode(0, "", "");

    /*添加节点是单向链表，不考虑顺序*/
    /*1、找到当前链表的最后节点
     * 2、将最后这个节点的next指向新的节点即可*/
    public void add(HeroNode heroNode) {
        if (ObjectUtils.isEmpty(heroNode)) {
            log.info("待添加的节点内容为空");
            return;
        }
        /*中间变量指向头节点*/
        HeroNode temp = headNode;
        /*遍历，直到获取到尾部元素*/
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    public void addList(SingleLinkedList addList){
        /*将一个链表假如当前链表，不考虑排序问题*/
        /*如果待添加链表为空，则直接返回*/
        if(addList.headNode.next == null){
            return;
        }
        /*如果当前链表为空，直接将当前链表的头节点next域直接指向待添加链表头节点的next域即可*/
        if(headNode.next == null){
            headNode.next = addList.headNode.next;
            return;
        }
        /*如果当前链表不为空，则对当前链表进行循环，直到找到当前链表的末端*/
        HeroNode cur = headNode.next;
        while (cur.next != null){
            cur = cur.next;
        }
        cur.next = addList.headNode.next;
    }

    public void addByOrder(HeroNode heroNode) {
        if (ObjectUtils.isEmpty(heroNode)) {
            log.info("待添加的节点内容为空");
            return;
        }
        /*中间变量指向头节点*/
        HeroNode temp = headNode;
        /*标志位，添加的数据编号是否存在，默认不存在*/
        boolean flag = false;
        while (true) {
            /*如果中间节点的next域为空，说明已达链表的末端*/
            if (temp.next == null) {
                break;
            }
            /*如果添加的节点的编号和中间节点next域中的编号相同，证明存在数据重复现象*/
            if (temp.next.no == heroNode.no) {
                flag = true;
                break;
                /*如果中间节点的next域中的编号大于添加节点的编号，说明已找到节点的添加位置*/
            } else if (temp.next.no > heroNode.no) {
                break;
            }
            /*如果以上条件都不存在，说明还未找到添加位置，需将指针下移*/
            temp = temp.next;
        }
        if (flag) {
            log.info("准备插入的英雄编号{}已存在，不能重复添加", heroNode.no);
            return;
        }
        /*找到位置之后，添加节点的next域指向中间节点的next域，中间节点的next域指向添加节点即可*/
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    public void addListByOrder(SingleLinkedList addList){
        /*将一个链表有序添加入当前链表*/
        HeroNode addHeadNode = addList.headNode;
        /*如果待添加的链表数据为空，直接返回*/
        if(addHeadNode.next == null){
            return;
        }
        HeroNode addCur = addHeadNode.next;
        /*定义中间变量节点*/
        HeroNode next ;
        /*依次循环待添加链表*/
        while (addCur != null){
            /*中间变量指向头节点*/
            HeroNode temp = headNode;
            /*标志位，添加的数据编号是否存在，默认不存在*/
            boolean flag = false;
            while (true) {
                /*如果中间节点的next域为空，说明已达链表的末端*/
                if (temp.next == null) {
                    break;
                }
                /*如果添加的节点的编号和中间节点next域中的编号相同，证明存在数据重复现象*/
                if (temp.next.no == addCur.no) {
                    flag = true;
                    break;
                    /*如果中间节点的next域中的编号大于添加节点的编号，说明已找到节点的添加位置*/
                } else if (temp.next.no > addCur.no) {
                    break;
                }
                /*如果以上条件都不存在，说明还未找到添加位置，需将指针下移*/
                temp = temp.next;
            }
            if (flag) {
                log.info("准备插入的英雄编号{}已存在，不能重复添加", addCur.no);
                return;
            }
            /*获取当前变量的next域*/
            next = addCur.next;
            /*将当前节点next域指向待插入位置中间节点的next域*/
            addCur.next = temp.next;
            /*待插入位置中间节点的next域指向待添加节点*/
            temp.next = addCur;
            /*待添加节点后移*/
            addCur = next;
        }
    }

    public void update(HeroNode newHeroNode) {
        if (ObjectUtils.isEmpty(newHeroNode)) {
            log.info("待更新的节点内容为空");
            return;
        }
        HeroNode temp = headNode;
        /*用于标志待修改节点是否找到*/
        boolean flag = false;
        while (true) {
            /*找到链表末端*/
            if (temp == null) {
                break;
            }
            /*找到编号相同*/
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            /*指针下移*/
            temp = temp.next;
        }
        /*如果节点找到就进行节点内容修改，不能进行no修改，如果修改的话，类似于添加*/
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            log.info("未找到编号{}的节点，不能进行修改。", newHeroNode.no);
        }
    }

    public void delete(Object object) {
        if (ObjectUtils.isEmpty(object)) {
            log.info("待删除的节点内容为空");
            return;
        }
        /*这里为了删除函数操作方便，做了类型判断处理，删除参数可以传递删除节点编号，也可以传递删除节点对象*/
        int no = 0;
        if (Integer.class.isInstance(object)) {
            no = (Integer) object;
        } else if (HeroNode.class.isInstance(object)) {
            no = ((HeroNode) object).no;
        }
        if (no == 0) {
            log.info("待删除节点{}不存在", no);
            return;
        }
        HeroNode temp = headNode;
        /*标志是否找到待删除的链表节点*/
        boolean flag = false;
        while (true) {
            /*找到链表末端*/
            if (temp.next == null) {
                break;
            }
            /*找到待删除的链表节点的前一个节点，如果找到节点本身无法进行删除操作*/
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            /*每循环一次，指针下移一次*/
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            log.info("未找到编号{}节点，无法进行删除。", no);
        }
    }

    public void list() {
        /*中间变量指向头节点的下一个节点*/
        HeroNode temp = headNode.next;
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        /*遍历，直到获取到尾部元素*/
        while (true) {
            if (temp == null) {
                break;
            }
            /*如果节点不为空，就打印出当前节点*/
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public int length() {
        /*获取链表的有效数据长度，带头节点的链表，头节点不计算在内*/
        HeroNode temp = headNode.next;
        int length = 0;
        while (temp != null) {
            length++;
            /*如果中间节点不为空，链表长度+1，且指针下移，直到获取到链表的末端数据*/
            temp = temp.next;
        }
        return length;
    }

    public HeroNode inDexNode(int index) {
        /*获取链表指定位置的节点信息，如果是负数就获取链表倒数指定位置的节点信息*/
        /*标志位，用于获取是正向位置节点信息还是你想位置节点信息*/
        boolean flag = true;
        if (index <= 0) {
            flag = false;
        }
        index = Math.abs(index);
        /*获取链表有效信息长度*/
        int length = length();
        if (index > length) {
            log.error("超出链表有效信息长度");
            return null;
        }
        HeroNode temp = headNode;
        if (flag) {
            /*获取链表正向节点数据*/
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            /*获取链表逆向节点数据*/
        } else {
            for (int i = 0; i < length - index; i++) {
                temp = temp.next;
            }
        }
        if (temp == headNode) {
            log.info("未找到指定位置节点");
            return null;
        }
        return temp;
    }

    public void reverse() {
        /*链表的反转方法,改变链表的数据结构*/
        /*如果链表为空或链表只有一个节点直接返回，反转后即是本身*/
        if (headNode.next == null || headNode.next.next == null) {
            return;
        }
        /*定义一个中间头节点*/
        HeroNode tempHead = new HeroNode(0, "", "");
        /*定义该节点指向当前节点的下一个节点，中间存储*/
        HeroNode next = null;
        /*当前节点*/
        HeroNode cur = headNode.next;
        while (cur != null) {
            /*获取当前节点的下一个节点*/
            next = cur.next;
            /*让当前节点的下一个节点指向中间头的下一个节点*/
            cur.next = tempHead.next;
            /*中間頭的下一个节点指向当前节点*/
            tempHead.next = cur;
            /*当前节点返回原链表，并将指针下移一位，继续遍历原链表数据*/
            cur = next;
        }
        /*原链表的头节点的next域指向中间节点的next域，改变原链表的数据结构*/
        headNode.next = tempHead.next;
    }

    public void reversePrint() {
        /*不改变链表结构，借助栈进行数据处理*/
        if (headNode.next == null) {
            log.info("链表数据为空");
            return;
        }
        Stack<HeroNode> heroNodes = new Stack<>();
        HeroNode cur = headNode.next;
        while (cur != null) {
            heroNodes.push(cur);
            cur = cur.next;
        }
        while (!heroNodes.isEmpty()){
            System.out.println(heroNodes.pop());
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }
}
