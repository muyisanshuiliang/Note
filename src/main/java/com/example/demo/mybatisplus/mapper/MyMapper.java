package com.example.demo.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Author yl
 * @Date 2019/12/16 16:31
 * @description:
 */
public interface MyMapper<T> extends BaseMapper<T> {

    /**
     * 删除所有的
     * @return  影响行数
     */
    int deleteAll();
}
