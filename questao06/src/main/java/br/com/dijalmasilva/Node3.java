package br.com.dijalmasilva;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 15/03/17 - 09:10
 */
public class Node3 {

    public static void main(String[] args) throws FileManagerException, IOException {

        List<String> messages = new ArrayList<String>();
        messages.add("Outra texto qualquer!");
        messages.add("asodijas teira qualquer!");
        messages.add("Hello world...");

        FileManager fileManager = new FileManager("/home/dijalma/Documentos/Shared/disk.txt");

        fileManager.writeMessages(messages);
    }
}
