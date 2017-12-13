package proxy;

import java.util.HashMap;
import java.util.Map;

public class ProxyVehicle implements Vehicle {
    private int num;
    private Vehicle vehicle;
    private Map<String, Integer> map = new HashMap<String, Integer> ();

    public ProxyVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void start(String name) {
        if (map.containsKey (name)) {
            Integer integer = map.get (name);
            System.out.println (name + "行驶了" + integer + "次");
            map.put (name, ++integer);
        } else {
            map.put (name, 1);
            Integer integer = map.get (name);
            System.out.println (name + "行驶了" + integer + "次");
            map.put (name, ++integer);
        }
        vehicle.start (name);
    }
}
