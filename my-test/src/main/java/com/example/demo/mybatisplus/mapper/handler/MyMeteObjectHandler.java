package com.example.demo.mybatisplus.mapper.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * @author yanglang@cdzhnf.com
 * @date 2019/12/10 9:36
 * @description
 */
@Component
public class MyMeteObjectHandler implements MetaObjectHandler {

    /*配置公共字段自动填充功能*/
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("createTime")) {
            Object value = getFieldValByName("createTime", metaObject);
            Calendar instance = Calendar.getInstance();
            Date time = instance.getTime();
            if (ObjectUtils.isNull(value)) {
                setFieldValByName("createTime", time, metaObject);
            }
        }
        if (metaObject.hasSetter("updateTime")) {
            Object value = getFieldValByName("updateTime", metaObject);
            Calendar instance = Calendar.getInstance();
            Date time = instance.getTime();
            if (ObjectUtils.isNull(value)) {
                setFieldValByName("updateTime", time, metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("updateTime")) {
            Object value = getFieldValByName("updateTime", metaObject);
            Calendar instance = Calendar.getInstance();
            Date time = instance.getTime();
            if (ObjectUtils.isNull(value)) {
                setFieldValByName("updateTime", time, metaObject);
            }
        }
    }
}
