package com.wj.concurrent.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicXXX
 * 同步类型
 * 原子操作类型，其中的每个方法都是原子操作，可以保证线程安全
 */
public class Demo11Atomic {

    AtomicInteger count = new AtomicInteger(0);

    void m(){
        for(int i = 0;i < 1000 ;i++){
            count.incrementAndGet();
        }
    }
    public static void main(String[] args) {
        final Demo11Atomic d = new Demo11Atomic();
        List<Thread> threads = new ArrayList<>();
        for(int i = 0;i < 10;i++){
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    d.m();
                }
            }));
        }
        for(Thread thread : threads){
            thread.start();
        }

        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(d.count);
    }
}
