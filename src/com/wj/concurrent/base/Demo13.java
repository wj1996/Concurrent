package com.wj.concurrent.base;


import java.util.concurrent.TimeUnit;

public class Demo13 {

    Object o = new Object();

    void m(){
        System.out.println(Thread.currentThread().getName() + " start ");
        synchronized (o){
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end ");
            }
        }
    }

    public static void main(String[] args) {
        final Demo13 d = new Demo13();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.m();
            }
        },"thread1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                d.m();
            }
        },"thread2");

        d.o = new Object();
        thread2.start();
    }
}
