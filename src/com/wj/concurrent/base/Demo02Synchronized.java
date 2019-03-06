package com.wj.concurrent.base;

/**
 * synchronized关键字
 * 同步方法：static
 * 静态同步方法，锁的是当前类型的类对象，在本代码中就是Demo02Synchronized.class
 */
public class Demo02Synchronized {

    private static int staticCount = 0;

    public static synchronized void testSync4(){
        System.out.println(Thread.currentThread().getName() + " staticCount = " + staticCount++);
    }

    public static void testSync5(){
        synchronized (Demo02Synchronized.class){
            System.out.println(Thread.currentThread().getName() + " staticCount = " + staticCount++);
        }
    }

}
