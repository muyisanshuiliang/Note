package com.example.demo.comport.exception;

import com.irrigation.icl.exception.ContextRuntimeException;

/**
 * @Author yl
 * @Date 2019/12/31 15:51
 * @description:
 */
public class ReadDataFromSerialPortFailure extends Exception {
    public ReadDataFromSerialPortFailure() {
        throw new ContextRuntimeException("从串口读取数据时出错");
    }
}
