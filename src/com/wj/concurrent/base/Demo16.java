package com.wj.concurrent.base;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 * 重入锁
 */
public class Demo16 {

    Lock lock = new ReentrantLock();

    void m1(){
        try{
            lock.lock(); //加锁
            for(int i = 0; i < 10; i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println("m1() " + i);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock(); //解锁
        }

    }

    void m2(){
        lock.lock();
        System.out.println("m2()");
        lock.unlock();
    }


    public static void main(String[] args) {
        final Demo16 d = new Demo16();
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
