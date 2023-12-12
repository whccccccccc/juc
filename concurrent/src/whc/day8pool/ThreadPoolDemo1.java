package whc.day8pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo1 {
    public static void main(String[] args) {

//        pool1();
//        pool2();
//        pool3();

    }

    private static void pool3() {
        //可扩容线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        try {
            for (int i = 1; i <= 20; i++) {
                threadPool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } finally {
            threadPool3.shutdown();
        }
    }

    private static void pool2() {
        //一池一线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool2.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } finally {
            threadPool2.shutdown();
        }
    }

    private static void pool1() {
        //一池 N线程
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool1.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } finally {
            threadPool1.shutdown();
        }
    }
}
