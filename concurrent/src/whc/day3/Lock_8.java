package whc.day3;

import java.util.concurrent.TimeUnit;

class Phone {
    public synchronized void sendEmail() throws Exception {
        System.out.println("sendEmail");
    }

    public synchronized void sendSMS() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("sendSMS");
    }

    public void hello() {
        System.out.println("hello");
    }
}



/**
 * 1. 标准访问，请问先打印邮件还是短信？  sms,email  (对象锁)
 * 2. 短信停4秒  先短信后邮件  (对象锁)
 * 3. 普通hello方法  先短信还是先hello  hello,sms   (对象锁,但hello不走锁)
 * 4. 两个资源对象，两个同步方法，先打印邮件还是短信？  email,sms   不是一把锁 然后sms还睡了四秒 (对象锁)
 * 5. 两个静态同步方法  先sms 后email (类锁)
 * 6. 两个资源对象，两个静态同步方法，先打印邮件还是短信？  sms,email  (类锁)
 * 7. 一个资源对象，一个静态同步方法，一个普通同步方法， 先普通 后静态 (两把锁, 互不影响)
 * 8. 两个 对象  一个静态同步方法，一个普通同步方法 两把锁 因为sms有睡 所以先email后sms
 */

public class Lock_8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        Thread.sleep(100);
        new Thread(() -> {
            try {
                phone.sendEmail();
//                phone.hello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
