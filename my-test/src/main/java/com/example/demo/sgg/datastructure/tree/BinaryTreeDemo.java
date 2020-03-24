package com.example.demo.sgg.datastructure.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author yl
 * @Date 2020/1/20 13:53
 * @description:
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node1 = new HeroNode(2, "吴用");
        HeroNode node2 = new HeroNode(3, "卢俊义");
        HeroNode node3 = new HeroNode(4, "林冲");
        HeroNode node4 = new HeroNode(5, "武松");
        root.setLeft(node1);
        root.setRight(node2);
        node2.setRight(node3);
        node2.setLeft(node4);
        BinaryTree binaryTree = new BinaryTree(root);
        binaryTree.preOrder();
//        binaryTree.midOrder();
//        binaryTree.postOrder();
//        binaryTree.delNeroNode(1);
        binaryTree.delNeroNodeLeft(2);
        binaryTree.preOrder();
    }
}

@Data
@AllArgsConstructor
class BinaryTree {
    private HeroNode root;

    public void preOrder() {
        System.out.println("==========前序遍历==========");
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空二叉树");
        }
    }

    public void midOrder() {
        System.out.println("==========中序遍历==========");
        if (root != null) {
            root.midOrder();
        } else {
            System.out.println("空二叉树");
        }
    }

    public void postOrder() {
        System.out.println("==========后序遍历==========");
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("空二叉树");
        }
    }

    public void delNeroNode(int no) {
        if (root == null) {
            System.out.println("空树，不能进行删除操作！！！");
            return;
        }
        /*如果根节点的编号与待删除节点的编号一致。直接将root节点置null即可*/
        if (root.getNo() == no) {
            root = null;
            return;
        }
        /*递归删除指定节点*/
        root.delHeroNode(no);
    }

    public void delNeroNodeLeft(int no) {
        if (root == null) {
            System.out.println("空树，不能进行删除操作！！！");
            return;
        }
        /*如果根节点的编号与待删除节点的编号一致。直接将root节点置null即可*/
        if (root.getNo() == no) {
            if(root.getLeft() != null){
                root = root.getLeft();
            }else {
                root = root.getRight();
            }
            return;
        }
        /*递归删除指定节点*/
        root.delHeroNodeLeft(no);
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

    /*中序遍历*/
    public void midOrder() {
        /*1、递归左子树中序遍历*/
        if (this.left != null) {
            this.left.midOrder();
        }
        /*2、当前节点*/
        System.out.println(this);
        /*3、递归右子树中序遍历*/
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    /**
     * 中序查找
     *
     * @param no 待查找的编号
     * @return 返回指定节点数据，如果没有就返回null
     */
    public HeroNode midOrderSearch(int no) {
        HeroNode resNode = null;
        /*如果左子树不为空，递归遍历左子树进行节点查找*/
        if (this.left != null) {
            resNode = midOrderSearch(no);
        }
        /*如果左子树找到节点，直接返回，毋需再进行当前节点和右子树遍历*/
        if (resNode != null) {
            return resNode;
        }
        /*如果当前节点即为待查找节点，直接返回当前节点即可*/
        if (this.no == no) {
            return this;
        }
        /*如果右子树不为空，递归遍历右子树进行节点查找*/
        if (this.right != null) {
            resNode = midOrderSearch(no);
        }
        return resNode;
    }

    /*后序遍历*/
    public void postOrder() {
        /*1、递归左子树后序遍历*/
        if (this.left != null) {
            this.left.postOrder();
        }
        /*2、递归右子树后序遍历*/
        if (this.right != null) {
            this.right.postOrder();
        }
        /*3、当前节点*/
        System.out.println(this);
    }

    /**
     * 后序查找
     *
     * @param no 待查找的编号
     * @return 返回指定节点数据，如果没有就返回null
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        /*如果左子树不为空，递归遍历左子树进行节点查找*/
        if (this.left != null) {
            resNode = postOrderSearch(no);
        }
        /*如果左子树找到节点，直接返回，毋需再进行右子树和前节点遍历*/
        if (resNode != null) {
            return resNode;
        }
        /*如果右子树不为空，递归遍历右子树进行节点查找*/
        if (this.right != null) {
            resNode = postOrderSearch(no);
        }
        /*如果当前节点在右子树找到，毋需再进行当前节点的判断*/
        if (resNode != null) {
            return resNode;
        }
        /*进行当前节点的判断*/
        if (this.no == no) {
            resNode = this;
        }
        return resNode;
    }

    /**
     * 根据编号删除节点
     * 1、如果是叶子节点直接删除该节点
     * 2、如果是非叶子节点直接删除该子树
     *
     * @param no 待删除的节点编号
     */
    public void delHeroNode(int no) {
        /*判断左节点是否是待删除节点，如果是直接删除*/
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        /*判断右节点是否是待删除的节点，如果是直接删除*/
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        /*递归左子树*/
        if (this.left != null) {
            this.left.delHeroNode(no);
        }
        /*递归右子树*/
        if (this.right != null) {
            this.right.delHeroNode(no);
        }
    }

    /**
     * 根据编号删除节点
     * 1、如果是叶子节点直接删除该节点
     * 2、如果是非叶子节点且只有一个子节点，将子节点置为当前节点
     * 3、如果是非叶子节点且有两个子节点，将左节点置为当前节点
     *
     * @param no 待删除的节点编号
     */
    public void delHeroNodeLeft(int no) {
        if (this.left != null && this.left.no == no) {
            if(left.left != null){
                this.left = left.left;
            }else {
                this.left = left.right;
            }
            return;
        }
        if (this.right != null && this.right.no == no) {
            if(right.left != null){
                this.right = right.left;
            }else {
                this.right = right.right;
            }
            return;
        }
        /*递归左子树*/
        if (this.left != null) {
            this.left.delHeroNode(no);
        }
        /*递归右子树*/
        if (this.right != null) {
            this.right.delHeroNode(no);
        }
    }
}
