package com.wj.concurrent.base;

import java.util.concurrent.TimeUnit;

public class Demo05Synchronized {

    private double d = 0.0;

    public synchronized void m1(double d){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.d = d;
    }

    public double m2(){
        return this.d;
    }

    public static void main(String[] args) {
        final Demo05Synchronized d = new Demo05Synchronized();
        new Thread(new Runnable() {

            @Override
            public void run() {
                d.m1(100);
            }
        }).start();
        System.out.println(d.m2());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(d.m2());
    }
}
