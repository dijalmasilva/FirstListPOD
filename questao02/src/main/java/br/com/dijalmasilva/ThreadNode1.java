package br.com.dijalmasilva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 19/03/17 - 10:04
 */
public class ThreadNode1 extends Thread {

    private Socket accept;

    public ThreadNode1(Socket accept) {
        this.accept = accept;
    }

    @Override
    public void run() {

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(accept.getInputStream());
            DataOperation dataOperation = null;

            while (dataOperation == null) {
                dataOperation = (DataOperation) in.readObject();
            }

            System.out.println("Recebido uma operação do tipo: " + dataOperation.getTypeOperation());
            if (dataOperation.getTypeOperation() == 1) {
                System.out.println("Resolvendo operação...");
                ObjectOutputStream out = new ObjectOutputStream(accept.getOutputStream());
                Integer sum = Calc.sum(dataOperation.getX(), dataOperation.getY());
                System.out.println("Resolvido: " + sum);
                out.writeObject(sum);
                System.out.println("Resultado enviado.");
            } else {
                System.out.println("Encaminhando operação para o nó 3");
                Socket s = new Socket("localhost", 10993);
                ObjectOutputStream out2 = new ObjectOutputStream(s.getOutputStream());
                out2.writeObject(dataOperation);
                System.out.println("Enviado objeto ao nó 3");
                ObjectInputStream in2 = new ObjectInputStream(s.getInputStream());
                Integer result = null;
                System.out.println("Aguardando resposta do nó 3...");
                while (result == null) {
                    result = (Integer) in2.readObject();
                }
                System.out.println("Recebido: " + result);
                ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                System.out.println("Respondendo resultado ao cliente...");
                out.writeObject(result);
                System.out.println("Respondindo!");
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
