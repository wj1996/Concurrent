package com.wj.concurrent.base;

public class Demo12 {

    synchronized void m1(){
        //前置逻辑
        System.out.println("同步逻辑");
        //后置逻辑
    }

    //不建议上述写法，上述执行的逻辑不管是不是同步逻辑都会加锁
    void m2(){
        //前置逻辑
        synchronized (this){
            System.out.println("同步逻辑");
        }
        //后置逻辑
    }
}
