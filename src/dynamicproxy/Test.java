package dynamicproxy;


import java.lang.reflect.Proxy;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Car car = new Car ();
        Class<?> cls = car.getClass ();
        Vehiclehandler vehiclehandler = new Vehiclehandler (car);
        //ProxyVehicle proxyVehicle = (ProxyVehicle) Proxy.newProxyInstance (Vehiclehandler.class.getClassLoader (), cls.getInterfaces (), vehiclehandler);

        Vehicle vehicle = (Vehicle) Proxy.newProxyInstance (Vehiclehandler.class.getClassLoader (), cls.getInterfaces (), vehiclehandler);
        vehicle.start ("jack");
        ProxyVehicle proxyVehicle = new ProxyVehicle (vehicle);
        String[] name = {"Tmo", "jack", "lala"};
        Random ra = new Random ();
        for (int i = 0; i < 100; i++) {
            int n = ra.nextInt (3);
            proxyVehicle.start (name[n]);
        }
    }
}
