package com.example.demo.spring.condition;

/**
 * @Author yl
 * @Date 2019/12/18 15:33
 * @description:
 */
public class WinShowCmd implements ShowCmd {
    @Override
    public String showCmd() {
        return "dir";
    }
}
