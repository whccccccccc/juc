package whc.day4callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread1 implements Runnable {

    @Override
    public void run() {
        System.out.println("MyThread1");
    }
}

class MyThread2 implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        return 6;
    }
}

public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread1 myThread1 = new MyThread1();
        Thread thread = new Thread(myThread1, "A");
        thread.start();

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
//        FutureTask<Integer> futureTaskLamada = new FutureTask<>(() -> {
//            return 6;
//        });
        new Thread(futureTask,"B").start();
        System.out.println(futureTask.get());
    }

}
