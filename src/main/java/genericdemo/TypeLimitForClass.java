package genericdemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author yl
 * @Date 2019/12/24 14:24
 * @description:
 */
public class TypeLimitForClass<T extends List & Serializable> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String[] args) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("A");
        stringArrayList.add("B");
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(1);
        integerArrayList.add(2);
        integerArrayList.add(3);
        TypeLimitForClass<ArrayList> typeLimitForClass01 = new TypeLimitForClass<>();
        typeLimitForClass01.setData(stringArrayList);
        TypeLimitForClass<ArrayList> typeLimitForClass02 = new TypeLimitForClass<>();
        typeLimitForClass02.setData(integerArrayList);

        System.out.println(TypeLimitForMethod.getMin(typeLimitForClass01.getData().size(), typeLimitForClass02.getData().size()));
        System.out.println(TypeLimitForMethod.getMax(typeLimitForClass01.getData().size(), typeLimitForClass02.getData().size()));

    }

    public static <T extends Comparable<T>> T getMinListSize(T a, T b) {
        return (a.compareTo(b) < 0) ? a : b;
    }
    public static <T extends Comparable<T>> T getMaxListSize(T a, T b) {
        return (a.compareTo(b) > 0) ? a : b;
    }

}
