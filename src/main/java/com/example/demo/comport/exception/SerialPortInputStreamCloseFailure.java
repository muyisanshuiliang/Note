package com.example.demo.comport.exception;

import com.irrigation.icl.exception.ContextRuntimeException;

/**
 * @Author yl
 * @Date 2019/12/31 15:51
 * @description:
 */
public class SerialPortInputStreamCloseFailure extends Exception {
    public SerialPortInputStreamCloseFailure() {
        throw new ContextRuntimeException("关闭串口对象输入流出错");
    }
}
