package com.example.demo.sgg.datastructure.graph;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author yl
 * @Date 2020/3/1 14:45
 * @description:
 */
public class GraphDemo {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] vertexes = {"A", "B", "C", "D", "E"};
        for (String item : vertexes) {
            graph.insertVertex(item);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.show();
        System.out.println(graph.getTotalEdges());

        graph.dfs();

        System.out.println();
        graph.bfs();
    }
}

@Data
class Graph {

    private final static int ON_PATH = 1;
    /*存储顶点的集合*/
    private List<String> vertexList;
    /*存储图对应的邻接矩阵，就是边*/
    private int[][] edges;
    /*总共边数*/
    private int totalEdges;
    /*记录某个顶点是否被访问*/
    private boolean[] isVisited;


    public Graph(int vertexNumber) {
        this.vertexList = Lists.newArrayList();
        this.edges = new int[vertexNumber][vertexNumber];
        this.totalEdges = 0;
    }

    /**
     * 插入顶点
     *
     * @param vertex 顶点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 插入边
     *
     * @param vertexIndex1 表示顶点1的索引值
     * @param vertexIndex2 表示顶点2的索引值
     * @param weight       权值，顶点1与顶点2之间是否有边
     */
    public void insertEdge(int vertexIndex1, int vertexIndex2, int weight) {
        edges[vertexIndex1][vertexIndex2] = weight;
        edges[vertexIndex2][vertexIndex1] = weight;
        if (weight == ON_PATH) {
            totalEdges++;
        }
    }

    /**
     * 返回顶点总数
     *
     * @return
     */
    public int getNumberOfVertex() {
        return vertexList.size();
    }

    /**
     * 返回边的总数
     *
     * @return
     */
    public int getEdges() {
        return totalEdges;
    }

    /**
     * 根据索引获取对应的顶点值
     *
     * @param index 索引
     * @return
     */
    public String getVertexByIndex(int index) {
        return vertexList.get(index);
    }

    /**
     * 获取两个顶点之间的权值
     *
     * @param vertexIndex1 顶点1的索引
     * @param vertexIndex2 顶点2的索引
     * @return
     */
    public int getWeight(int vertexIndex1, int vertexIndex2) {
        return edges[vertexIndex1][vertexIndex2];
    }

    /**
     * 显示整个图
     */
    public void show() {
//        System.out.println(Arrays.deepToString(edges));
        for (int[] item : edges) {
            System.out.println(Arrays.toString(item));
        }
    }

    /**
     * 得到第一个邻接顶点的下标W
     *
     * @param index
     * @return 如果存在就返回对应下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点的下标来获取下一个邻接节点
     *
     * @param vertexIndex1
     * @param vertexIndex2
     * @return
     */
    public int getNextNeighbor(int vertexIndex1, int vertexIndex2) {
        for (int i = vertexIndex2 + 1; i < vertexList.size(); i++) {
            if (edges[vertexIndex1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 重载，遍历所有的节点，进行dfs
     */
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        /*遍历所有的顶点，进行dfs[回溯]*/
        for (int index = 0; index < vertexList.size(); index++) {
            if (!isVisited[index]) {
                dfs(index);
            }
        }
    }

    /**
     * 深度优先遍历
     *
     * @param vertexIndex 顶点的索引值
     */
    private void dfs(int vertexIndex) {
        /*首先我们访问该结点，输出*/
        System.out.print(getVertexByIndex(vertexIndex) + "->");
        /*将结点设置为已经访问*/
        isVisited[vertexIndex] = true;
        /*查找当前节点的第一个邻接节点*/
        int firstNeighbor = getFirstNeighbor(vertexIndex);
        while (firstNeighbor != -1) {
            /*如果该顶点没有被访问*/
            if (!isVisited[firstNeighbor]) {
                dfs(firstNeighbor);
            }
            firstNeighbor = getNextNeighbor(vertexIndex, firstNeighbor);
        }
    }

    /**
     * 重载，遍历所有的节点，进行bfs
     */
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        /*遍历所有的顶点，进行dfs[回溯]*/
        for (int index = 0; index < vertexList.size(); index++) {
            if (!isVisited[index]) {
                bfs(index);
            }
        }
    }
    /**
     * 广都优先遍历
     *
     * @param vertexIndex
     */
    private void bfs(int vertexIndex) {
        /*表示队列的头部节点对应的下标*/
        int headIndex;
        /*邻接节点下标*/
        int firstNeighborIndex;
        /*队列，记录节点的访问顺序*/
        LinkedList<Integer> queue = Lists.newLinkedList();
        /*访问节点*/
        System.out.print(getVertexByIndex(vertexIndex) + "=>");
        /*将该节点标记位已读*/
        isVisited[vertexIndex] = true;
        /*将该节点假如队列*/
        queue.addLast(vertexIndex);
        while (!queue.isEmpty()){
            /*取出队列的头节点*/
            headIndex = queue.removeFirst();
            /*得到第一个邻接节点下标*/
            firstNeighborIndex = getFirstNeighbor(headIndex);
            while (firstNeighborIndex != -1){
                if(!isVisited[firstNeighborIndex]){
                    /*访问节点*/
                    System.out.print(getVertexByIndex(firstNeighborIndex) + "=>");
                    /*标记为已读*/
                    isVisited[firstNeighborIndex] = true;
                    /*入队列*/
                    queue.addLast(firstNeighborIndex);
                }
                /*以headIndex为前驱节点，找firstNeighborIndex后面的下一个节点，体现广都优先*/
                firstNeighborIndex = getNextNeighbor(headIndex,firstNeighborIndex);
            }
        }
    }
}
