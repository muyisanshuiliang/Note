package com.example.demo.mybatisplus.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @Author yl
 * @Date 2019/12/16 16:17
 * @description:
 */
public class DeleteAllMethod extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        /*执行的mysql语句*/
        String sql = "delete from " + tableInfo.getTableName();
        /*mapper接口的方法名*/
        String methodName = "deleteAll";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, mapperClass);
        return addDeleteMappedStatement(mapperClass,methodName,sqlSource);
    }
}
