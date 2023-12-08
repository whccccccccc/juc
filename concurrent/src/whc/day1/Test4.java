package whc.day1;

/**
 * synchronized 解决方案
 * ABC 三个线程
 * A先拿到钥匙可以操作资源
 * 然后操作系统分配的时间片到了 A没保存数据
 * 时间片分给了BC线程 但是BC线程因为没用钥匙 所以阻塞状态
 * 这时候再次分配给A线程的时候  A继续操作 直至释放锁
 */
public class Test4 {

//    static int counter = 0;
//    static final Object lock = new Object();
//
//    public static void main(String[] args) throws InterruptedException {
//        Thread thread1 = new Thread(() -> {
//            synchronized (lock) {
//                for (int i = 0; i < 5000; i++) {
//                    counter++;
//                }
//            }
//        });
//
//        Thread thread2 = new Thread(() -> {
//            synchronized (lock) {
//                for (int i = 0; i < 5000; i++) {
//                    counter--;
//                }
//            }
//        });
//
//        thread1.start();
//        thread2.start();
//        thread1.join();
//        thread2.join();
//        System.out.println(counter);
//    }



    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
        Thread thread1 = new Thread(room::increment);

        Thread thread2 = new Thread(room::decrement);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(room.getCount());
    }

}

class Room {

    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized void decrement() {
        count--;
    }

    public synchronized int getCount() {
        return count;
    }
}
