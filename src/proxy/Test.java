package proxy;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Car car = new Car ();
        ProxyVehicle proxyVehicle = new ProxyVehicle (car);
        String[] name = {"Tmo", "jack", "lala"};
        Random ra = new Random ();
        for (int i = 0; i < 100; i++) {
            int n = ra.nextInt (3);
            proxyVehicle.start (name[n]);
        }

    }
}
