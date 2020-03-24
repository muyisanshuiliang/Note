package com.example.demo.comport.exception;

import com.irrigation.icl.exception.ContextRuntimeException;

/**
 * @Author yl
 * @Date 2019/12/31 15:50
 * @description:
 */
public class PortInUseException extends Exception {
    public PortInUseException() {
        throw new ContextRuntimeException("端口已被占用");
    }
}
