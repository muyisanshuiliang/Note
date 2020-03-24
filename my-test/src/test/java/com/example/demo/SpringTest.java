package com.example.demo;

import com.example.demo.spring.SayHello;
import com.example.demo.spring.condition.ShowCmd;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author yl
 * @Date 2019/12/18 15:26
 * @description:
 */
@SpringBootTest
public class SpringTest {
    @Autowired
    private SayHello sayHello;

    @Autowired
    private SayHello sayHello1;

    @Qualifier("showCmd")
    @Autowired
    private ShowCmd showCmd;

    @Test
    public void test1(){
        System.out.println(sayHello.sayHello("java"));
    }

    @Test
    public void test2(){
        System.out.println(showCmd.showCmd());
    }

    @Test
    public void test3(){
        /*只注入了一个bean,*/
        System.out.println(sayHello);
        System.out.println(sayHello1);
    }
}
