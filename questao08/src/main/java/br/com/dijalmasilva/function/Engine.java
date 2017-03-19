package br.com.dijalmasilva.function;

import br.com.dijalmasilva.queue.Queue1;
import br.com.dijalmasilva.queue.Queue2;
import br.com.dijalmasilva.attendant.Attendant1;
import br.com.dijalmasilva.attendant.Attendant2;
import br.com.dijalmasilva.attendant.Attendant3;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 18/03/17 - 16:28
 */
public class Engine {

    private Queue1 queue1;
    private Queue2 queue2;
    Attendant1 attendant1;
    Attendant2 attendant2;
    Attendant3 attendant3;
    private IncomingManager manager;

    private double x;
    private Thread t0 = null;
    private boolean fim = false;
    private boolean printed = false;

    //
    private void printAll() {
        System.out.println("-------------------------------------------------------");
        System.out.println("Quantidade de pessoas que chegaram: " + manager.getEntered());
        System.out.println("Quantidade de pessoas que foram embora: " + manager.getNotEntered());
        System.out.println("Quantidade de pessoas na fila: " + (queue1.stayedInQueue() + queue2.stayedInQueue()));
        System.out.println("Quantidade de pessoas atendidas: " + (attendant1.getPeopleAttended() +
                attendant2.getPeopleAttended() + attendant3.getPeopleAttended()));
        System.out.println("-------------------------------------------------------");
    }

    private void randowX(){
        Runnable run = new Runnable() {
            public void run() {
                while(!fim){
                    x = Math.random();
                    System.out.println("value of x: " + x);
                    synchronized (t0){
                        try {
                            t0.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread t = new Thread(run);
        t.start();
    }

    private void manageSystem(){
        Runnable run = new Runnable() {
            public void run() {

                while(!fim){
                    synchronized (t0){
                        try {
                            t0.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    final long fx = CalcFunction.calculateFx(x);
                    final long gx = CalcFunction.calculateGx(x);
                    final long hx = CalcFunction.calculateHx(x);

                    System.out.println("f(x) = " + fx);
                    System.out.println("g(x) = " + gx);
                    System.out.println("h(x) = " + hx);

                    Thread thread = new Thread(){
                        @Override
                        public void run() {
                            manager.exec1(fx);
                            long attend = attendant1.attend(hx);
                            if (attend < hx){
                                attendant2.attend(hx-attend);
                            }
                        }
                    };
                    thread.start();
                    Thread thread2 = new Thread(){
                        @Override
                        public void run() {
                            manager.exec2(gx);
                            attendant3.attend(hx);
                        }
                    };
                    thread2.start();
                }
            }
        };

        Thread t = new Thread(run);
        t.start();
    }


    private void temporizador() {
        Runnable r0 = new Runnable() {
            public void run() {
                //
                long ti = System.currentTimeMillis();
                //
                int timef = 0;
                boolean printed = false;
                //
                long time0 = System.currentTimeMillis();
                long time1;
                while (true) {
                    time1 = System.currentTimeMillis();
                    if (time1 - time0 >= 1000) {
                        timef++;
                        time0 = time1;
                        synchronized (t0) {
                            t0.notifyAll();
                        }
                        System.out.println("seconds: " + timef + "s");
                    }
                    if (timef == 599 && !printed) {
                        fim = true;
                        printed = true;
                        System.out.println("The end!");
                    }
                    if (timef == 600) {
                        printAll();
                        break;
                    }
                }
                //
                long tf = System.currentTimeMillis();
                System.out.println("Tempo total de simulação (ms): " + (tf - ti));
            }
        };
        //
        t0 = new Thread(r0);
        t0.start();
    }


    public Engine(Queue1 queue1, Queue2 queue2, Attendant1 attendant1, Attendant2 attendant2, Attendant3 attendant3, IncomingManager manager) {
        this.queue1 = queue1;
        this.queue2 = queue2;
        this.attendant1 = attendant1;
        this.attendant2 = attendant2;
        this.attendant3 = attendant3;
        this.manager = manager;
    }

    public void execute(){
        temporizador();
        randowX();
        manageSystem();
    }
}
