package com.wj.concurrent.practice;

import java.util.ArrayList;
import java.util.List;

public class Demo1 {

    public static void main(String[] args) {
        final MyCollection collection = new MyCollection();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 10;i++){
                    collection.add(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("增加：" + i);
                }


                System.out.println("增加完毕");
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(collection.size() == 5){
                        System.out.println(collection.size() + "结束");
                        break;
                    }
                }
            }
        }).start();
    }
}

class MyCollection{

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
