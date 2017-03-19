package br.com.dijalmasilva;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 11/03/17 - 15:43
 */
public class Node1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(10991);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Conexão aceita!");
            ThreadNode1 node1 = new ThreadNode1(socket);
            node1.start();
        }
    }
}
