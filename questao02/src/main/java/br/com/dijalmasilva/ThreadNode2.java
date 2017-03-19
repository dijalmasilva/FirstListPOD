package br.com.dijalmasilva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 19/03/17 - 10:06
 */
public class ThreadNode2 extends Thread{

    private Socket accept;

    public ThreadNode2(Socket accept) {
        this.accept = accept;
    }

    @Override
    public void run() {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(accept.getInputStream());
            DataOperation dataOperation = null;

            while (dataOperation == null){
                dataOperation = (DataOperation) in.readObject();
            }

            System.out.println("Recebido uma operação do tipo: " + dataOperation.getTypeOperation());
            if (dataOperation.getTypeOperation() == 1){
                System.out.println("Resolvendo operação...");
                ObjectOutputStream out = new ObjectOutputStream(accept.getOutputStream());
                Integer sum = Calc.sum(dataOperation.getX(), dataOperation.getY());
                System.out.println("Resolvido: " + sum);
                out.writeObject(sum);
                System.out.println("Resultado enviado.");
            }else{
                System.out.println("Não é possível responder esse tipo de operação.");
                ObjectOutputStream out = new ObjectOutputStream(accept.getOutputStream());
                out.writeObject(0);
                System.out.println("Enviado 0 (zero) como resposta.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
