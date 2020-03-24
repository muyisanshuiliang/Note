package genericdemo;

/**
 * @Author yl
 * @Date 2019/12/24 14:08
 * @description:
 */
public class GenericInterfaceImpl2 implements GenericInterface<String> {
    @Override
    public String getData() {
        return "Generic Interface2";
    }

    public static void main(String[] args) {
        GenericInterfaceImpl2 genericInterfaceImpl2 = new GenericInterfaceImpl2();
        System.out.println(genericInterfaceImpl2.getData());
    }
}
