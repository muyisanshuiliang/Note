package com.example.demo.apache.commons;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author yl
 * @Date 2020/2/19 17:39
 * @description:
 */
public class BeanUtilsDemo {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        User user = new User();
        user.setFather("zhang jack");
        user.setMother("zhao jack");
        BeanUtils.setProperty(user, "name", "Jack");
        BeanUtils.setProperty(user, "id", 21);

        String name = BeanUtils.getProperty(user, "name");
        System.out.println("赋值后的对象：" + user);

        User user1 = (User) BeanUtils.cloneBean(user);
        System.out.println("cloneBean对象" + user1);

        Map<String, String> describe = BeanUtils.describe(user);
        Set<Map.Entry<String, String>> entries = describe.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
            System.out.println(key + "=" + value);
        }

        describe.put("testKey", "testValue");

        User user2 = new User();
        BeanUtils.populate(user2, describe);
        System.out.println("populate后的对象：" + user2);
//        describe.clear();

        User user3 = new User();
        describe.remove("id");
        BeanUtils.populate(user3, describe);
        System.out.println(user3);

        User user4 = new User();
        BeanUtils.copyProperties(user4, user);
        System.out.println(user4);

        String[] names = BeanUtils.getArrayProperty(user, "name");
        for (String item : names) {
            System.out.println(item);
        }

        String[] subject = {"生物", "语文", "数学", "英语", "地理"};
        user.setSubject(subject);
        String[] subjects = BeanUtils.getArrayProperty(user, "subject");
        for (String item : subjects) {
            System.out.println(item);
        }

        String subjectName = BeanUtils.getSimpleProperty(user, "name");
        System.out.println(subjectName);

        subjectName = BeanUtils.getSimpleProperty(user, "subject");
        System.out.println(subjectName);


        subjectName = BeanUtils.getIndexedProperty(user, "subject[0]");
        System.out.println(subjectName);

        subjectName = BeanUtils.getIndexedProperty(user, "subject", 1);
        System.out.println(subjectName);

        Map<String, String> contactDetails = Maps.newHashMap();
        contactDetails.put("1", "13550124568");
        contactDetails.put("2", "13550124567");
        contactDetails.put("3", "13550124566");
        contactDetails.put("4", "13550124565");
        user.setContactDetails(contactDetails);

        String mappedProperty = BeanUtils.getMappedProperty(user, "contactDetails(2)");
        System.out.println(mappedProperty);

        mappedProperty = BeanUtils.getMappedProperty(user, "contactDetails", "1");
        System.out.println(mappedProperty);
    }
}


