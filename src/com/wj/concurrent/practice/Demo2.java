package com.wj.concurrent.practice;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 生产者消费者模式
 * wait/notify
 */
public class Demo2<E> {

    private final LinkedList<E> list = new LinkedList<>();
    private final int MAX = 10;
    private int count = 0;

    public synchronized int getCount(){
        return count;
    }

    public synchronized void put(E e){
        while(list.size() == MAX){
            try {
                this.wait();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

        list.add(e);
        count++;
        this.notifyAll();
    }

    public synchronized E get(){
        E e = null;
        while (list.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        e = list.removeFirst();
        count--;
        this.notifyAll();
        return e;
    }

    public static void main(String[] args) {
        final Demo2<String> d = new Demo2<>();
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

