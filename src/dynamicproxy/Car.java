package dynamicproxy;

public class Car implements Vehicle {
    public void start(String name) {
        System.out.println (name + ":" + "start travel");
    }
}
