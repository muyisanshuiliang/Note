package com.example.demo.sgg.datastructure;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @Author yl
 * @Date 2020/1/8 17:47
 * @description:
 */
@Slf4j
public class PolandDemo {

    /**
     * 数字匹配正则表达式
     */
    private final static String NUMBER_MATCHES_EXPRESSION = "\\d+[\\.\\d+]{0,}";

    public static void main(String[] args) {
        String expresion = "4*(3+2)-5";
        List<String> list = toPrefixExpressionList(expresion);
        System.out.println("数据表达式逆向字符串列表：" + list);
        List<String> prefixExpression = getPrefixExpression(list);
        System.out.println("前缀表达式列表：" + prefixExpression);
        Collections.reverse(prefixExpression);
        System.out.println("反转后的表达式："+ prefixExpression);
        double calculationResults = getCalculationResults(prefixExpression);
        System.out.printf("表达式 %s = %f\n", expresion,calculationResults);

        expresion = "1+((2+3)*4)-5";
        list = toPrefixExpressionList(expresion);
        System.out.println("数据表达式逆向字符串列表：" + list);
        prefixExpression = getPrefixExpression(list);
        System.out.println("前缀表达式列表：" + prefixExpression);
        Collections.reverse(prefixExpression);
        System.out.println("反转后的表达式："+ prefixExpression);
        calculationResults = getCalculationResults(prefixExpression);
        System.out.printf("表达式 %s = %f\n", expresion,calculationResults);


        expresion = "7.89+5.45*(2.34+5.78)*(4.69-4.57)/(12.98-5.12)";
        list = toPrefixExpressionList(expresion);
        System.out.println("数据表达式逆向字符串列表：" + list);
        prefixExpression = getPrefixExpression(list);
        System.out.println("前缀表达式列表：" + prefixExpression);
        Collections.reverse(prefixExpression);
        System.out.println("反转后的表达式："+ prefixExpression);
        calculationResults = getCalculationResults(prefixExpression);
        System.out.printf("表达式 %s = %f\n", expresion,calculationResults);
    }


    /**
     * 根据前缀表达式获取计算结果
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
        double num2;//[5, 4, 3, 2, +, ×, -, 1, +]
        /*循环中缀表达式，一旦遇到非数字字符串，则从结果栈中取出两个数据进行运算操作，并将执行结果压入结果栈*/
        for (String item : suffixExpression) {
            /*如果为数字，则直接将数据压入结果栈中*/
            if (item.matches(NUMBER_MATCHES_EXPRESSION)) {
                resultStack.push(Double.parseDouble(item));
                continue;
            }
            try {
                /*注意数据的操作顺序*/
                /*先弹出的数据为被操作数*/
                num2 = resultStack.pop();
                /*后弹出的数据为操作数*/
                num1 = resultStack.pop();
                double calculate = Operation.calculate(num1, num2, item);
                resultStack.push(calculate);
            } catch (Exception e) {
                throw new RuntimeException("前缀表达式错误！");
            }
        }
        try {
            /*最终结果栈中只存在一个数据，那就是计算结果数据*/
            res = resultStack.pop();
        } catch (Exception e) {
            throw new RuntimeException("前缀表达式错误！");
        }
        return res;
    }

    /**
     * 根据中缀表达式获取后缀表达式
     *
     * @param list
     * @return
     */
    private static List<String> getPrefixExpression(List<String> list) {
        if (list.size() == 0) {
            log.info("表达式为空");
            return Lists.newArrayList();
        }
        /*用于存储操作符号栈*/
        Stack<String> operationStack = new Stack<>();
        Stack<String> tempStack = new Stack<>();

        for (String item : list) {
            /*如果是数字，则直接入列表*/
            if (item.matches(NUMBER_MATCHES_EXPRESSION)) {
                tempStack.push(item);
                continue;
            }
            /*如是果是右括号，则直接入操作符号栈*/
            if (item.equals(")")) {
                operationStack.push(item);
                continue;
            }
            /*如果是左括号*/
            if (item.equals("(")) {
                /*循环查看栈顶元素是否是右括号，或者栈内元素为空*/
                while (true) {
                    if (operationStack.size() == 0 || ")".equals(operationStack.peek())) {
                        break;
                    }
                    /*依次弹出栈顶元素，放入数据列表*/
                    tempStack.push(operationStack.pop());
                }
                /*如果栈内还有元素，将栈顶的右括号弹出弃掉*/
                if (operationStack.size() != 0) {
                    operationStack.pop();
                }
                continue;
            }
            /*如果是操作符号*/
            /*如果操作符号栈为空或右括号，则将操作符号直接入栈*/
            if (operationStack.size() == 0 || ")".equals(operationStack.peek())) {
                operationStack.push(item);
                continue;
            }
            /*如果当前操作符优先级小于操作符号栈顶元素*/
            /*依次查看栈顶元素，直至栈空或者栈顶元素为左括号*/
            while (true) {
                if (operationStack.size() == 0 || ")".equals(operationStack.peek())) {
                    /*将当前操作符压入操作符栈*/
                    operationStack.push(item);
                    break;
                }
                /*如果当前操作符的优先级大于或等于操作符号栈顶元素，则亦直接将当前操作符压入栈*/
                if (Operation.getPriority(item) >= Operation.getPriority(operationStack.peek())) {
                    operationStack.push(item);
                    break;
                }
                /*依次弹出栈顶元素添加到数据列表*/
                tempStack.push(operationStack.pop());
            }
        }

        /*将符号栈内的元素依次添加到数据列表，获取最终的后缀表达式*/
        while (operationStack.size() != 0) {
            tempStack.push(operationStack.pop());
        }
        List<String> strings = Lists.newArrayList();
        while (tempStack.size() != 0) {
            strings.add(tempStack.pop());
        }
        return strings;
    }

    /**
     * 将中缀表达式转换成list
     *
     * @param expresion
     * @return
     */
    private static List<String> toPrefixExpressionList(String expresion) {
        if (StringUtils.isEmpty(expresion)) {
            log.info("表达式为空");
            return Lists.newArrayList();
        }
        int index = expresion.length() - 1;

        List<String> res = Lists.newArrayList();
        char tempCh;
        String tempStr;
        while (index >= 0) {
            tempCh = expresion.charAt(index--);
            tempStr = String.valueOf(tempCh);
            if (isNumber(tempCh)) {
                while (true) {
                    if (index < 0 || !isNumber(expresion.charAt(index))) {
                        break;
                    }
                    tempStr += String.valueOf(expresion.charAt(index--));
                }
                tempStr = new StringBuffer(tempStr).reverse().toString();
            }
            res.add(tempStr);
        }
        return res;
    }

    /**
     * 判断一个字符串是否为数字
     *
     * @param ch
     * @return
     */
    private static boolean isNumber(char ch) {
        return (ch >= 48 && ch <= 57) || ch == '.';
    }
}
