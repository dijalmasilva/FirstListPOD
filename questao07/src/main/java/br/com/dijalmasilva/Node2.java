package br.com.dijalmasilva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 09/03/17 - 15:41
 *
 * Classe que representa o Nó 2
 */
public class Node2 {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Cria servidor
        ServerSocket serverSocket = new ServerSocket(1098);
        //Inicia Laço de repetição
        while (true){
            //Conexão recebida
            Socket socket = serverSocket.accept();
            //Obtem mensagem recebida do cliente
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Numbers numbers = null;
            while(numbers == null) {
                numbers = (Numbers) in.readObject();
            }
            //Verifica se os números possuem o mesmo valor
            //Se não tiverem, faz a comunicação com o Nó 3 e repassa os dados
            //Caso contrário, apenas retorna 0 (zero)
            if (!Operations.verifyNumbersIdentics(numbers.getNumber1(), numbers.getNumber2())){
                //Abriu comunicação com o nó 3
                Socket comunication = new Socket("localhost", 1099);
                //envia os dados
                ObjectOutputStream out = new ObjectOutputStream(comunication.getOutputStream());
                out.writeObject(numbers);
                ObjectInputStream in2 = new ObjectInputStream(comunication.getInputStream());
                Double result = null;
                while(result == null){
                    result = (Double) in2.readObject();
                }
                System.out.println("Recebido de node3 = " + result);
                ObjectOutputStream out2 = new ObjectOutputStream(socket.getOutputStream());
                out2.writeObject(result);
            }else{
                //envia a resposta para o cliente com o resultado 0 (zero)
                new ObjectOutputStream(socket.getOutputStream()).writeObject(0d);
            }

        }
    }
}
