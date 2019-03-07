package com.wj.concurrent.base;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字
 * 通知OS操作系统底层，在CPU计算过程中，都要检查内存中数据的有效性，保证最新的内存数据被使用
 */
public class Demo09Volatile {

    volatile boolean b = true;

    void m(){
        System.out.println("start");
        while(b){

        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        final Demo09Volatile d = new Demo09Volatile();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.m();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        d.b = false;
    }
}
