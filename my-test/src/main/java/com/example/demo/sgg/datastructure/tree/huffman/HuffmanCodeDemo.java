package com.example.demo.sgg.datastructure.tree.huffman;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author yl
 * @Date 2020/2/22 23:26
 * @description:
 */
public class HuffmanCodeDemo {
    /**
     * 用于编码，将赫夫曼编码存放在Map<Byte,String>中，形式 32->01 97->100 100->11000等等
     */
    private static Map<Byte, String> huffmanCodeMap = Maps.newHashMap();
    /**
     * 用于解码，将赫夫曼编码存放在Map<String,Byte>中，形式 01->32 100->97 11000->100等等
     */
    private static Map<String, Byte> huffmanDecodeMap = Maps.newHashMap();
    /**
     * 在生成赫夫曼编码表时，需要去拼接路径，定义一个StringBuilder，存储某个叶子节点的路径
     */
    private static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        byte[] bytes = huffmanCodeZip(content);
//        System.out.println(Arrays.toString(bytes));
//        bytes = huffmanCodeUnzip(bytes);
//        System.out.println(new String(bytes));

        String content = "love !";
        byte[] bytes = huffmanCodeZip(content);
        System.out.println(Arrays.toString(bytes));
        bytes = huffmanCodeUnzip(bytes);
        System.out.println(new String(bytes));
    }
    /**
     * 完成数据的解码
     * 1、将压缩完成的byte数组转换成对应的二进制字符串
     *    [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     *    => 10101000101111111100100010111111110010001011111111001001010011011100011100000110111010001111001
     *       01000101111111100110001001010011011100
     * 2、赫夫曼编码对应的二进制字符串转换成原始数据
     *   10101000101111111100100010111111110010001011111111001001010011011100011100000110111010001111001
     *   01000101111111100110001001010011011100
     *   => i like like like java do you like a java
     */
    /**
     * 赫夫曼编码解码
     *
     * @param huffmanZipCodeData 通过huffman coding压缩完成的二进制编码
     * @return 压缩前字符传对应的数组
     */
    private static byte[] huffmanCodeUnzip(byte[] huffmanZipCodeData) {
        if (huffmanZipCodeData == null || huffmanZipCodeData.length == 0) {
            return null;
        }
        /*1、将压缩完成的byte数组转换成对应的二进制字符串*/
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, length = huffmanZipCodeData.length; i < length; i++) {
            /*判断是否是最后一个字符*/
            boolean flag = (i == (length - 1));
            stringBuilder.append(byteToString(!flag, huffmanZipCodeData[i]));
        }
        /*2、通过解码表，获取对应的byte[]*/
        return getHuffmanDecodeList(stringBuilder);
    }

    /**
     * 根据字符串获取对应的Byte集合
     *
     * @param value
     * @return
     */
    private static byte[] getHuffmanDecodeList(StringBuilder value) {
        if (value == null) {
            return null;
        }
        List<Byte> byteList = Lists.newArrayList();
        /*扫描value的每一个字符，组成key,通过huffmanDecodeMap中获取对应的key值是否存在value*/
        for (int index = 0; index < value.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte tempByteValue = null;
            while (flag) {
                /*通过移动count,直到获取到key对应的value*/
                String key = value.substring(index, index + count);
                tempByteValue = huffmanDecodeMap.get(key);
                if (tempByteValue == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            byteList.add(tempByteValue);
            /*索引移动草count，进行下一次匹配*/
            index += count;
        }
        /*将list数据放入数组返回*/
        byte[] bytes = new byte[byteList.size()];
        for (int i = 0, length = byteList.size(); i < length; i++) {
            bytes[i] = byteList.get(i);
        }
        return bytes;
    }

    /**
     * 将一个byte转换成二进制字符串
     *
     * @param flag      标志是否需要补高位，如果是true,需要补高位，false不需要补高位，最后一个字节不需要补高位
     * @param byteValue byte值
     * @return byte值转换完成后对应的字符串（按补码的形式返回）
     */
    private static String byteToString(boolean flag, byte byteValue) {
        int temp = byteValue;
        if (flag) {
            /*按位或256，1 0000 0000 | 0000 0001 = 1 0000 0001 */
            temp |= 256;
        }
        /*返回temp对应的二进制补码*/
        String tempString = Integer.toBinaryString(temp);
        if (flag) {
            return tempString.substring(tempString.length() - 8);
        } else {
            return tempString;
        }
    }

    /**
     * 获取通过huffman编码压缩完成后的数据
     *
     * @param content 原始字符串
     * @return
     */
    private static byte[] huffmanCodeZip(String content) {
        /*1、字符串转换成字符数组*/
        byte[] contentBytes = content.getBytes();
        /*2、创建节点列表*/
        List<CodeNode> codeNodes = getCodeNodes(contentBytes);
        /*3、创建一棵huffman树*/
        CodeNode codeNode = creatHuffmanTree(codeNodes);
        /*4、获取huffman编码表*/
        getCodes(codeNode);
        /*5、获取数据压缩数组*/
        return zip(contentBytes);
    }

    /**
     * 将字符串对应的Byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的Byte[]
     *
     * @param bytes 原始字符串对应的Byte[]
     * @return 返回赫夫曼编码处理后的Byte[]
     * String content = "i like like like java do you like a java"; =>  byte[] contentBytes = content.getBytes();
     * 返回的字符串是：10101000101111111100100010111111110010001011111111001001010011011100011100000110111010001111001
     * 01000101111111100110001001010011011100
     * => 对应的Byte[] huffmanCodeBytes,级8位对应一个Byte,放入到huffmanCodeBytes
     * huffmanCodeBytes[0] = 10101000（补码）
     * => byte 【10101000 => 10101000 - 1 = 10100111(反码) => 11011000】
     * huffmanCodeBytes[0] = -88
     */
    private static byte[] zip(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        /*1、将字符串转换成对应的赫夫曼编码字符串*/
        StringBuilder stringBuilder = new StringBuilder();
        for (byte item : bytes) {
            stringBuilder.append(huffmanCodeMap.get(item));
        }
        /**
         * 2、求取赫夫曼编码的字符串长度,需要向上取整,类似于下面的步骤
         * int len;
         * if (stringBuilder.length() % 8 == 0) {
         *      len = stringBuilder.length() / 8;
         * } else {
         *      len = stringBuilder.length() / 8 + 1;
         * }
         */
        int len = (stringBuilder.length() + 7) / 8;
        /*3、将赫夫曼编码字符串转换成byte[],8位数据压缩成一个数据放入到数组中*/
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        String tempByte;
        /*因为是8位对应一个Byte,所以步长是8*/
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            if ((i + 8) > stringBuilder.length()) {
                tempByte = stringBuilder.substring(i);
            } else {
                tempByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(tempByte, 2);
        }
        return huffmanCodeBytes;
    }

    /**
     * 方法重载
     *
     * @param codeNode
     */
    private static void getCodes(CodeNode codeNode) {
        if (codeNode == null) {
            return;
        }
        getCodes(codeNode.getLeft(), "0", stringBuilder);
        getCodes(codeNode.getRight(), "1", stringBuilder);
    }

    /**
     * 将传入的node节点的所有叶子节点的赫夫曼编码得到，并放入到huffmanCodeMap中
     *
     * @param codeNode      传入的节点
     * @param code          路径，约定左子节点是 0，右子节点是 1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(CodeNode codeNode, String code, StringBuilder stringBuilder) {
        StringBuilder tempCode = new StringBuilder(stringBuilder);
        /*1、将code加入到tempCode中去*/
        tempCode.append(code);
        /*2、如果节点为空，不出力，直接返回*/
        if (codeNode == null) {
            return;
        }
        /*3、判断当前节点是否是叶子节点*/
        if (codeNode.getValue() == null) {
            /*向左、向右递归处理*/
            getCodes(codeNode.getLeft(), "0", tempCode);
            getCodes(codeNode.getRight(), "1", tempCode);
        } else {
            /*表示找到某个叶子节点的最后*/
            huffmanCodeMap.put(codeNode.getValue(), tempCode.toString());
            huffmanDecodeMap.put(tempCode.toString(), codeNode.getValue());
        }
    }

    private static List<CodeNode> getCodeNodes(byte[] contentBytes) {
        if (ObjectUtils.isEmpty(contentBytes)) {
            return Lists.newArrayList();
        }
        Map<Byte, Integer> contentMap = Maps.newHashMap();
        Integer value;
        for (Byte item : contentBytes) {
            value = contentMap.getOrDefault(item, 0);
            contentMap.put(item, value + 1);
        }
        List<CodeNode> codeNodes = Lists.newArrayList();
        for (Byte item : contentMap.keySet()) {
            codeNodes.add(new CodeNode(item, contentMap.get(item)));
        }
        return codeNodes;
    }

    private static CodeNode creatHuffmanTree(List<CodeNode> codeNodes) {
        if (CollectionUtils.isEmpty(codeNodes)) {
            return null;
        }
        while (codeNodes.size() > 1) {
            Collections.sort(codeNodes);
            /*从小到大取第一、二个元素，如果是从大到小的排序，就取第size-1、size-2个元素*/
            CodeNode leftNode = codeNodes.get(0);
            CodeNode rightNode = codeNodes.get(1);
            CodeNode parent = new CodeNode(null, leftNode.getWeight() + rightNode.getWeight());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            codeNodes.remove(leftNode);
            codeNodes.remove(rightNode);
            codeNodes.add(parent);
        }
        return codeNodes.get(0);
    }
}

@Data
class CodeNode implements Comparable<CodeNode> {
    private Byte value;// 存放数值本身
    private Integer weight;// 权值，用于存放数据出现的次数
    private CodeNode left;
    private CodeNode right;

    public CodeNode(Byte value, Integer weight) {
        this.value = value;
        this.weight = weight;
    }

    /*从小到大排序*/
    @Override
    public int compareTo(CodeNode o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "CodeNode{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }
}
