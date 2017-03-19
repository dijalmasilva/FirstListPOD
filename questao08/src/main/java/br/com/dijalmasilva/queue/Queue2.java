package br.com.dijalmasilva.queue;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 16/03/17 - 20:16
 */
public class Queue2 implements Queue {

    private final int size = 50;
    private int inQueue = 0;

    @Override
    public synchronized boolean addPeople() {
        if (inQueue == size) {
            return false;
        } else {
            inQueue++;
            return true;
        }
    }

    @Override
    public synchronized boolean removePeople() {
        if (inQueue == 0) {
            return false;
        } else {
            inQueue--;
            return true;
        }
    }

    public int stayedInQueue() {
        return inQueue;
    }
}
