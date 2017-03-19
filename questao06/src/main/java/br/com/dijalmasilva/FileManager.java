package br.com.dijalmasilva;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 15/03/17 - 21:07
 */
public class FileManager {

    private File file;

    public FileManager(String path) throws FileManagerException {
        this.file = new File(path);
        if (!this.file.isFile()) {
            if (this.file.isDirectory()) {
                new FileManagerException("O caminho recebido é equivalente a um diretório!");
            }
            new FileManagerException("Arquivo não existe para esse caminho!");
        }
    }


    public boolean writeMessages(List<String> messages) throws IOException {

        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file));
        for (String m : messages) {
            if (!m.equals("")) {
                buffWrite.append(m + "\n");
            }
        }
        buffWrite.close();
        return true;
    }

    public List<String> readMessages() throws IOException {
        List<String> messages = new ArrayList<>();

        BufferedReader buffRead = new BufferedReader(new FileReader(file));
        String linha = "";
        while (true) {
            linha = buffRead.readLine();
            if (linha != null) {
                if (!linha.equals("")) {
                    messages.add(linha);
                }
            } else
                break;
        }

        buffRead.close();

        List<String> clean = new ArrayList<>();
        for (int i = 0; i < messages.size(); i++) {
            clean.add("");
        }

        writeMessages(clean);

        return messages;
    }
}
