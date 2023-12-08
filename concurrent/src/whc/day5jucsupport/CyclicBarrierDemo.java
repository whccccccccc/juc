package whc.day5jucsupport;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static final int NUMBER = 7;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("召唤神龙");
        });
        for (int i = 1; i <= NUMBER; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集到第" + temp + "颗龙珠");
                try {
                    cyclicBarrier.await();//等待
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "i:" + i).start();
        }
    }
}
