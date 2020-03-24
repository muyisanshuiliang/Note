package genericdemo;

/**
 * @Author yl
 * @Date 2019/12/24 14:20
 * @description:
 */
public class TypeLimitForMethod {

    /**
     * 计算最小值
     * 如果要实现这样的功能就需要对泛型方法的类型做出限定
     */
    public static <T extends Comparable> T getMax(T a, T b) {
        return (a.compareTo(b) > 0) ? a : b;
    }

    /**
     * 限定类型使用extends关键字指定
     * 可以使类，接口，类放在前面接口放在后面用&符号分割
     * 例如：<T extends ArrayList & Comparable<T> & Serializable>
     */
    public static <T extends Comparable<T>> T getMin(T a, T b) {
        return (a.compareTo(b) < 0) ? a : b;
    }

    public static void main(String[] args) {
        System.out.println(TypeLimitForMethod.getMin(2, 4));
        System.out.println(TypeLimitForMethod.getMin("a", "r"));
        System.out.println(TypeLimitForMethod.getMax(2, 4));
        System.out.println(TypeLimitForMethod.getMax("a", "r"));
    }
}
