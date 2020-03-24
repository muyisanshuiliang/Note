package com.example.demo.apache.commons;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author yl
 * @Date 2020/2/20 21:04
 * @description:
 */
@Slf4j
public class CollectionsUtilsDemo {
    public static void main(String[] args) {
        List<String> firstList = getFirstList();
        List<String> emptyList = getEmptyList();

        if (CollectionUtils.isEmpty(emptyList)) {
            log.info("emptyList is null");
        }

        List<String> secondList = getSecondList();
        boolean containsAll = CollectionUtils.containsAll(firstList, secondList);
        if (containsAll) {
            log.info("firstList contains all secondList!");
        } else {
            log.info("firstList not contains all secondList!");
        }
        List<String> thirdList = getThirdList();
        if (CollectionUtils.containsAll(firstList, thirdList)) {
            log.info("firstList contains all thirdList!");
        } else {
            log.info("firstList not contains all thirdList!");
        }

        if (CollectionUtils.containsAny(firstList, secondList)) {
            log.info("firstList contains any secondList!");
        } else {
            log.info("firstList not contains any secondList!");
        }
        if (CollectionUtils.containsAny(firstList, thirdList)) {
            log.info("firstList contains any thirdList!");
        } else {
            log.info("firstList not contains any thirdList!");
        }
        List<String> forthList = getForthList();
        if (CollectionUtils.containsAny(firstList, forthList)) {
            log.info("firstList contains any forthList!");
        } else {
            log.info("firstList not contains any forthList!");
        }
        String[] temp = {"A","B"};
        System.out.println(CollectionUtils.containsAny(firstList, temp));
        System.out.println(CollectionUtils.containsAny(firstList, "E","F"));

        List<String> strings = (List)CollectionUtils.retainAll(firstList, thirdList);
        System.out.println(strings);

    }


    private static List<String> getFirstList() {
        List<String> temp = Lists.newArrayList();
        temp.add("A");
        temp.add("B");
        temp.add("C");
        temp.add("D");
        return temp;
    }

    private static List<String> getSecondList() {
        List<String> temp = Lists.newArrayList();
        temp.add("B");
        temp.add("C");
        temp.add("D");
        return temp;
    }

    private static List<String> getThirdList() {
        List<String> temp = Lists.newArrayList();
        temp.add("B");
        temp.add("C");
        temp.add("D");
        temp.add("E");
        return temp;
    }

    private static List<String> getForthList() {
        List<String> temp = Lists.newArrayList();
        temp.add("E");
        temp.add("F");
        temp.add("G");
        temp.add("H");
        return temp;
    }

    private static List<String> getEmptyList() {
        return Lists.newArrayList();
    }

    private static List<String> getFixedLengthList(int length) {
        return new ArrayList<>(length);
    }
}
