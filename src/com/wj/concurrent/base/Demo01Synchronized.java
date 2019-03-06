package com.wj.concurrent.base;

/**
 * synchronized关键字
 * 锁对象。synchronized(this)和synchronized方法都是锁当前对象
 */
public class Demo01Synchronized {

    private int count = 0;
    private Object o = new Object();

    public void testSync1(){
        synchronized (o){
            System.out.println(Thread.currentThread().getName() + " count = " + count++);
        }
    }

    public void testSync2(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " count = " + count++);
        }
    }

    public synchronized void testSync3(){
        System.out.println(Thread.currentThread().getName() + " count = " + count++);
    }

    public static void main(String[] args) {

    }

}
