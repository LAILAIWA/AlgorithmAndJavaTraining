package categories.java.a7stream.observer;

/**
 * 主题—通知接口
 */
public interface Subject {
    /**
     * 注册观察者
     * @param o 观察者
     */
    void registerObserver(Observer o);

    /**
     * 通知观察者
     * @param tweet 内容
     */
    void notifyObservers(String tweet);
}
