package com.wj.concurrent.base;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可打断锁
 *
 * 阻塞状态：包括普通阻塞，等待队列，锁池队列
 * 普通阻塞：sleep，可以被打断，调用Thread.interrupt方法，可以打断阻塞状态，抛出异常
 * 等待队列：wait()方法调用，也是一种阻塞状态，只能由notify唤醒
 * 锁池队列：无法获取锁标记。不是所有的锁池队列都可以被打断，
 *      使用ReentrantLock的lock方法，获取锁标记的时候，如果需要阻塞等待锁标记，无法被打断
 *      使用ReentrantLock的lockInterruptibly方法，获取锁标记的时候，如果需要阻塞等待，可以被打断
 */
public class Demo18 {

    Lock lock = new ReentrantLock();

    void m1(){
        try {
            lock.lock();
            for(int i = 0; i < 5; i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println("m1() " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2(){
        try {
            lock.lockInterruptibly();  //尝试打断，阻塞等待锁。可以被其他的线程打断阻塞状态
            System.out.println("m2()");
        } catch (InterruptedException e) {
            System.out.println("m2() interrupted");
        } finally {
            try {
                lock.unlock(); //如果没有获取锁标记，一定会抛异常
            } catch (Exception e) {

            }
        }
    }

    public static void main(String[] args) {
        final Demo18 d = new Demo18();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.m1();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                d.m2();
            }
        });
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.interrupt();  //打断线程休眠  非正常结束阻塞状态的异常，都会抛出异常

    }
}
