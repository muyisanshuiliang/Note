package com.example.demo.sgg.datastructure.tree.binarysorttree;

import lombok.Data;

/**
 * @Author yl
 * @Date 2020/2/25 9:54
 * @description:
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {

        int[] arr = {8, 4, 5, 7, 3, 33, 18, 16};
//        int[] arr = { 9, 6,12,33};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int item : arr) {
            binarySortTree.addNode(new Node(item));
        }
        binarySortTree.infixOrder();

        Node node = binarySortTree.get(10);
        System.out.println("查找的节点：" + node);

        node = binarySortTree.getParent(56);
        System.out.println("查找节点的父节点：" + node);

        for (int item : arr) {
            binarySortTree.delNode(item);
        }
        System.out.println("删除节点后：");
        binarySortTree.infixOrder();
    }
}

@Data
class BinarySortTree {

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

    /**
     * 查找指定节点
     *
     * @param value
     * @return
     */
    public Node get(int value) {
        if (root == null) {
            return null;
        }
        return root.get(value);
    }

    /**
     * 查找指定节点的父节点
     *
     * @param value
     * @return
     */
    public Node getParent(int value) {
        if (root == null) {
            return null;
        }
        return root.getParent(value);
    }

    /**
     * 删除节点
     *
     * @param value
     */
    public void delNode(int value) {
        /*如果节点为空，直接返回*/
        if (root == null) {
            return;
        }
        /*获取到待删除的节点*/
        Node targetNode = get(value);
        /*如果未查找到待删除节点，待删除节点为空，直接返回*/
        if (targetNode == null) {
            return;
        }
        /*如果root没有子节点，说明root节点就是待删除节点*/
        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
            return;
        }
        /*获取到待删除节点的父节点*/
        Node parent = getParent(value);
        /*待删除节点是叶子节点*/
        if (targetNode.getLeft() == null && targetNode.getRight() == null) {
            /*判断待删除节点是父节点的左子节点还是右子节点*/
            if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                parent.setLeft(null);
            } else if (parent.getRight() != null && parent.getRight().getValue() == value) {
                parent.setRight(null);
            }
            /*待删除节点右左右两个子节点*/
        } else if (targetNode.getLeft() != null && targetNode.getRight() != null) {
            int min = delRightTreeMin(targetNode.getRight());
            targetNode.setValue(min);
            /*待删除节点只有左或右一个子节点*/
        } else {
            /*待删除节点只有一个左子节点*/
            if (targetNode.getLeft() != null) {
                /*如果父节点为NULL，表示待删除节点就是root节点*/
                if (parent == null) {
                    /*直接将root节点指向待删除节点的子节点即可*/
                    root = targetNode.getLeft();
                } else {
                    /*判断待删除节点是父节点的左子节点还是右子节点*/
                    if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                        parent.setLeft(targetNode.getLeft());
                    } else if (parent.getRight() != null && parent.getRight().getValue() == value) {
                        parent.setRight(targetNode.getLeft());
                    }
                }
                /*待删除节点只有个右子节点*/
            } else {
                /*如果父节点为NULL，表示待删除节点就是root节点*/
                if (parent == null) {
                    /*直接将root节点指向待删除节点的子节点即可*/
                    root = targetNode.getRight();
                } else {
                    /*判断待删除节点是父节点的左子节点还是右子节点*/
                    if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                        parent.setLeft(targetNode.getRight());
                    } else if (parent.getRight() != null && parent.getRight().getValue() == value) {
                        parent.setRight(targetNode.getRight());
                    }
                }
            }
        }

    }

    /**
     * 1、返回以node为根节点的二叉排序树的最小节点的值
     * 2、删除node为根节点的二叉排序树的最小节点
     *
     * @param node 作为二叉树根节点的节点
     * @return 返回最小值
     */
    public int delRightTreeMin(Node node) {
        if (node == null) {
            throw new RuntimeException("节点不能为空！");
        }
        Node targetNode = node;
        /*向左子树递归查找，直到叶子节点*/
        while (targetNode.getLeft() != null) {
            targetNode = targetNode.getLeft();
        }
        /*此时 targetNode就指向了最小节点*/
        delNode(targetNode.getValue());
        return targetNode.getValue();
    }

    /**
     * 1、返回以node为根节点的二叉排序树的最大节点的值
     * 2、删除node为根节点的二叉排序树的最大节点
     *
     * @param node 作为二叉树根节点的节点
     * @return 返回最大值
     */
    public int delLeftTreeMax(Node node) {
        if (node == null) {
            throw new RuntimeException("节点不能为空！");
        }
        Node targetNode = node;
        /*向右子树递归查找，直到叶子节点*/
        while (targetNode.getRight() != null) {
            targetNode = targetNode.getRight();
        }
        /*此时 targetNode就指向了最大节点*/
        delNode(targetNode.getValue());
        return targetNode.getValue();
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
    }

    /**
     * 根据值获取节点
     * 切记：如果值相同的时候返回的都是第一值的节点，
     *
     * @param value 值
     * @return 如果找到就返回该节点，未找到返回NULL
     */
    public Node get(int value) {
        /*找到该节点*/
        if (this.value == value) {
            return this;
        }
        /*如果待查找的值小于当前节点的值，递归向左子树查找*/
        if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.get(value);
            /**
             * 如果待查找的值大于等于当前节点的值，递归向右子树查找
             * 如果在添加的时候将等于节点放在左子树，这是等于将放在向左递归查找中
             */
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.get(value);
        }
    }

    public Node getParent(int value) {
        /*当前节点就是待查找值的父节点*/
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        }
        if (value < this.value && this.left != null) {
            return this.left.getParent(value);
        }
        if (value >= this.value && this.right != null) {
            return this.right.getParent(value);
        }
        return null;
    }
}