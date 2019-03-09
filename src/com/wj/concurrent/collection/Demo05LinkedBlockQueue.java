package com.wj.concurrent.collection;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 并发容器 LinkedBlockQueue
 * 阻塞容器
 * put & take   自动阻塞
 * put自动阻塞，队列容量满后，自动阻塞
 * take自动阻塞 队列容器为0后，自动阻塞
 */
public class Demo05LinkedBlockQueue {

    final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    final Random r = new Random();

    public static void main(String[] args) {
        final Demo05LinkedBlockQueue d = new Demo05LinkedBlockQueue();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        d.queue.put("value" + d.r.nextInt(1000));
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"producer").start();

        for(int i = 0; i < 3; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " - " + d.queue.take());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            },"consumer" + i).start();
        }

    }

}
