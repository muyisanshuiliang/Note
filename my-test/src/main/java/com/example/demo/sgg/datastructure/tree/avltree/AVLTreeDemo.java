package com.example.demo.sgg.datastructure.tree.avltree;

import lombok.Data;

/**
 * @Author yl
 * @Date 2020/2/28 17:30
 * @description:
 */
public class AVLTreeDemo {
    public static void main(String[] args) {

//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 8, 12, 7, 9, 6};
        int[] arr ={10,11,7,6,8,9};
//        int[] arr = { 9, 6,12,33};
        AVLTree avlTree = new AVLTree();
        for (int item : arr) {
            avlTree.addNode(new Node(item));
        }
        avlTree.infixOrder();
        System.out.println("平衡处理之前：");
        System.out.println("根节点：" + avlTree.getRoot());
        System.out.println("树的高度：" + avlTree.getRoot().height());
        System.out.println("左子树的高度：" + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度：" + avlTree.getRoot().rightHeight());
    }
}

@Data
class AVLTree {

    private Node root;

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root == null) {
            System.out.println("空树，无法进行遍历");
        } else {
            root.infixOrder();
        }
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void addNode(Node node) {
        if (node == null) {
            return;
        }
        /*如果根节点为空，待添加的节点直接加到根节点即可*/
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }
}


@Data
class Node {
    private int value;

    private Node left;

    private Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        /*如果当前节点的值大于待添加节点的值，则待添加节点应加入到当前节点的左子树*/
        if (node.value < value) {
            /*如果左子树为空，说明已找到待添加的位置*/
            if (this.left == null) {
                this.left = node;
            } else {
                /*向左递归查找添加位置*/
                this.left.add(node);
            }
            /*如果当前节点的值小于等于待添加节点的值，则待添加节点应加入到当前节点的右子树*/
        } else {
            /*如果右子树为空，说明已找到待添加的位置*/
            if (this.right == null) {
                this.right = node;
            } else {
                /*向右递归查找添加位置*/
                this.right.add(node);
            }
        }
        /*平衡二叉树操作*/
        AVLtree();
    }

    private void AVLtree(){
        /*当添加完一个节点后，如果 （右子树的高度 - 左子树的高度）> 1 ,左旋转*/
        if (rightHeight() - leftHeight() > 1) {
            /*如果右子树的左子树高度大于右子树的右子树的高度*/
            if(right != null && right.leftHeight() > right.rightHeight()){
                /*对右子树进行右旋转*/
                right.rightRotate();
            }
            leftRotate();
            return;
        }

        /*当添加完一个节点后，如果 （左子树的高度 - 右子树的高度）> 1 ,右旋转*/
        if (leftHeight() - rightHeight() > 1) {
            /*如果左子树的右子树高度大于它的左子树的高度*/
            if(left != null && left.rightHeight() > left.leftHeight()){
                /*先对当前节点的左节点（左子树）→ 左旋转*/
                left.leftRotate();
            }
            rightRotate();
            return;
        }
    }

    /**
     * 返回以当前节点为根节点的左子树的高度
     *
     * @return
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 返回以当前节点为根节点的右子树的高度
     *
     * @return
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 返回以当前节点为根节点的树的高度
     *
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 左转方法
     */
    public void leftRotate() {
        /*创建新的节点，节点值为当前根节点的值*/
        Node newNode = new Node(value);
        /*把新的节点的左子树设置成当前节点的左子树*/
        newNode.left = left;
        /*新节点的右子树指向当前节点右子树的左子树*/
        newNode.right = right.left;
        /*把当前节点的值替换成右子节点的值*/
        value = right.value;
        /*把当前节点右子树设置成当前节点右子树的右子树*/
        right = right.right;
        /*把当前节点的左子树（左子节点设置成新的节点）*/
        left = newNode;
    }


    public void rightRotate() {
        /*创建新的节点，节点值为当前根节点的值*/
        Node newNode = new Node(value);
        /*把新的节点的右子树设置成当前节点的右子树*/
        newNode.right = right;
        /*新节点的左子树指向当前节点左子树的右子树*/
        newNode.left = left.right;
        /*把当前节点的值替换成左子节点的值*/
        value = left.value;
        /*把当前节点右子树设置成当前节点右子树的右子树*/
        left = left.left;
        /*把当前节点的左子树（左子节点设置成新的节点）*/
        right = newNode;
    }
}

