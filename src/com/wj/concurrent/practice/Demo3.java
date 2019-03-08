package com.wj.concurrent.practice;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo3 {

    public static void main(String[] args) {
        final MyCollection2 collection2 = new MyCollection2();
        final CountDownLatch latch = new CountDownLatch(1);

         new Thread(new Runnable() {
             @Override
             public void run() {
                 if(collection2.size() != 5){
                     try {
                         latch.await();  //等待门闩的开放
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }

                 System.out.println("size = " + collection2.size());
             }
         }).start();

         new Thread(new Runnable() {
             @Override
             public void run() {
                 for(int i = 0;i < 10; i++){
                     collection2.add(i);
                     System.out.println("add " + i);
                     if(collection2.size() == 5){
                         latch.countDown(); //门闩-1
                     }
                     try {
                         TimeUnit.SECONDS.sleep(1);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }
         }).start();
    }
}
