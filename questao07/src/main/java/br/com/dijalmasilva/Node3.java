package br.com.dijalmasilva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 09/03/17 - 15:41
 *
 * Classe que representa o Nó 3
 */
public class Node3 {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Cria o servidor
        ServerSocket server = new ServerSocket(1099);
        //Joga num laço infinito
        while(true) {
            //Atende uma conexão
            Socket socket = server.accept();
            //Instancia o objeto de leitura dos dados recebidos
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Numbers numbers = null;
            while (numbers == null) {
                numbers = (Numbers) in.readObject();
            }
            //Obtém o resultado da operação calculate()
            double result = Operations.calculate(numbers.getNumber1(), numbers.getNumber2());
            System.out.println("Resultado da soma: " + result);
            //retorna o resultado da operação ao cliente da conexão
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(result);

        }
    }
}
