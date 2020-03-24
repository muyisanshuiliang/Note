package com.example.demo.comport.exception;

import com.irrigation.icl.exception.ContextRuntimeException;

/**
 * @Author yl
 * @Date 2019/12/31 15:44
 * @description:
 */
public class NotAerielPortException extends Exception {

    public NotAerielPortException() {
        throw new ContextRuntimeException("端口指向设备不是串口类型");
    }
}
