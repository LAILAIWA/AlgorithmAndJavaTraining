package java.base.a14lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CommonTest {

    public static void main(String[] args)throws Exception{
        Lock lock = new ReentrantLock();
        lock.lock();
        try{
            //不要将获取锁的过程放入try块，若获取锁时发生异常，异常抛出也会导致锁的释放
        }finally {
            //保证释放
            lock.unlock();
        }
    }
}
