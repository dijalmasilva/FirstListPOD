package br.com.dijalmasilva;

import java.io.*;
import java.net.Socket;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 13/03/17 - 20:36
 */
public class Node1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Socket socket = new Socket("localhost", 10992);

        OutputStream out = socket.getOutputStream();
        out.write("Testando mensagem...".getBytes());
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Boolean result = null;
        while (result == null){
            result = (Boolean) in.readObject();
        }
        System.out.println("Result: " + result.toString());
    }
}
