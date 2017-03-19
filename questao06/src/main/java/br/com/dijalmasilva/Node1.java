package br.com.dijalmasilva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 15/03/17 - 09:09
 */
public class Node1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(10991);

        while (true) {
            Socket accept = serverSocket.accept();
            ObjectInputStream in = new ObjectInputStream(accept.getInputStream());
            List<String> messages = null;

            while (messages == null) {
                messages = (List<String>) in.readObject();
            }

            for (String m : messages) System.out.println("Notify: " + m);
        }
    }
}
