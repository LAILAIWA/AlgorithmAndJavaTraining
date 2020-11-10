package java.base.a7stream.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题实现—通知器
 */
public class Feed implements Subject {
    //观察者集合
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }
}
