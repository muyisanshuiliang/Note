package com.example.demo.sgg.datastructure;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author yl
 * @Date 2020/1/7 15:00
 * @description:
 */
public class CalculatorDemo {
    public static void main(String[] args) {
        String expression = "50*(35+17)";
        /*数字栈，用于数据的存储*/
        CalculatorStack numberStack = new CalculatorStack(10);
        /*操作符号栈，用于存储表达式的操作符号*/
        CalculatorStack operStack = new CalculatorStack(10);
        int index = 0;
        /*被操作数*/
        int num1;
        /*操作数*/
        int num2;
        /*字符串中每个字符变量*/
        char temp;
        /*操作符栈中取出的数据*/
        int oper;
        /*计算结果*/
        int res;
        while (true) {
            /*如果扫描点大于表达式的长度，循环结束*/
            if (index >= expression.length()) {
                break;
            }
            temp = expression.charAt(index);
            index++;
            /*如果是后括号，则进行相关计算，并结束本次循环*/
            if (isBackBrackets(temp, numberStack, operStack)) {
                continue;
            }
            /*如果是数据，则直接放入数据栈*/
            if (!isOper(temp)) {
                /*中间变量转字符串*/
                StringBuilder keepNum = new StringBuilder();
                keepNum.append(temp);
                while (true) {
                    /*取出当前值所在位置的下一位变量*/
                    char nextTemp = expression.charAt(index);
                    /*如果为操作符，循环结束*/
                    if (isOper(nextTemp)) {
                        break;
                    }
                    /*如果不是操作符，将当前变量和下一位置的变量进行相加*/
                    keepNum.append(nextTemp);
                    /*指针位置++*/
                    index++;
                    /*如果指针位置已大于字符串的最大index,循环结束*/
                    if (index > expression.length() - 1) {
                        break;
                    }
                }
                /*将当前数据压入数据栈*/
                numberStack.push(Integer.parseInt(keepNum.toString()));
                continue;
            }
            /*如果操作符栈为空，则直接将操作符压入栈*/
            if (operStack.isEmpty()) {
                operStack.push(temp);
                continue;
            }
            /*判断符号栈的栈顶元素是否是前括号，如果不是前括号，需对运算符的优先级进行对比，如果是前括号，则直接将当前运算符压入栈即可*/
            if (!isBeforeBrackets(operStack.peek())) {
                /*如果操作符栈不为空，如果当前操作符的优先级低于操作符栈顶操作符*/
                if (priority(temp) < priority(operStack.peek())) {
                    /*取出数据栈栈顶的两个元素*/
                    num1 = numberStack.pop();
                    num2 = numberStack.pop();
                    /*取出符号操作栈顶的一个元素*/
                    oper = (char) operStack.pop();
                    /*计算结果*/
                    res = cal(num1, num2, oper);
                    /*将结果压入数据栈*/
                    numberStack.push(res);
                    /*将当前操作符压入符号栈*/
                    operStack.push(temp);
                    continue;
                }
            }
            /*如果操作符栈不为空，如果当前操作符的优先级高于操作符栈顶操作符，直接将当前操作符压入栈*/
            operStack.push(temp);
        }
        /*循环计算结果，最终数据栈内仅存在一个计算结果元素*/
        while (true) {
            /*操作符栈为空，说明计算结束*/
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numberStack.pop();
            num2 = numberStack.pop();
            oper = operStack.pop();
            res = cal(num1, num2, oper);
            numberStack.push(res);
        }
        res = numberStack.pop();
        System.out.printf("表达式 %s = %d \n", expression, res);
    }

    /**
     * 判断当前字符是否是小括号
     *
     * @param ch
     * @return
     */
    private static boolean isBackBrackets(int ch, CalculatorStack numberStack, CalculatorStack operStack) {
        if (ch != ')') {
            return false;
        }
        char temp = (char) operStack.pop();
        int num1;
        int num2;
        int res;
        while (temp != '(') {
            num1 = numberStack.pop();
            num2 = numberStack.pop();
            res = cal(num1, num2, temp);
            numberStack.push(res);
            temp = (char) operStack.pop();
        }
        return true;
    }

    /**
     * 判断是否为前括号
     *
     * @param ch
     * @return
     */
    private static boolean isBeforeBrackets(int ch) {
        return ch == '(';
    }

    /**
     * 判断一个字符是否是操作符
     *
     * @param ch
     */
    private static boolean isOper(int ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')';
    }

    /**
     * 运算符的优先级，自行定义，数字越大优先级越高
     *
     * @param ch
     * @return
     */
    public static int priority(int ch) {
        if (ch == '/' || ch == '*' || ch == '(') {
            return 1;
        }
        if (ch == '+' || ch == '-') {
            return 0;
        }
        return -1;
    }

    /**
     * 计算两个数的值
     *
     * @param num1 被操作数
     * @param num2 操作数
     * @param ch   操作符
     * @return
     */
    private static int cal(int num1, int num2, int ch) {
        int res = 0;
        switch (ch) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                /*注意数据的顺序*/
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                /*注意数据的顺序*/
                res = num2 / num1;
                break;
            case '%':
                /*注意数据的顺序*/
                res = num2 % num1;
                break;
            default:
                break;
        }
        return res;
    }

}


@Slf4j
class CalculatorStack {
    /*表示栈的最大深度*/
    private int maxSize;
    /*数组用于实现栈内数据存储*/
    private int[] array;
    /*栈顶标识，默认值-1*/
    private int top = -1;

    /**
     * 构造函数
     *
     * @param maxSize 栈的最大深度
     */
    public CalculatorStack(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 返回栈顶元素
     *
     * @return
     */
    public int peek() {
        return array[top];
    }

    /**
     * 判断栈是否已满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 将数据压入栈
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈已满，无法进行元素添加");
        }
        /*每存入一个数据，栈顶标识会上移一次*/
        array[++top] = value;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无元素");
        }
        /*每取出一个数据，栈顶会下移一次*/
        return array[top--];
    }

    /**
     * 显示栈内所有元素，类似于peek
     */
    public void show() {
        if (isEmpty()) {
            log.info("栈为空，无元素");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("array[%d] = %d \n", i, array[i]);
        }
    }


}