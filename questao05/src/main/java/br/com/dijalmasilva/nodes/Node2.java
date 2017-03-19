package br.com.dijalmasilva.nodes;

import br.com.dijalmasilva.files.FileManager;
import br.com.dijalmasilva.files.FileManagerException;
import br.com.dijalmasilva.functions.Calc;

import java.io.IOException;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 19/03/17 - 17:22
 */
public class Node2 extends Thread {

    //    private FileManager managerDiff = new FileManager("/opt/shared/diff.txt");
    private FileManager managerDiff = new FileManager("/home/dijalma/Documentos/Shared/diff.txt");

    public Node2() throws FileManagerException {
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    wait();
                    List<String> strings = managerDiff.readMessages();

                    if (strings.size() % 3 != 0) {
                        int mult = strings.size() / 3;
                        if ((strings.size() - (mult * 3)) == 2) {
                            Integer diff = Calc.diff(Integer.parseInt(strings.get(strings.size() - 2)), Integer.parseInt(strings.get(strings.size() - 1)));
                            System.out.println("Subtrai: " + diff);
                            strings.add("" + diff);
                            managerDiff.writeMessages(strings);
                        } else {
                            System.out.println("Falta outro número para substrair!");
                        }
                    } else {
                        System.out.println("É multiplo de 3!");
                    }
                    notify();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this) {
                notify();
            }
        }
    }
}
