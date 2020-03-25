package com.example.demo.base.point;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yl
 * @Date 2020/3/25 10:44
 * @description:
 */
public class MapDemo {
    public static void main(String[] args) {
        System.out.println("===========test IfAbsent===========");
        testIfAbsent();
        System.out.println("===========test forEach===========");
        testForEach();
        System.out.println("===========test ComputeIfPresent===========");
        testComputeIfPresent();
        System.out.println("===========test ComputeIfAbsent===========");
        testComputeIfAbsent();
        System.out.println("===========test Remove===========");
        testRemove();
        System.out.println("===========test GetOrDefault===========");
        testGetOrDefault();
        System.out.println("===========test Merge===========");
        testMerge();
    }

    private static void testIfAbsent() {
        HashMap<String, String> map = Maps.newHashMap();
        String oldValue = map.putIfAbsent("AAA", "BBB");
        System.out.println("old value: " + oldValue);
        System.out.println("new value: " + map.get("AAA"));
        /*此时key对应的value存在，忽略put操作*/
        oldValue = map.putIfAbsent("AAA", "CCC");
        System.out.println("old value: " + oldValue);
        System.out.println("new value: " + map.get("AAA"));
    }

    private static void testForEach() {
        Map<String, String> map = Maps.newHashMap();
        map.putIfAbsent("key1", "value1");
        map.putIfAbsent("key2", "value1");
        map.putIfAbsent("key3", "value1");
        map.forEach((key, value) -> System.out.println(key + ":" + value));
    }

    private static void testComputeIfPresent() {
        Map<String, String> map = Maps.newHashMap();
        map.putIfAbsent("key1", "value1");

        // 如果存在则计算：先判断key是否存在，如果key存在，将BiFunction计算的结果作为key的新值重新put进去
        map.computeIfPresent("key1", (key, value) -> key + "=" + value);
        String value = map.get("key1");
        System.out.println(value);

        // 如果计算的结果为null，相当于从map中移除
        map.computeIfPresent("key1", (k, v) -> null);
        boolean contain = map.containsKey("key1");
        System.out.println(contain);
    }

    private static void testComputeIfAbsent() {
        // 如果key不存在则将计算的结果put进去
        Map<String, String> map = Maps.newHashMap();
        map.computeIfAbsent("key2", v -> "value1");
        boolean contain = map.containsKey("key2");
        System.out.println(contain);
        System.out.println(map.get("key2"));
//        map.computeIfPresent("key2",(key,value) -> null);
//        contain = map.containsKey("key2");
//        System.out.println(contain);
        map.computeIfAbsent("key2", v -> "value2");
        contain = map.containsKey("key2");
        System.out.println(contain);
        System.out.println(map.get("key2"));
    }

    private static void testRemove() {
        /*只有key-value同时满足条件时才会删除*/
        Map<String, String> map = Maps.newHashMap();
        map.putIfAbsent("key1", "value1");
        boolean result = map.remove("key1", "value2");
        System.out.println(result);
    }

    private static void testGetOrDefault() {
        // 如果key不存在，则返回默认值
        Map<String, String> map = Maps.newHashMap();
        String value = map.getOrDefault("key1", "default value");
        System.out.println(value);
        map.putIfAbsent("key1", "value1");
        value = map.getOrDefault("key1", "default value");
        System.out.println(value);
    }

    private static void testMerge() {
        // 合并：将BiFunction的结果作为key的新值,key值存在与否都可以进行操作
        Map<String, String> map = Maps.newHashMap();
        map.put("key1", "value1");
        map.merge("key1", "newValue", (value, newValue) -> value + "-" + newValue);
        String value = map.get("key1");
        System.out.println(value);
    }
}
