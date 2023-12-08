package whc.day1;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author admin
 */
public class Test1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 1. Thread
         * 2. Runnable+Thread
         * 3. FutureTask+Thread  有返回值
         */

        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("running");
                Thread.sleep(1000);
                return 6;
            }
        });
        new Thread(task).start();
        System.out.println(task.get());
        //用TimeUnit 而不是Thread.sleep
        //while true 内 写sleep 能降低CPU占用
        //join 等待返回
    }
}
