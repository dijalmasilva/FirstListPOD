package br.com.dijalmasilva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 11/03/17 - 15:43
 */
public class Client {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                Socket socket = null;
                DataOperation dataOperation = new DataOperation(2, 10, 5);
                try {
                    socket = new Socket("localhost", 10991);
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject(dataOperation);
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    Integer integer = null;
                    while (integer == null) {
                        integer = (Integer) in.readObject();
                    }

                    System.out.println("Conectando ao node1 com a op1()- R: " + integer);
                    out.close();
                    in.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                Socket socket2 = null;
                DataOperation dataOperation = new DataOperation(1, 10, 5);
                try {
                    socket2 = new Socket("localhost", 10993);
                    ObjectOutputStream out2 = new ObjectOutputStream(socket2.getOutputStream());
                    out2.writeObject(dataOperation);
                    ObjectInputStream in2 = new ObjectInputStream(socket2.getInputStream());
                    Integer integer = (Integer) in2.readObject();
                    System.out.println("Conectando ao node3 com a op2()- R: " + integer);
                    out2.close();
                    in2.close();
                    socket2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                Socket socket3 = null;
                DataOperation dataOperation = new DataOperation(1, 10, 5);
                try {
                    socket3 = new Socket("localhost", 10992);
                    ObjectOutputStream out3 = new ObjectOutputStream(socket3.getOutputStream());
                    out3.writeObject(dataOperation);
                    ObjectInputStream in3 = new ObjectInputStream(socket3.getInputStream());
                    Integer integer = null;
                    while (integer == null) {
                        integer = (Integer) in3.readObject();
                    }
                    System.out.println("Conectando ao node2 com a op1()- R: " + integer);
                    out3.close();
                    in3.close();
                    socket3.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        };


        t1.start();
//        t2.start();
//        t3.start();


    }

}
