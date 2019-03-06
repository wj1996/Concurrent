package com.wj.concurrent.base;

/**
 * synchronized 同步方法和非同步方法调用
 * 同步方法只影响其他线程调用同一个同步方法时的锁问题，不影响其他线程调用非同步方法，或调用其它锁资源的方法
 */
public class Demo04Synchronized {

    Object o = new Object();

    public synchronized void m1(){   //重量级的锁，不建议这样写（除非重量级情况，要锁最好锁临界资源，锁多个线程访问的对象）
        System.out.println("public synchronized void m1() start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("public synchronized void m1() end");
    }

    public  void m2(){
        System.out.println("public synchronized void m2() start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("public synchronized void m2() end");
    }

    public void m3(){
        synchronized (o){
            System.out.println("public synchronized void m3() start");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("public synchronized void m3() end");
        }
    }

    public static class MyThread implements Runnable{

        private int i;
        private Demo04Synchronized d;

        public MyThread(int i,Demo04Synchronized d){
            this.i = i;
            this.d = d;
        }

        @Override
        public void run() {
            if(i == 0){
                d.m1();
            }else if(i > 1){
                d.m2();
            }else{
                d.m3();
            }
        }
    }

    public static void main(String[] args) {
        Demo04Synchronized d = new Demo04Synchronized();
        new Thread(new MyThread(0,d)).start();
//        new Thread(new MyThread(1,d)).start();
//        new Thread(new MyThread(2,d)).start();
        new Thread(new MyThread(0,d)).start();
    }
}
