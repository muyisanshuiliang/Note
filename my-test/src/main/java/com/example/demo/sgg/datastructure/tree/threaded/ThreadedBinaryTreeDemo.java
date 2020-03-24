package com.example.demo.sgg.datastructure.tree.threaded;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @Author yl
 * @Date 2020/2/4 9:14
 * @description:
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "张三1号");
        HeroNode node3 = new HeroNode(3, "张三3号");
        HeroNode node6 = new HeroNode(6, "张三6号");
        HeroNode node8 = new HeroNode(8, "张三8号");
        HeroNode node10 = new HeroNode(10, "张三10号");
        HeroNode node14 = new HeroNode(14, "张三14号");

        /*构件树*/
        node1.setLeft(node3);
        node1.setRight(node6);
        node3.setLeft(node8);
        node3.setRight(node10);
        node6.setLeft(node14);
//        System.out.println(node6.getParent());

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(node1);
//        threadedBinaryTree.preOrder();
//        threadedBinaryTree.midThreadedNode();
//        threadedBinaryTree.preThreadedNode();
//        threadedBinaryTree.postThreadedNode();
//        System.out.println("10号节点的左节点 = " + node10.getLeft());
//        System.out.println("10号节点的右节点 = " + node10.getRight());

        List<HeroNode> heroNodes;
//        heroNodes = threadedBinaryTree.midThreadedList();
//        heroNodes = threadedBinaryTree.preThreadedList();
//        heroNodes = threadedBinaryTree.postThreadedList();
//        System.out.println(heroNodes);
    }
}

class ThreadedBinaryTree {
    private HeroNode root;

    /*前驱节点*/
    private HeroNode pre = null;

    public ThreadedBinaryTree(HeroNode root) {
        this.root = root;
    }

    public void midThreadedNode() {
        this.midThreadedNodes(this.root);
    }

    public void preThreadedNode() {
        this.preThreadedNodes(this.root);
    }

    public void postThreadedNode() {
        this.postThreadedNodes(this.root);
    }

    /**
     * 中序线索化二叉树
     *
     * @param node 就是当前需要线索化的节点
     */
    public void midThreadedNodes(HeroNode node) {
        /*如果节点为空，不能进行线索化，直接返回*/
        if (node == null) {
            return;
        }
        //1、先线索化左子树
        midThreadedNodes(node.getLeft());
        /**
         * 2、再线索化当前节点
         * 处理当前节点的前驱节点，｛8,3,10,1,14,6｝ 以8节点来理解，left = null，leftType = 1；
         */
        if (node.getLeft() == null) {
            /*让当前节点的左指针指向前驱节点*/
            node.setLeft(pre);
            /*修改前驱节点指向类型*/
            node.setLeftType(1);
        }

        /*处理后继节点*/
        if (pre != null && pre.getRight() == null) {
            /*让前驱节点的右指针指向当前节点*/
            pre.setRight(node);
            /*修改前驱节点的右指针指向类型*/
            pre.setRightType(1);
        }
        /*!!! 每处理一个节点后，让当前节点是下一个节点的前驱节点*/
        pre = node;
        //3、最后线索化右子树
        midThreadedNodes(node.getRight());
    }

    public List<HeroNode> midThreadedList() {
        List<HeroNode> heroNodes = Lists.newArrayList();
        /*定义中间变量，初始值为root*/
        HeroNode node = root;
        /*如果节点不为空，就一直查找*/
        while (node != null) {
            /*向左查找，直到获取leftType == 1的节点*/
            while (node.getLeftType() != 1) {
                node = node.getLeft();
            }
            /*将找到的第一个节点添加到列表*/
            heroNodes.add(node);
            /*如果当前节点的右指针指向的是后继节点，就将其全部添加到列表*/
            /*如果当前节点的后继节点指向的是一棵子树，那么就退出循环*/
            while (node.getRightType() == 1) {
                node = node.getRight();
                heroNodes.add(node);
            }
            /*切记：当前节点一定要指向后继节点，替换这个遍历的节点，否则会一直陷入死循环*/
            node = node.getRight();
        }
        return heroNodes;
    }

    public void preThreadedNodes(HeroNode node) {
        /*如果节点为空，不能进行线索化，直接返回*/
        if (node == null) {
            return;
        }
        /**
         * 1、线索化当前节点
         * 处理当前节点的前驱节点，｛1,3,8,10,6,14｝ 以8节点来理解，left = 3，leftType = 0；
         */
        if (node.getLeft() == null) {
            /*让当前节点的左指针指向前驱节点*/
            node.setLeft(pre);
            /*修改前驱节点指向类型*/
            node.setLeftType(1);
        }

        /*处理后继节点*/
        if (pre != null && pre.getRight() == null) {
            /*让前驱节点的右指针指向当前节点*/
            pre.setRight(node);
            /*修改前驱节点的右指针指向类型*/
            pre.setRightType(1);
        }

        /*!!! 每处理一个节点后，让当前节点是下一个节点的前驱节点*/
        pre = node;
        //2、再线索化左子树（!!! 如果当前节点的左指针域指向的是前驱节点，则不再进行处理，否则会进入死循环）
        if (node.getLeftType() == 0) {
            preThreadedNodes(node.getLeft());
        }
        //3、最后线索化右子树（!!! 如果当前节点的右指针域指向的是后继节点，则不再进行处理，否则会进入死循环）
        if (node.getRightType() == 0) {
            preThreadedNodes(node.getRight());
        }
    }

    /**
     * 按照后继线索遍历
     *
     * @return
     */
    public List<HeroNode> preThreadedList() {
        List<HeroNode> heroNodes = Lists.newArrayList();
        /*定义中间变量，初始值为root*/
        HeroNode node = root;
        /*如果节点不为空，就一直查找*/
        while (node != null) {
            /**向左查找，直到获取leftType == 1的节点,
             * 前序遍历：现将遇到的节点添加到列表，再一次向左查找，完了之后再一次向右查找即可
             * */
            while (node.getLeftType() != 1) {
                /*将找到的第一个节点添加到列表*/
                heroNodes.add(node);
                node = node.getLeft();
            }
            heroNodes.add(node);
            node = node.getRight();
        }
        return heroNodes;
    }

    public void postThreadedNodes(HeroNode node) {
        /*如果节点为空，不能进行线索化，直接返回*/
        if (node == null) {
            return;
        }
        /*1、先线索化左子树*/
        postThreadedNodes(node.getLeft());

        /*2、再线索化右子树*/
        postThreadedNodes(node.getRight());
        /**
         * 3、最后处理当前节点
         * 处理当前节点的前驱节点，｛8,10,3,14,6,1｝ 以8节点来理解，left = null，leftType = 1；
         */
        if (node.getLeft() == null) {
            /*让当前节点的左指针指向前驱节点*/
            node.setLeft(pre);
            /*修改前驱节点指向类型*/
            node.setLeftType(1);
        }

        /*处理后继节点*/
        if (pre != null && pre.getRight() == null) {
            /*让前驱节点的右指针指向当前节点*/
            pre.setRight(node);
            /*修改前驱节点的右指针指向类型*/
            pre.setRightType(1);
        }
        /*!!! 每处理一个节点后，让当前节点是下一个节点的前驱节点*/
        pre = node;
    }

    public void preOrder() {
        System.out.println("==========前序遍历==========");
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空二叉树");
        }
    }
}

@Data
class HeroNode {
    private int no;
    private String name;
    /*左子树*/
    private HeroNode left;
    /*右子树*/
    private HeroNode right;
    /*如果leftType或rightType为0，分别表示指向的是右子树或左子树，为1则表示的指向前驱节点或后继节点*/
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /*前序遍历*/
    public void preOrder() {
        /*1、当前节点*/
        System.out.println(this);
        /*2、递归左子树前序遍历*/
        if (this.left != null) {
            this.left.preOrder();
        }
        /*3、递归右子树前序遍历*/
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 前序遍历查找节点
     *
     * @param no 待查找的编号
     * @return 返回指定节点数据，如果没有就返回null
     */
    public HeroNode preOrderSearch(int no) {
        /*比较当前节点是否与待查找的节点*/
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        /*如果左子树不为空，递归遍历左子树进行节点查找*/
        if (this.left != null) {
            resNode = preOrderSearch(no);
        }
        /*如果左子树找到节点，直接返回，毋需再进行右子树遍历*/
        if (resNode != null) {
            return resNode;
        }
        /*如果右子树不为空，递归遍历右子树进行节点查找*/
        if (this.right != null) {
            resNode = preOrderSearch(no);
        }
        return resNode;
    }

}
