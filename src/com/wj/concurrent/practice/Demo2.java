package com.wj.concurrent.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Demo2 {

    public static void main(String[] args) {
        final MyCollection2 collection2 = new MyCollection2();
        final Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    if(collection2.size() != 5){
                        try {
                            lock.wait();  //线程进入等待队列
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("size == " + collection2.size());
                    lock.notifyAll();  //唤醒其它等待线程
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for(int i = 0; i < 10; i++){
                        collection2.add(i);
                        System.out.println("add " + i);
                        if(collection2.size() == 5){
                            lock.notifyAll();
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

}

class MyCollection2{

    private /*volatile*/ int size;

    private List list = new ArrayList(); //容器


    public void add(Object o){
        list.add(o);
    }

    public int size(){
        this.size = list.size();
        return this.size;
    }

}
