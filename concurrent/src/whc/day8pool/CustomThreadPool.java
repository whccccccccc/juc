package whc.day8pool;

import java.util.concurrent.*;

/**
 * 自定义线程池
 */
public class CustomThreadPool {
    public static void main(String[] args) {


        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } finally {
            threadPool.shutdown();
        }

    }
}
