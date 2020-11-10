package java.base.a11proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-04-17
 */
public class Client {
    public static void main(String[] args) {
        Subject realSubject = new RealSubject();
        InvocationHandler handler = new ProxyHandler(realSubject);
        Subject subject = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject
                .getClass().getInterfaces(), handler);
        System.out.println("代理类名: " + subject.getClass().getName());
        subject.print();
    }
}
