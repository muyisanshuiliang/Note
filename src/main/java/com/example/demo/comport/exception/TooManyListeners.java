package com.example.demo.comport.exception;

import com.irrigation.icl.exception.ContextRuntimeException;

/**
 * @Author yl
 * @Date 2019/12/31 15:51
 * @description:
 */
public class TooManyListeners extends Exception {
    public TooManyListeners() {
        throw new ContextRuntimeException("监听类对象过多");
    }
}
