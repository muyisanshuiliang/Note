package com.example.demo;

import com.example.demo.executor.MyTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author yl
 * @Date 2019/12/17 11:05
 * @description:
 */
@SpringBootTest
public class ExecutorTest {

    @Qualifier("ThreadPoolExecutor")
    @Autowired
    private ThreadPoolExecutor executor;

    @Test
    public void test() {

        for (int i = 0; i < 40; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getTaskCount());
        }
        executor.shutdown();
    }
}
