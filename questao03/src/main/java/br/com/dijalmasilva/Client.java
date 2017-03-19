package br.com.dijalmasilva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 12/03/17 - 16:41
 *
 * Representa Node1
 */
public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        DataOperation dataOperation = new DataOperation(1, 10, 5);
        Socket socket = new Socket("localhost", 10992);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(dataOperation);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Integer integer = null;
        while(integer == null){
            integer = (Integer) in.readObject();
        }
        System.out.println("Teste para o nó 2 com uma op1: " + integer);

        DataOperation dataOperation1 = new DataOperation(2, 10, 5);
        Socket socket1 = new Socket("localhost", 10992);
        ObjectOutputStream out1 = new ObjectOutputStream(socket1.getOutputStream());
        out1.writeObject(dataOperation1);
        ObjectInputStream in1 = new ObjectInputStream(socket1.getInputStream());
        Integer integer1 = null;
        while(integer1 == null){
            integer1 = (Integer) in1.readObject();
        }
        System.out.println("Teste para o nó 2 com uma op2: " + integer1);

        DataOperation dataOperation2 = new DataOperation(1, 10, 5);
        Socket socket2 = new Socket("localhost", 10993);
        ObjectOutputStream out3 = new ObjectOutputStream(socket2.getOutputStream());
        out3.writeObject(dataOperation2);
        ObjectInputStream in3 = new ObjectInputStream(socket2.getInputStream());
        Integer integer3 = null;
        while(integer3 == null){
            integer3 = (Integer) in3.readObject();
        }
        System.out.println("Teste para o nó 3 com uma op1: " + integer3);

        DataOperation dataOperation3 = new DataOperation(2, 10, 5);
        Socket socket3 = new Socket("localhost", 10993);
        ObjectOutputStream out4 = new ObjectOutputStream(socket3.getOutputStream());
        out4.writeObject(dataOperation3);
        ObjectInputStream in4 = new ObjectInputStream(socket3.getInputStream());
        Integer integer4 = null;
        while(integer4 == null){
            integer4 = (Integer) in4.readObject();
        }
        System.out.println("Teste para o nó 3 com uma op2: " + integer4);
    }
}
