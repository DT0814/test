package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Vehiclehandler implements InvocationHandler {
    private Object tragit;

    public Vehiclehandler(Object tragit) {
        this.tragit = tragit;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke (tragit, args);
    }

}
