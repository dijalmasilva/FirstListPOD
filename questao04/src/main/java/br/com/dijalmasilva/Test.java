package br.com.dijalmasilva;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 12/03/17 - 18:13
 */
public class Test {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("jdbc:postgresql://localhost/question4pod", 5432);
        socket.getOutputStream().write(new String("insert into message ('Testando conexão')").getBytes());
        byte[] b = new byte[2048];
        socket.getInputStream().read(b);
        String result = new String(b).trim();
    }
}
