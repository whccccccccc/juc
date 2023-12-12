package whc.day7queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
//        group1();//抛出异常
//        group2();//特殊值
//        group3();//一直阻塞
//        group4();//超时退出
    }

    private static void group4() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));//也会阻塞 但是超出超长时间会结束
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d", 3L, TimeUnit.SECONDS));//false 自动结束线程
    }

    private static void group3() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("d");
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        //过程中会阻塞
    }

    private static void group2() {
        //不报错版本
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));//fasle

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());//null
        System.out.println(blockingQueue.peek());//null
    }

    private static void group1() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
//
//        //第一组
//        blockingQueue.add("a");
//        blockingQueue.add("b");
//        blockingQueue.add("c");
//        System.out.println(blockingQueue.element());//只是获取第一个 a
//        blockingQueue.add("d"); //队列满了
//        blockingQueue.remove();
//        blockingQueue.remove();
//        blockingQueue.remove();
//        blockingQueue.remove();//队列空了 所以报错
    }
}
