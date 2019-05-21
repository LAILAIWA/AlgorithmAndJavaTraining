package categories.java.a11proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-04-17
 */
public class ProxyHandler implements InvocationHandler {

    private Object subject;

    public ProxyHandler(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-由代理接收-");
        System.out.println("categories.java.a11proxy: " + proxy.getClass().getName());
        System.out.println("method: " + method);
        Object result = method.invoke(subject,args);
        System.out.println("-由代理转发-");
        return result;
    }
}
