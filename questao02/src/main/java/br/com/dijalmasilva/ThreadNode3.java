package br.com.dijalmasilva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 19/03/17 - 10:08
 */
public class ThreadNode3 extends Thread {

    private Socket accept;

    public ThreadNode3(Socket accept) {
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
            if (dataOperation.getTypeOperation() == 2) {
                System.out.println("Resolvendo operação...");
                ObjectOutputStream out = new ObjectOutputStream(accept.getOutputStream());
                Integer diff = Calc.diff(dataOperation.getX(), dataOperation.getY());
                System.out.println("Resolvido: " + diff);
                out.writeObject(diff);
                System.out.println("Resultado enviado.");
            } else {
                System.out.println("Encaminhando operação...");
                int n = 10990 + (new Random().nextInt(2) + 1);
                System.out.println("Conectando-se ao node: " + (n - 10990));
                Socket s = new Socket("localhost", n);
                ObjectOutputStream out2 = new ObjectOutputStream(s.getOutputStream());
                out2.writeObject(dataOperation);
                System.out.println("Operação encaminhada!");
                ObjectInputStream in2 = new ObjectInputStream(s.getInputStream());
                Integer result = null;
                System.out.println("Aguardando resposta...");
                while (result == null) {
                    result = (Integer) in2.readObject();
                }
                System.out.println("Recebido: " + result);
                ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                System.out.println("Respondendo resultado ao cliente...");
                out.flush();
                out.writeObject(result);
                System.out.println("Respondindo!");
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
