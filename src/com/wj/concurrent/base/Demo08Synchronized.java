package com.wj.concurrent.base;

import java.util.concurrent.TimeUnit;

/**
 * 锁与异常
 * 当同步方法中发生异常的时候，自动释放锁资源，不会影响其他线程的执行
 * 注意：同步业务逻辑中，如果发生异常如何处理
 */
public class Demo08Synchronized {
    int i = 0;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName() + " . start ");
        while(true){
            i++;
            System.out.println(Thread.currentThread().getName() + " - " + i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(i == 5){
                i = 1 / 0;
            }
        }
    }

    public static void main(String[] args) {
        final Demo08Synchronized d = new Demo08Synchronized();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.m();
            }
        },"t1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.m();
            }
        },"t2").start();
    }
}
