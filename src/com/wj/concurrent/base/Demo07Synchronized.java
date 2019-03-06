package com.wj.concurrent.base;

import java.util.concurrent.TimeUnit;

public class Demo07Synchronized {

    synchronized void m(){
        System.out.println("m1() start");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1() end");
    }

    public static void main(String[] args) {
        Sub_Demo07Synchronized d = new Sub_Demo07Synchronized();
        d.m();
    }
}

class Sub_Demo07Synchronized extends Demo07Synchronized{

    synchronized void m(){
        System.out.println("m2() start");
        super.m();
        System.out.println("m2() end");
    }
}
