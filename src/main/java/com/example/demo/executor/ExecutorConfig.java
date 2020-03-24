package com.example.demo.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author yl
 * @Date 2019/12/13 9:37
 * @description:
 */
@Slf4j
@Configuration
public class ExecutorConfig {

    @Bean(value = "ThreadPoolExecutor")
    public ThreadPoolExecutor asyncServiceExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(30));
        return executor;
    }
}
