package whc.day5jucsupport;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建CountDownLatch 对象

        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()  + "号同学离开");
                countDownLatch.countDown();
            }, "i:" + i).start();
        }
        countDownLatch.await();//countDown为0之后才会执行
        System.out.println(Thread.currentThread().getName() + "班长离开");
    }
}
