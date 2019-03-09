package com.wj.concurrent.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class Demo03ConcurrentList {

    public static void main(String[] args) {
//        final List<String> list = new ArrayList<>(10000);
//        final List<String> list = new Vector<>();
        final List<String> list = new CopyOnWriteArrayList<>();

        final Random r = new Random();
        Thread[] array = new Thread[100];

        final CountDownLatch latch = new CountDownLatch(array.length);

        long begin = System.currentTimeMillis();
        for(int i = 0;i < array.length; i++){
            array[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0; j < 1000; j++){
                        list.add("value" + r.nextInt(10000000));
                    }
                    latch.countDown();
                }

            });
        }
        for(Thread t : array){
            t.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时为： " + (end - begin) + "毫秒！");
        System.out.println("sizi为：" + list.size());
    }
}
