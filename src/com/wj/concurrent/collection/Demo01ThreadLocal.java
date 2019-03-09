package com.wj.concurrent.collection;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal
 * 就是一个Map key ---- 》 Thread.getCurrentThread()  value ---> 线程需要的变量
 * ThreadLocal.set(value)  ---> map.put(Thread.currentThread(),value);
 * ThreadLocal.get()  ---> map.get(Thread.currentThread());
 * 内存问题：在并发量高的时候，可能有内存溢出
 * 使用ThreaLocal的时候，一定要注意回收资源问题，在每个线程结束之前，将当前线程保存的线程变量一定要删除
 * ThreadLocal.remove();
 *
 */
public class Demo01ThreadLocal {

    volatile static String name = "zhangshan";
    static ThreadLocal<String> tl = new ThreadLocal<>();


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name);
                System.out.println(tl.get());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                name = "lisi";
                tl.set("wangwu");
            }
        }).start();

    }
}
