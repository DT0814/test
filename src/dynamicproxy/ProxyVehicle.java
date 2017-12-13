package dynamicproxy;

import java.util.HashMap;
import java.util.Map;

public class ProxyVehicle implements Vehicle {
    private Map<String, Integer> map = new HashMap<String, Integer> ();
    private Vehicle vehicle;

    public ProxyVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void start(String name) {
        if (map.containsKey (name)) {
            Integer num = map.get (name);
            vehicle.start (name);
            System.out.println (name + ":行驶了" + num + "次");
            map.put (name, ++num);
        } else {
            map.put (name, 1);
            vehicle.start (name);
            System.out.println (name + ":行驶了" + 1 + "次");
        }
    }
}
