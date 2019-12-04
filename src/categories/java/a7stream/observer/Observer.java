package categories.java.a7stream.observer;

/**
 * 观察者接口
 */
public interface Observer {
    /**
     * 当接收到一条新闻时，会调用此方法
     * @param tweet 内容
     */
    void notify(String tweet);
}
