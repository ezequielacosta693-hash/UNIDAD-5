package com.hilos;

public class TareaRunnable implements Runnable {
    public void run(){
        for(int i=1;i<=5;i++){
            System.out.println(Thread.currentThread().getName()+" (Runnable) -> "+i);
        }
    }
}
