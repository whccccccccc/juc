package whc.day2;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * list 线程不安全
 * 解决方案
 * Vector
 * Collections.synchronizedList(new ArrayList<>());
 * CopyOnWriteArrayList
 * hashSet不安全 用CopyOnWriteArraySet
 * hashMap不安全 用ConcurrentHashMap
 */
public class ThreadDemo4 {
    public static void main(String[] args) {

//        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();//方式1
//        List<String> list = Collections.synchronizedList(new ArrayList<>());//方式2
        List<String> list = new CopyOnWriteArrayList<>();//方式3  写时复制
        for (int i = 0; i < 30 ; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

    }
}
