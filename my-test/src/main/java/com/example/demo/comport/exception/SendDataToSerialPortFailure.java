package com.example.demo.comport.exception;

import com.irrigation.icl.exception.ContextRuntimeException;

/**
 * @Author yl
 * @Date 2019/12/31 15:50
 * @description:
 */
public class SendDataToSerialPortFailure extends Exception {
    public SendDataToSerialPortFailure() {
        throw new ContextRuntimeException("向串口发送数据失败");
    }
}
