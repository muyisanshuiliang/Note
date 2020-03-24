package com.example.demo.string;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author yl
 * @Date 2019/12/30 13:59
 * @description:
 */
public class Test {
    public static void main(String[] args) {
//        String s = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010E600293100000000000000";
//        System.out.println(s.length());
//        s = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000000002931";
//        System.out.println(s.length());
//        s = "6C01000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000000002931";
//        System.out.println(s.length());
//        s = "01010010E50029300000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//        System.out.println(s.length());


        System.out.println(System.currentTimeMillis());
        Date date = new Date();
        date.setTime(1577808000662L);
        System.out.println(date.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.YEAR);
        System.out.println(i);
        System.out.println(date);


//        List<Integer> a = Lists.newArrayList();
//        List<Integer> b = Lists.newArrayList();
//        a.add(1);
//        a.add(2);
//        a.add(3);
//
//        b.add(3);
//        b.add(4);
//        b.add(5);
//        System.out.println(a);
//
//        Iterator<Integer> iterator = a.iterator();
//        while (iterator.hasNext()){
//            if(b.contains(iterator.next())){
//                continue;
//            }
//            iterator.remove();
//        }
//        System.out.println("最后：" + a);
//
//
//        StringBuilder stringBuilder = new StringBuilder("aaa");
//        System.out.println(stringBuilder.toString());
//
//
//        LinkedList<Integer> aaaa = Lists.newLinkedList();
//        aaaa.addLast(1);
//        aaaa.addLast(2);
//        aaaa.addLast(3);
//        System.out.println(aaaa);
//        Integer last = aaaa.removeLast();
//        last = last+1;
//        aaaa.addLast(last);
//        System.out.println(aaaa);

//        List<Integer> objects = Lists.newArrayList();


//        LinkedList<Integer> a = Lists.newLinkedList();
//        Integer integer = a.removeLast();
//        objects.remove((Integer) 1);
//
//        if(ObjectUtils.isEmpty(objects)){
//            System.out.println("机行业为空");
//        }
//
//        Integer[] item = {1,2,3,4,5,6};
//        System.out.println(Arrays.asList(item));
//        System.out.println(new JSONArray(Arrays.asList(item)));

        System.out.println("test:");
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.getTime());
        System.out.println(instance.get(Calendar.YEAR));
        instance.set(2020, 0, 1);
        Date time = instance.getTime();
        System.out.println(time);
        System.out.println(instance.get(Calendar.MONTH));
        System.out.println(instance.get(Calendar.DAY_OF_MONTH));

        instance.set(2020, 0, 1, 0, 0, 0);
        instance.set(Calendar.MILLISECOND, 0);
        System.out.println(instance.getTimeInMillis());
        System.out.println(instance.getTime());
        System.out.println(instance.getTimeInMillis() - 1);
        date.setTime(instance.getTimeInMillis() - 1);
        instance.setTime(date);
        System.out.println(instance.getTime());


        double value = 12548.235689;
        double addValue = 12548.235689;
        BigDecimal sum = new BigDecimal(value);
        System.out.println(sum.toString());
        BigDecimal add = sum.add(new BigDecimal(addValue));
        System.out.println(add.toString());
        BigDecimal divide = add.divide(new BigDecimal(10000));
        System.out.println(divide.toString());
        System.out.println(divide.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());

        System.out.println(System.currentTimeMillis());


        Map<Integer, String> map = Maps.newHashMap();
        System.out.println(map.get(null));

        LinkedList<Integer> integers = Lists.newLinkedList();
        integers.addLast(1);
        integers.addLast(5);
        System.out.println(integers);
        Integer integer = integers.getLast();
        integer = 7;
        integers.addLast(integer);
        System.out.println(integers);
        long count = integers.stream().distinct().count();
        System.out.println(count);
        integers.addLast(7);
        count = integers.stream().distinct().count();
        System.out.println(count);

        List<Integer> ss = Lists.newArrayList();
        System.out.println(ss.contains(null));


        System.out.println("==================");
        for (int j = 0; j < 10; j++) {
            StringBuffer sb = new StringBuffer();
            sb.append("0.");
            sb.append(j);
            sb.append("5");
            BigDecimal bdx = new BigDecimal(sb.toString());
            System.out.println(sb + " " + bdx.setScale(1, RoundingMode.HALF_EVEN));
        }

        System.out.println("==================");
        for (int j = 0; j < 10; j++) {
            StringBuffer sb = new StringBuffer();
            sb.append("0.");
            sb.append(j);
            sb.append("5");
            BigDecimal bdx = BigDecimal.valueOf(Double.parseDouble(sb.toString()));
            System.out.println(sb + " " + bdx.setScale(1, RoundingMode.HALF_EVEN));
        }


        Set<String> planUniqueInAddPlan = Sets.newHashSet();
        planUniqueInAddPlan.add("1");
        planUniqueInAddPlan.add("1");
        System.out.println(planUniqueInAddPlan);


        System.out.println("我是来要时间的");
        System.out.println("start time:" + new Date(1767196799999L));
        System.out.println("stop time:" + new Date(1584979200000L));


        System.out.println(isTimeCrossingOutEqual(1L, 5L, 5L, 8L));


//        testFlatMap();
        testOptional();
    }


    public static boolean isTimeCrossingOutEqual(long startTime, long endTime, long judgeStartTime, long judgeStopTime) {
        if (judgeStartTime == startTime) {
            return true;
        }
        if (judgeStopTime == endTime) {
            return true;
        }
        if (startTime < judgeStartTime && judgeStartTime < endTime) {
            return true;
        }
        if (startTime < judgeStopTime && judgeStopTime < endTime) {
            return true;
        }
        return false;
    }

    private static void testFlatMap() {
        System.out.println("========test flatMap========");
        List<Student> students = Lists.newArrayList(
                new Student(17, "Jack", Lists.newArrayList("12345", "12346", "12347", "12348")),
                new Student(17, "Tom", Lists.newArrayList("12355", "12356", "12357", "12358")),
                new Student(18, "Lily", Lists.newArrayList("12365", "12366", "12367", "12368")),
                new Student(19, "Lucy", Lists.newArrayList("12375", "12376", "12377", "12378")),
                new Student(16, "Toni", Lists.newArrayList("12385", "12386", "12387", "12388")),
                new Student(16, "Toni", null),
                null
        );

        List<String> collect = students.stream()
                .filter(item -> !ObjectUtils.isEmpty(item) && !CollectionUtils.isEmpty(item.getPhone()))
                .flatMap(student -> student.getPhone().stream()).collect(Collectors.toList());
        System.out.println(collect);
        List<List<String>> collect1 = students.stream()
                .filter(item -> !ObjectUtils.isEmpty(item) && !CollectionUtils.isEmpty(item.getPhone()))
                .map(student -> student.getPhone()).collect(Collectors.toList());
        System.out.println(collect1);

        System.out.println("========test concat========");

        List<String> collect2 = Stream.concat(
                students.stream()
                        .filter(item -> !ObjectUtils.isEmpty(item))
                        .map(Student::getName),
                students.stream()
                        .filter(item -> !ObjectUtils.isEmpty(item))
                        .map(item -> Optional.ofNullable(item.getPhone()).orElse(Lists.newArrayList()).toString())).collect(Collectors.toList());
        System.out.println(collect2);

        List<Integer> collect3 = Stream.concat(
                students.stream()
                        .filter(item -> !ObjectUtils.isEmpty(item))
                        .map(item -> Optional.ofNullable(item.getAge()).orElse(0)),
                students.stream()
                        .filter(item -> !ObjectUtils.isEmpty(item))
                        .map(item -> {
                            try {
                                Integer integer = Integer.valueOf(item.getName());
                                return integer;
                            } catch (Exception e) {
                                return 0;
                            }
                        })
        ).collect(Collectors.toList());
        System.out.println(collect3);


        System.out.println("========test reduce========");
        Optional<String> reduce = students.stream()
                .filter(item -> !ObjectUtils.isEmpty(item))
                .map(item -> item.getName())
                .reduce((before, after) -> before + "," + after);
        reduce.ifPresent(System.out::println);

        BigDecimal reduce1 = students.stream()
                .filter(item -> !ObjectUtils.isEmpty(item) && !ObjectUtils.isEmpty(item.getAge()))
                .map(item -> BigDecimal.valueOf(item.getAge()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(reduce1);

        int sum = students.stream()
                .filter(item -> !ObjectUtils.isEmpty(item) && !ObjectUtils.isEmpty(item.getAge()))
                .mapToInt(item -> item.getAge())
                .sum();
        System.out.println(sum);

        double average = students.stream()
                .filter(item -> !ObjectUtils.isEmpty(item) && !ObjectUtils.isEmpty(item.getAge()))
                .mapToInt(item -> item.getAge())
                .average()
                .getAsDouble();
        System.out.println(average);

        int max = students.stream()
                .filter(item -> !ObjectUtils.isEmpty(item) && !ObjectUtils.isEmpty(item.getAge()))
                .mapToInt(item -> item.getAge())
                .max()
                .getAsInt();
        System.out.println(max);

        int min = students.stream()
                .filter(item -> !ObjectUtils.isEmpty(item) && !ObjectUtils.isEmpty(item.getAge()))
                .mapToInt(item -> item.getAge())
                .min()
                .getAsInt();
        System.out.println(min);
    }

    private static void testOptional() {

        System.out.println("=========test Optional========");
        Optional<String> name = null;
        try {
            /*报NPE*/
            name = Optional.of(null);
        } catch (Exception e) {
            System.out.println("Exception name：" + e.toString());
        }
        name = Optional.of("ZhangSan");
        /*可以获取值*/
        if (name.isPresent()) {
            System.out.println(name.get());
        }
        /*可以定义NULL*/
        Optional<String> firstName = Optional.ofNullable(null);
        if (firstName.isPresent()) {
            System.out.println(firstName.get());
        } else {
            System.out.println("firstName not exist");
        }
        /*直接消费*/
        name.ifPresent(System.out::println);
        String first_name = firstName.orElse("first name");
        System.out.println(first_name);
        firstName = Optional.ofNullable(null);
        /*与orElse无差*/
        String secondName = firstName.orElseGet(() ->{

        });
        System.out.println(secondName);

    }
}

@Data
@AllArgsConstructor
class Student {
    int age;
    String name;
    List<String> phone;
}
