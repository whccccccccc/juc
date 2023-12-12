package whc.day10completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回值，completableFuture");
        });
        completableFuture.get();
        //有返回值
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回值，completableFuture1");
            int i = 10 / 0;
            return 1024;
        });
        completableFuture1.whenComplete((t, u) -> {
            System.out.println("t=>" + t);//正常的返回结果
            System.out.println("u=>" + u);//错误信息
        }).exceptionally(f -> {
            System.out.println("exception:" + f.getMessage());
            return 444;
        }).get();

    }
}
