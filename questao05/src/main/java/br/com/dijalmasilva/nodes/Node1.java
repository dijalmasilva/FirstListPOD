package br.com.dijalmasilva.nodes;

import br.com.dijalmasilva.files.FileManager;
import br.com.dijalmasilva.files.FileManagerException;
import br.com.dijalmasilva.functions.Calc;

import java.io.IOException;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 19/03/17 - 17:22
 */
public class Node1 extends Thread {

    //    private FileManager managerSum = new FileManager("/home/dijalma/Documentos/Shared/sum.txt");
    private FileManager managerSum = new FileManager("/opt/shared/sum.txt");

    public Node1() throws FileManagerException {
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    wait();
                    List<String> strings = managerSum.readMessages();

                    if (strings.size() % 3 != 0) {
                        int mult = strings.size() / 3;
                        if ((strings.size() - (mult * 3)) == 2) {
                            Integer sum = Calc.sum(Integer.parseInt(strings.get(strings.size() - 2)), Integer.parseInt(strings.get(strings.size() - 1)));
                            System.out.println("Soma: " + sum);
                            strings.add("" + sum);
                            managerSum.writeMessages(strings);
                        } else {
                            System.out.println("Falta outro número para somar!");
                        }
                    } else {
                        System.out.println("É multiplo de 3!");
                    }
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
