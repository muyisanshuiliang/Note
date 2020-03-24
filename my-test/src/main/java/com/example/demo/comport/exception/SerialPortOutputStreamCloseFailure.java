package com.example.demo.comport.exception;

import com.irrigation.icl.exception.ContextRuntimeException;

/**
 * @Author yl
 * @Date 2019/12/31 15:50
 * @description:
 */
public class SerialPortOutputStreamCloseFailure extends Exception {
    public SerialPortOutputStreamCloseFailure() {
        throw new ContextRuntimeException("关闭串口对象的输出流出错");
    }
}
