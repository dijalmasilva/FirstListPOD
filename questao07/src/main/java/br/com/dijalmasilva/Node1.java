package br.com.dijalmasilva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 09/03/17 - 15:41
 */
public class Node1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        int requests = 0;
        long totalTime = 0;
        long maxTime = 1000;
        long a = System.currentTimeMillis();
        while (requests < 20) {

            Numbers numbers = new Numbers();
            ThreadRequest request = new ThreadRequest(numbers);
            request.start();
            long c = System.currentTimeMillis();
            synchronized (request) {
                request.wait();
            }
            long d = System.currentTimeMillis();
            long time = d - c;
            long timeToThread = maxTime / (20 - requests++);
            while (maxTime > 0 && time < timeToThread) {
                d = System.currentTimeMillis();
                time = d - c;
                maxTime = 1000 - (System.currentTimeMillis() - a);
            }
        }
        long b = System.currentTimeMillis();
        totalTime = b - a;
        System.out.println("Realizou operação em " + totalTime + "ms");
    }
}
