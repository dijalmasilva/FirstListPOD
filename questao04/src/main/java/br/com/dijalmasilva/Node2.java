package br.com.dijalmasilva;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 13/03/17 - 20:51
 */
public class Node2 {

    public static void main(String[] args) throws IOException, SQLException {

        ServerSocket serverSocket = new ServerSocket(10992);

        while (true) {
            Socket accept = serverSocket.accept();
            InputStream in = accept.getInputStream();
            byte[] b = new byte[2048];
            in.read(b);
            ConnectionDataBase connectionDataBase = new ConnectionDataBase();
            String msg = new String(b).trim();
            boolean b1 = connectionDataBase.insertMySql(msg);
            boolean b2 = connectionDataBase.insertPostgres(msg);
            System.out.println(""+ b1 + b2);
//            boolean b1 = true;
            if (!b1 && !b2) {
                new ObjectOutputStream(accept.getOutputStream()).writeObject(true);
            } else {
                new ObjectOutputStream(accept.getOutputStream()).writeObject(false);
            }
        }
    }
}
