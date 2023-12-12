package whc.day6readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

//演示读写锁降级
public class Demo1 {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        //1.获取写锁
        writeLock.lock();
        System.out.println("atguigu");
        //2.获取读锁
        readLock.lock();
        System.out.println("---read");
        //3.释放写锁
        writeLock.unlock();
        //4.释放读锁
    }
}
