package com.example.demo.sgg.datastructure.tree.huffman;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @Author yl
 * @Date 2020/2/21 16:22
 * @description:
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node node = creatHuffmanTree(arr);
        preOrder(node);
    }

    private static Node creatHuffmanTree(int[] arr) {
        List<Node> nodeList = getNodeList(arr);
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);
            /*从小到大取第一、二个元素，如果是从大到小的排序，就取第size-1、size-2个元素*/
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            Node parent = new Node(leftNode.getValue() + rightNode.getValue());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            nodeList.add(parent);
        }
        return nodeList.get(0);
    }

    private static List<Node> getNodeList(int[] arr) {
        if (arr.length == 0) {
            return Lists.newArrayList();
        }
        List<Node> nodes = Lists.newArrayList();
        for (int item : arr) {
            nodes.add(new Node(item));
        }
        return nodes;
    }

    private static void preOrder(Node root) {
        if (root == null) {
            System.out.println("这是一棵空的赫夫曼树");
            return;
        }
        root.preOrder();
    }

}

@Data
class Node implements Comparable<Node> {
    private int value;

    private Node left;

    private Node right;

    public Node(int value) {
        this.value = value;
    }

    /*从小到大排序*/
    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
