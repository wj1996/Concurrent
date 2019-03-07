package com.wj.concurrent.base;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile的非原子性问题
 * volatile，只是保证可见性，不能保证原子性
 * 不是加锁问题，只是内存数据可见
 */
public class Demo10Volatile {

    volatile int count = 0;

    void m(){
        for(int i = 0;i < 1000 ;i++){
            count++;
        }
    }

    public static void main(String[] args) {
        final Demo10Volatile d = new Demo10Volatile();
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
