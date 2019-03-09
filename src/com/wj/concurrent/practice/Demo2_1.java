package com.wj.concurrent.practice;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者
 * 重入锁&条件
 * 条件  --condition  为lock增加条件，当条件满足时，做什么事情，比如加锁或者解锁
 */
public class Demo2_1<E> {

    private final LinkedList<E> list = new LinkedList<>();
    private final int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public int getCount(){
        return count;
    }

    public void put(E e){
        lock.lock();
        try {
            while (list.size() == MAX){
                System.out.println(Thread.currentThread().getName() + " 等待。。。。");
                producer.await();
            }
            System.out.println(Thread.currentThread().getName() + " put .......");
            list.add(e);
            count++;
            //唤醒所有的消费者
            consumer.signalAll();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } finally {
          lock.unlock();
        }

    }

    public E get(){
        E e = null;
        lock.lock();
        try {
            while(list.size() == 0){
                System.out.println(Thread.currentThread().getName() + " 等待。。。。");
                consumer.await();
            }
            System.out.println(Thread.currentThread().getName() + " get.....");
            e = list.removeFirst();
            count --;
            producer.signalAll();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } finally {
            lock.unlock();
        }

        return e;
    }


    public static void main(String[] args) {
        final Demo2_1 d = new Demo2_1();
        for(int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0; j < 5; j++){
                        System.out.println(d.get());
                    }
                }
            },"consumer-" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 2; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0; j < 25; j++){
                        d.put("container value -" + Thread.currentThread().getName() + ": " + j);
                    }
                }
            },"producer-" + i).start();
        }
    }

}
