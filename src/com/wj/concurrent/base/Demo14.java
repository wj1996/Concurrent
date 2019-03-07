package com.wj.concurrent.base;

/**
 * 常量问题
 * 在定义同步代码块时，不要使用常量对象作为锁对象
 */
public class Demo14 {

    String s1 = "hello";
    String s2 = "hello";  //同一个

    String s3 = new String("hello"); //new关键字，一定是在堆中创建一个新的对象

    void m1(){
        synchronized (s1) {
            System.out.println("m1()");
            while (true) {

            }
        }
    }

    void m2() {
        synchronized (s2) {
            System.out.println("m2()");
            while (true) {

            }
        }
    }

    public static void main(String[] args) {
        final Demo14 d = new Demo14();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.m1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.m2();
            }
        }).start();
    }
}
