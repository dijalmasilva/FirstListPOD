package br.com.dijalmasilva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 09/03/17 - 15:41
 */
public class Node1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Se comunica com o nó 2
        Socket socket = new Socket("localhost", 1098);
        Numbers numbers = new Numbers();
        //envia a mensagem
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(numbers);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Double result = null;
        while (result == null){
            result = (Double) in.readObject();
        }
        //Log
        System.out.println("Valor recebido = " + result);
    }
}
