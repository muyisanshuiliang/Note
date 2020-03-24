package genericdemo;

/**
 * @Author yl
 * @Date 2019/12/24 14:06
 * @description:
 */
public class GenericInterfaceImpl1<T> implements GenericInterface<T> {
    private T data;
    private void setData(T data) {
        this.data = data;
    }
    @Override
    public T getData() {
        return data;
    }

    public static void main(String[] args) {
        GenericInterfaceImpl1<String> genericInterfaceImpl1 = new GenericInterfaceImpl1<>();
        genericInterfaceImpl1.setData("Generic Interface1");
        System.out.println(genericInterfaceImpl1.getData());
    }
}
