package whc.day1;

import java.util.concurrent.TimeUnit;

/**
 * 两阶段终止模式
 *
 * 停止LockSupport 线程的话 但是再次调用的话 无法停止   如果需要重复上锁的话 可以调用 interrupted
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {

        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();
        Thread.sleep(3500);
        twoPhaseTermination.stop();
    }

}

class TwoPhaseTermination {

    private Thread monitor;

    public void start() {

        monitor = new Thread(() -> {
            while (true) {
                Thread thread = Thread.currentThread();
                if (thread.isInterrupted()) {
                    System.out.println("料理后事");
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("监控操作");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    thread.interrupt();

                }
            }
        });
        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }
}