package br.com.dijalmasilva.function;

import br.com.dijalmasilva.queue.Queue;
import br.com.dijalmasilva.queue.Queue1;
import br.com.dijalmasilva.queue.Queue2;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 18/03/17 - 16:37
 */
public class IncomingManager {

    private int entered = 0;
    private int notEntered = 0;
    private final Queue1 queue1;
    private final Queue2 queue2;

    public IncomingManager(Queue1 queue1, Queue2 queue2) {
        this.queue1 = queue1;
        this.queue2 = queue2;
    }

    public void exec1(long x) {
        System.out.println("Chegaram em queue1: " + x);
        action(x, queue1);
    }

    public void exec2(long x) {
        System.out.println("Chegaram em queue2: " + x);
        action(x, queue2);
    }

    private void action(long x, Queue queue) {
        for (int i = 0; i < x; i++) {
            if (queue.addPeople()) {
                entered++;
            } else {
                notEntered++;
            }
        }
    }

    public int getEntered() {
        return this.entered;
    }

    public int getNotEntered() {
        return this.notEntered;
    }
}
