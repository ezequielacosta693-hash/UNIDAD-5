package com.hilos;

public class TareaHilo extends Thread {
    public void run(){
        for(int i=1;i<=5;i++){
            System.out.println(Thread.currentThread().getName()+" -> "+i);
        }
    }
}
