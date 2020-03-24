package com.example.demo.mybatisplus.model;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import com.example.demo.mybatisplus.mapper.handler.JSONTypeHandler;
import lombok.Data;
import lombok.experimental.Delegate;

import java.util.Date;

@Data
@TableName(value = "user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String name;

    private Integer number;

    /*空字段更新入库*/
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String address;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(typeHandler = JSONTypeHandler.class)
    private JSONObject detailInfo;
}
