package com.wj.concurrent.base;


import java.util.concurrent.TimeUnit;

/**
 * 锁对象变更问题
 * 同步代码一旦加锁后，那么会有一个临时的锁引用指向锁对象，和真实的引用无直接关联
 * 在锁未释放之前，修改锁对象引用，不会影响同步代码的执行
 */
public class Demo13 {

    Object o = new Object();


    int t(){
        int i = 0;
        try{
            /**(内存模型的问题)
             * return i
             * int _returnValue = i; //0;
             * return _returnValue;
             */
            return i;
        }finally{
            i = 10;
        }
    }


    void m(){
        System.out.println(Thread.currentThread().getName() + " start ");
        synchronized (o){
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end ");
            }
        }
    }

    public static void main(String[] args) {
        final Demo13 d = new Demo13();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.m();
            }
        },"thread1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                d.m();
            }
        },"thread2");
        d.o = new Object();
        thread2.start();

        System.out.println(d.t());
    }
}
