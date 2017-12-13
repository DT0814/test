package demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InterHandler implements InvocationHandler {
    private Object obj;

    public InterHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke (obj, args);
    }
}
