package com.example.demo.comport;

import com.example.demo.comport.exception.*;
import gnu.io.SerialPort;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * @Author yl
 * @Date 2019/12/31 16:22
 * @description:
 */
public class Test {
    /**
     * 设备总数
     */
    private final static int DEVICE_NUMBER = 15;
    /**
     * CRC16校验起始位
     */
    private final static int CHECK_START_BIT = 3;
    /**
     * CRC16校验位总数
     */
    private final static int CHECK_DATA_BIT = 106;
    /**
     * 设备信息长度
     */
    private final static int DEVICE_INFO_LENGTH = 7;

    /**
     * 中间变量：头部信息，固定值
     * 长度固定值108（6C） = 1（功能码01，固定值）+105（数据位）+2（CRC16校验值）
     * 功能码固定值：01
     */
    private final static String HEAD = "51156C01";

    public static void main(String[] args) throws PortInUseException, SerialPortParameterFailure, NoSuchPortException, NotAerielPortException, SerialPortOutputStreamCloseFailure, SendDataToSerialPortFailure, ReadDataFromSerialPortFailure, SerialPortInputStreamCloseFailure {

        SerialPort serialPort = SerialTool.openPort("COM1", 115200);

        byte[] head = hexStrToBinaryStr(HEAD);
        /*指定data数据长度固定值：111*/
        byte[] data = new byte[111];
        int index = 0;
        index = appendData(data, head, index, false);
        StringBuilder stringBuilder;
        byte[] tempBytes;
        for (int i = 0; i < DEVICE_NUMBER; i++) {
            /*模拟设备数据*/
            stringBuilder = new StringBuilder();
            /*模拟开关量*/
            if (i % 2 == 0) {
                stringBuilder.append("01");
            } else {
                stringBuilder.append("00");
            }
            /*模拟水位数据*/
            stringBuilder.append(handleData());
            /*模拟流量数据*/
            stringBuilder.append(handleData1());
            tempBytes = hexStrToBinaryStr(stringBuilder.toString());
            index = appendData(data, tempBytes, index, true);
        }
        /*获取校验码*/
        String crc = CRC16Util.getCrc(CRC16Util.calcCrc16(data, CHECK_START_BIT, CHECK_DATA_BIT));
        tempBytes = hexStrToBinaryStr(crc);
        appendData(data, tempBytes, index, false);
        SerialTool.sendToPort(serialPort, data);
    }

    private static int appendData(byte[] data, byte[] appendData, int index, boolean isDeviceInfo) {
        /*如果是设备信息为空，指针后移7位，为下一个设备存放数据做准备*/
        if (ObjectUtils.isEmpty(appendData)) {
            if (isDeviceInfo) {
                index += DEVICE_INFO_LENGTH;
            }
            return index;
        }
        for (int i = 0, count = appendData.length; i < count; i++) {
            data[index] = appendData[i];
            index++;
        }
        return index;
    }

    public static byte[] hexStrToBinaryStr(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            return null;
        }
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        int index = 0;

        byte[] bytes = new byte[len / 2];
        while (index < len) {
            String sub = hexString.substring(index, index + 2);
            bytes[index / 2] = (byte) Integer.parseInt(sub, 16);
            index += 2;
        }
        return bytes;
    }

    private static String handleData() {
        String data = Integer.toHexString(4325345).toUpperCase();
        return autoGenericCode(data, 6);
    }

    private static String handleData1() {
        String data = Integer.toHexString(234500).toUpperCase();
        return autoGenericCode(data, 6);
    }

    private static String autoGenericCode(String code, int num) {
        String data = "000000";
        if (StringUtils.isEmpty(code)) {
            return data;
        }
        int length = code.length();
        if (length >= num) {
            return code.substring(0, 6);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, j = num - length; i < j; i++) {
            stringBuilder.append("0");
        }
        stringBuilder.append(code);
        return stringBuilder.toString();
    }
}
