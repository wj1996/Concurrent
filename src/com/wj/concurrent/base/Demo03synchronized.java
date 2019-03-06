package com.wj.concurrent.base;

/**
 * 同步方法：原子性
 * 加锁的目的：保证操作的原子性
 */
public class Demo03synchronized implements Runnable{

    private int count = 0;


    @Override
    public synchronized void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getName() + " count = " + count++);
    }

    public static void main(String[] args) {
        Demo03synchronized d = new Demo03synchronized();
        for(int i = 0;i < 5;i++){
            new Thread(d,"thread-" + i).start();
        }
    }
}
