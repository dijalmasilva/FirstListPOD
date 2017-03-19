package br.com.dijalmasilva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 12/03/17 - 16:52
 */
public class Node3 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(10993);

        while(true){
            Socket accept = serverSocket.accept();
            System.out.println("Aceitou conexão!");
            ObjectInputStream in = new ObjectInputStream(accept.getInputStream());
            DataOperation operation = null;
            while (operation == null){
                operation = (DataOperation) in.readObject();
            }
            if (operation.getTypeOperation() == 1){
                Socket socket = new Socket("localhost", 10992);
                new ObjectOutputStream(socket.getOutputStream()).writeObject(operation);
                ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
                Integer integer = null;
                while(integer == null){
                    integer = (Integer) inSocket.readObject();
                }
                new ObjectOutputStream(accept.getOutputStream()).writeObject(integer);
            }else{
                Socket socket = new Socket("localhost", 10994);
                new ObjectOutputStream(socket.getOutputStream()).writeObject(operation);
                ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
                Integer integer = null;
                while(integer == null){
                    integer = (Integer) inSocket.readObject();
                }
                new ObjectOutputStream(accept.getOutputStream()).writeObject(integer);
            }
        }
    }
}
