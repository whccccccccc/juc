package whc.day6readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    // volatile 表示数据不断会发生改变
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //放数据
    public void put(String key, Object value) throws InterruptedException {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);

            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入OK");
        } finally {
            lock.writeLock().unlock();
        }
    }

    //取数据
    public Object get(String key) throws InterruptedException {
        lock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);

            TimeUnit.MILLISECONDS.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取OK");
        } finally {
            lock.readLock().unlock();
        }
        return result;
    }
}

public class ReadWriteLockDemo {

    //写锁独占锁
    //读锁共享锁
    //一个资源可以被多个读线程访问,或者可以被一个写线程访问,但是不能同时存在读写线程,读写互斥,读读共享
    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();

        // 5个线程写
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                try {
                    myCache.put(tempInt + "", tempInt + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
        TimeUnit.MICROSECONDS.sleep(300);
        // 5个线程读
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                try {
                    myCache.get(tempInt + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
