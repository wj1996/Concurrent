package com.wj.concurrent.base;

import java.util.concurrent.TimeUnit;

/**
 * 锁可重入，同一个线程多次调用同步代码，锁定同一个对象，可重入
 */
public class Demo06Synchronized {

    synchronized void m1(){  //锁this
        System.out.println("m1() start");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1() end");
    }

    synchronized void m2(){
        System.out.println("m2() start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("m2() end");
    }

    public static void main(String[] args) {
        Demo06Synchronized d = new Demo06Synchronized();
        d.m1();
    }
}
