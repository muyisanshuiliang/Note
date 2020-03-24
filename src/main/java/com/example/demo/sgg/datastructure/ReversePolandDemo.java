package com.example.demo.sgg.datastructure;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Stack;

/**
 * @Author yl
 * @Date 2020/1/8 13:49
 * @description:
 */
@Slf4j
public class ReversePolandDemo {

    /*数字匹配正则表达式*/
    private final static String NUMBER_MATCHES_EXPRESSION = "\\d+[\\.\\d+]{0,}";

    public static void main(String[] args) {
        String ss = "125.567";
        if (ss.matches("\\d+[\\.\\d+]{0,}")) {
            System.out.println(ss + " 是一个数字");
        }
        String expression = "40.4*(30.7+20.6)-5000";
        List<String> list = toInFixExpressionList(expression);
        System.out.println("中缀表达式：" + list);
        List<String> suffixExpression = getSuffixExpression(list);
        System.out.println("后缀表达式：" + suffixExpression);
        double calculationResults = getCalculationResults(suffixExpression);
        System.out.printf("计算结果：%s = %f\n", expression, calculationResults);

        expression = "4*(3+2)+5*(6+2)-5";
        list = toInFixExpressionList(expression);
        System.out.println("中缀表达式：" + list);
        suffixExpression = getSuffixExpression(list);
        System.out.println("后缀表达式：" + suffixExpression);
        calculationResults = getCalculationResults(suffixExpression);
        System.out.printf("计算结果：%s = %f\n", expression, calculationResults);


        expression = "7.89+5.45*(2.34+5.78)*(4.69-4.57)/(12.98-5.12)";
        list = toInFixExpressionList(expression);
        System.out.println("中缀表达式：" + list);
        suffixExpression = getSuffixExpression(list);
        System.out.println("后缀表达式：" + suffixExpression);
        calculationResults = getCalculationResults(suffixExpression);
        System.out.printf("计算结果：%s = %f\n", expression, calculationResults);
    }

    /**
     * 根据后缀表达式获取计算结果
     *
     * @param suffixExpression
     * @return
     */
    private static double getCalculationResults(List<String> suffixExpression) {
        double res = 0;
        if (StringUtils.isEmpty(suffixExpression)) {
            log.info("表达式为空");
            return res;
        }
        Stack<Double> resultStack = new Stack<>();
        double num1;
        double num2;
        /*循环中缀表达式，一旦遇到非数字字符串，则从结果栈中取出两个数据进行运算操作，并将执行结果压入结果栈*/
        for (String item : suffixExpression) {
            /*如果为数字，则直接将数据压入结果栈中*/
            if (item.matches(NUMBER_MATCHES_EXPRESSION)) {
                resultStack.push(Double.parseDouble(item));
                continue;
            }
            try {
                num1 = resultStack.pop();
                num2 = resultStack.pop();
                double calculate = Operation.calculate(num1, num2, item);
                resultStack.push(calculate);
            } catch (Exception e) {
                throw new RuntimeException("后缀表达式错误！");
            }
        }
        try {
            /*最终结果栈中只存在一个数据，那就是计算结果数据*/
            res = resultStack.pop();
        } catch (Exception e) {
            throw new RuntimeException("后缀表达式错误！");
        }
        return res;
    }

    /**
     * 根据中缀表达式获取后缀表达式
     *
     * @param list
     * @return
     */
    private static List<String> getSuffixExpression(List<String> list) {
        if (list.size() == 0) {
            log.info("表达式为空");
            return Lists.newArrayList();
        }
        /*用于存储操作符号栈*/
        Stack<String> operationStack = new Stack<>();
        /*用于存储后缀表达式的最终结果，由于不存在取数据的情况，固用列表即可*/
        List<String> suffixExpression = Lists.newArrayList();
        for (String item : list) {
            /*如果是数字，则直接入列表*/
            if (item.matches(NUMBER_MATCHES_EXPRESSION)) {
                suffixExpression.add(item);
                continue;
            }
            /*如是果是左括号，则直接入操作符号栈*/
            if (item.equals("(")) {
                operationStack.push(item);
                continue;
            }
            /*如果是右括号*/
            if (item.equals(")")) {
                /*循环查看栈顶元素是否是左括号，或者栈内元素为空*/
                while (true) {
                    if (operationStack.size() == 0 || "(".equals(operationStack.peek())) {
                        break;
                    }
                    /*依次弹出栈顶元素，放入数据列表*/
                    suffixExpression.add(operationStack.pop());
                }
                /*如果栈内还有元素，将栈顶的左括号弹出弃掉*/
                if (operationStack.size() != 0) {
                    operationStack.pop();
                }
                continue;
            }
            /*如果是操作符号*/
            /*如果操作符号栈为空或左括号，则将操作符号直接入栈*/
            if (operationStack.size() == 0 || "(".equals(operationStack.peek())) {
                operationStack.push(item);
                continue;
            }
            /*如果当前操作符优先级小于操作符号栈顶元素*/
            /*依次查看栈顶元素，直至栈空或者栈顶元素为左括号*/
            while (true) {
                if (operationStack.size() == 0 || "(".equals(operationStack.peek())) {
                    /*将当前操作符压入操作符栈*/
                    operationStack.push(item);
                    break;
                }
                /*如果当前操作符的优先级大于操作符号栈顶元素，则亦直接将当前操作符压入栈*/
                if (Operation.getPriority(item) > Operation.getPriority(operationStack.peek())) {
                    operationStack.push(item);
                    break;
                }
                /*依次弹出栈顶元素添加到数据列表*/
                suffixExpression.add(operationStack.pop());
            }
        }

        /*将符号栈内的元素依次添加到数据列表，获取最终的后缀表达式*/
        while (operationStack.size() != 0) {
            suffixExpression.add(operationStack.pop());
        }
        return suffixExpression;
    }

    /**
     * 将字符串（中缀表达式）转为List
     *
     * @param expression
     * @return
     */
    private static List<String> toInFixExpressionList(String expression) {
        if (StringUtils.isEmpty(expression)) {
            log.info("表达式为空");
            return Lists.newArrayList();
        }
        List<String> list = Lists.newArrayList();
        int index = 0;
        String tempStr;
        char tempChar;
        int length = expression.length();
        while (index < length) {
            tempChar = expression.charAt(index);
            tempStr = String.valueOf(tempChar);
            /*如果当前字符也不是数字字符，结束本次循环*/
            if (isNumber(tempChar)) {
                while (true) {
                    /*如果指针已到字符串的尾部，则不再进行数据的循环*/
                    if (index >= expression.length() - 1) {
                        break;
                    }
                    /*获取字符串当前指针的下一位的字符*/
                    tempChar = expression.charAt(index + 1);
                    /*如果该字符是不属于数据，则放弃循环*/
                    if (!isNumber(tempChar)) {
                        break;
                    }
                    /*如果下一位不是数字，则该句代码不会执行，故指针仍指向当前位置*/
                    /*如果当前指针的下一位仍然属于数据，进行字符串相加，并指针下移*/
                    tempStr += expression.charAt(++index);
                }
            }
            /*将指针下移*/
            index++;
            /*如果当前字符串不为空，将当字符串加入列表*/
            list.add(tempStr);
        }
        return list;
    }

    /**
     * 判断一个字符是否是数字的一部分，包含数字和小数点
     *
     * @param ch
     * @return
     */
    private static boolean isNumber(char ch) {
        return (ch >= 48 && ch <= 57) || ch == '.';
    }

}

class Operation {
    /*四则运算符优先级规则*/
    private final static int ADD = 1;
    private final static int SUB = 1;
    private final static int MUL = 2;
    private final static int DIV = 2;
    /*四则运算符表达式*/
    private final static String ADD_EXPRESSION = "+";
    private final static String SUB_EXPRESSION = "-";
    private final static String MUL_EXPRESSION = "*";
    private final static String DIV_EXPRESSION = "/";

    /**
     * 获取运算符号的优先级
     *
     * @param tempChar
     * @return
     */
    public static int getPriority(String tempChar) {

        int res;
        switch (tempChar) {
            case ADD_EXPRESSION:
                res = ADD;
                break;
            case SUB_EXPRESSION:
                res = SUB;
                break;
            case MUL_EXPRESSION:
                res = MUL;
                break;
            case DIV_EXPRESSION:
                res = DIV;
                break;
            default:
                throw new RuntimeException("运算符号错误");
        }
        return res;
    }

    /**
     * 计算运算结果
     *
     * @param num1      操作数
     * @param num2      被操作数
     * @param operation 运算符号
     * @return
     */
    public static double calculate(double num1, double num2, String operation) {
        double res;
        switch (operation) {
            case ADD_EXPRESSION:
                res = num2 + num1;
                break;
            case SUB_EXPRESSION:
                res = num2 - num1;
                break;
            case MUL_EXPRESSION:
                res = num2 * num1;
                break;
            case DIV_EXPRESSION:
                res = num2 / num1;
                break;
            default:
                throw new RuntimeException("运算符号错误");
        }
        return res;
    }
}
