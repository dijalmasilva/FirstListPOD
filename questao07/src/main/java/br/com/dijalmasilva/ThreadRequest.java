package br.com.dijalmasilva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 19/03/17 - 10:36
 */
public class ThreadRequest extends Thread {

    private Numbers numbers;

    public ThreadRequest(Numbers numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        synchronized (this) {
            //Se comunica com o nó 2
            Socket socket = null;
            try {
                socket = new Socket("localhost", 1098);
                //envia a mensagem
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(numbers);
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Double result = null;
                while (result == null) {
                    result = (Double) in.readObject();
                }
                //Log
                System.out.println("Valor recebido = " + result);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            this.notify();
        }
    }
}
