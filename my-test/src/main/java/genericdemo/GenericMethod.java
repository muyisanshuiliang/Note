package genericdemo;

/**
 * @Author yl
 * @Date 2019/12/24 14:11
 * @description:
 */
public class GenericMethod {

    private static int add(int a, int b) {
        System.out.println(a + " + " + b + " = " + (a + b));
        return a + b;
    }

    private static <T> T genericAdd(T a, T b) {
        System.out.println(a + " + " + b + " = " + a + b);
        return a;
    }

    static class Animal {
        @Override
        public String toString() {
            return "Animal";
        }
    }

    static class Dog extends Animal {
        @Override
        public String toString() {
            return "Dog";
        }
    }

    static class Fruit {
        @Override
        public String toString() {
            return "Fruit";
        }
    }

    static class GenericClass<T> {

        public void show01(T t) {
            System.out.println(t.toString());
        }

        public <T> void show02(T t) {
            System.out.println(t.toString());
        }

        public <K> void show03(K k) {
            System.out.println(k.toString());
        }
    }


    public static void main(String[] args) {
        GenericMethod.add(1, 2);
        GenericMethod.<String>genericAdd("a", "b");

        Animal animal = new Animal();
        Dog dog = new Dog();
        Fruit fruit = new Fruit();
        GenericClass<Animal> genericClass = new GenericClass<>();
        //泛型类在初始化时限制了参数类型
        genericClass.show01(dog);
//        genericClass.show01(fruit);

        //泛型方法的参数类型在使用时指定
        genericClass.show02(dog);
        genericClass.show02(fruit);

        genericClass.show03(animal);
        genericClass.<Animal>show03(dog);
        genericClass.show03(fruit);
//        genericClass.<Dog>show03(animal);
    }
}
