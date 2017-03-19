package br.com.dijalmasilva;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 15/03/17 - 09:09
 */
public class Node2 {

    public static void main(String[] args) throws FileManagerException {

        final FileManager fileManager = new FileManager("/home/dijalma/Documentos/Shared/disk.txt");

        Thread t = new Thread(){
            Socket conectionNode1;

            @Override
            public void run() {
                while(true){

                    List<String> messages = null;
                    try {
                        messages = fileManager.readMessages();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (messages != null){
                        try {
                            conectionNode1 = new Socket("localhost", 10991);
                            ObjectOutputStream out = new ObjectOutputStream(conectionNode1.getOutputStream());
                            out.writeObject(messages);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        sleep(1300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t.start();
    }
}
