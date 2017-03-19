package br.com.dijalmasilva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 12/03/17 - 16:59
 * <p>
 * Representa o Node4
 */
public class Device {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(10994);

        while (true) {
            Socket accept = serverSocket.accept();

            ObjectInputStream in = new ObjectInputStream(accept.getInputStream());
            DataOperation operation = null;
            while (operation == null) {
                operation = (DataOperation) in.readObject();
            }

            if (operation.getTypeOperation() == 1) {
                new ObjectOutputStream(accept.getOutputStream()).writeObject(Calc.sum(operation.getX(), operation.getY()));
            } else {
                new ObjectOutputStream(accept.getOutputStream()).writeObject(Calc.diff(operation.getX(), operation.getY()));
            }
        }
    }
}
