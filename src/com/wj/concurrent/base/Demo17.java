package com.wj.concurrent.base;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 尝试锁
 */
public class Demo17 {

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
        boolean isLocked = false;
        try {
            isLocked = lock.tryLock(); //尝试锁，如果有锁，无法获取锁标记，返回false，如果获取锁标记，返回true
//            isLocked = lock.tryLock(5,TimeUnit.SECONDS);  //阻塞尝试锁，阻塞参数代表的时长，尝试获取锁标记，如果超市，不等待，直接返回
            if(isLocked){
                System.out.println("m2() method synchronized");
            }else{
                System.out.println("m2() method unsynchronized");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(isLocked){
                //尝试锁在解除锁标记的时候，一定要判断是否获取到锁标记
                //如果当前线程没有获取到锁标记，会抛出异常
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        final Demo17 d = new Demo17();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.m1();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.m2();
            }
        }).start();
    }
}
