package com.hilos;

public class App {
    public static void main(String[] args) throws InterruptedException {

        // 1.1 Thread
        TareaHilo h1 = new TareaHilo();
        TareaHilo h2 = new TareaHilo();
        h1.start();
        h2.start();

        // 1.2 Runnable
        TareaRunnable tarea = new TareaRunnable();
        Thread t1 = new Thread(tarea);
        Thread t2 = new Thread(tarea);
        t1.start();
        t2.start();

        // 2.1 Condición de carrera
        Contador c = new Contador();
        Thread tc1 = new Thread(() -> {
            for(int i=0;i<10000;i++) c.incrementar();
        });
        Thread tc2 = new Thread(() -> {
            for(int i=0;i<10000;i++) c.incrementar();
        });

        tc1.start();
        tc2.start();
        tc1.join();
        tc2.join();
        System.out.println("Resultado sin sincronizar: " + c.getValor());

        // 3.1 Sincronizado
        ContadorSync cs = new ContadorSync();
        Thread ts1 = new Thread(() -> {
            for(int i=0;i<10000;i++) cs.incrementar();
        });
        Thread ts2 = new Thread(() -> {
            for(int i=0;i<10000;i++) cs.incrementar();
        });

        ts1.start();
        ts2.start();
        ts1.join();
        ts2.join();
        System.out.println("Resultado sincronizado: " + cs.getValor());

        // 4.1 Identidad y prioridad
        Thread p1 = new Thread(new TareaRunnable());
        Thread p2 = new Thread(new TareaRunnable());
        p1.setPriority(1);
        p2.setPriority(10);
        p1.start();
        p2.start();

        // 4.2 join e interrupt
        Thread largo = new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Tarea larga terminada");
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido");
            }
        });

        largo.start();
        // largo.join(); // probar esto
        largo.interrupt(); // probar interrupción
    }
}
