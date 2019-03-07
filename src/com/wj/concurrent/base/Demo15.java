package com.wj.concurrent.base;

import sun.util.locale.provider.TimeZoneNameUtility;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 门闩 CountDownLatch
 * 可以和锁混合使用，或者替代锁的功能
 * 在门闩未开放之前等待，当门闩完全开发后执行
 * 避免锁的效率低下问题
 */
public class Demo15 {

    CountDownLatch latch = new CountDownLatch(5);

    void m1(){
        try {
            latch.await(); //等待门闩的开放
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1()");
    }

    void m2(){
        for(int i = 0; i < 10; i++){
            if(latch.getCount() != 0){
                System.out.println("latch count : " + latch.getCount());
                latch.countDown(); //减门闩上的锁
            }
            try {
                TimeUnit.MICROSECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("m2()");
    }

    public static void main(String[] args) {
        final Demo15 d = new Demo15();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.m1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.m2();
            }
        }).start();
    }


}
