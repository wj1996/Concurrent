package com.wj.concurrent.collection;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * 转移队列
 */
public class Demo08LinkedTransferQueue {

    TransferQueue<String> queue = new LinkedTransferQueue<>();

    public static void main(String[] args) {
        final Demo08LinkedTransferQueue d = new Demo08LinkedTransferQueue();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " thread begin ");
                    System.out.println(Thread.currentThread().getName() + "-" + d.queue.take());
                } catch (InterruptedException e) {
                }
            }
        },"output thread").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            d.queue.transfer("Test string");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
