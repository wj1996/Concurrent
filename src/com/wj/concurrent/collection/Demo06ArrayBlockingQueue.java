package com.wj.concurrent.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Demo06ArrayBlockingQueue {

    final BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

    public static void main(String[] args) {
        final Demo06ArrayBlockingQueue d = new Demo06ArrayBlockingQueue();
        for(int i = 0; i < 5; i++){
//            System.out.println("add method : " + d.queue.add("value-" + i));
           /* try {
                d.queue.put("value" + i);
                System.out.println("put method " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            try {
                System.out.println("offer method : " + d.queue.offer("value" + i,1, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(d.queue);
    }
}
