package com.wj.concurrent.base;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 */
public class Demo19 {

    public static void main(String[] args) {
//        TestReentrantlock t = new TestReentrantlock();
        TestSync t = new TestSync();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();
    }

}
class TestReentrantlock extends Thread{
    //定义一个公平锁,加了一个参数true
    private static ReentrantLock lock = new ReentrantLock(true);

    public void run(){
        for(int i = 0; i < 5; i++){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock " + i);
            } finally {
                lock.unlock();
            }
        }
    }
}

class TestSync extends Thread{
    public void run(){
        for(int i = 0; i < 5; i++){
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " get lock in TestSync " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
