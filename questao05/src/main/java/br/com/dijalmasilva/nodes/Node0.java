package br.com.dijalmasilva.nodes;

import br.com.dijalmasilva.files.FileManager;
import br.com.dijalmasilva.files.FileManagerException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 19/03/17 - 17:22
 */
public class Node0 extends Thread {

//    private FileManager managerSum = new FileManager("/home/dijalma/Documentos/Shared/sum.txt");
//    private FileManager managerDiff = new FileManager("/home/dijalma/Documentos/Shared/diff.txt");
    private FileManager managerSum = new FileManager("/opt/shared/sum.txt");
    private FileManager managerDiff = new FileManager("/opt/shared/diff.txt");
    private List<String> messages;
    private final Node1 node1;
    private final Node2 node2;

    public Node0(Node1 node1, Node2 node2) throws FileManagerException {
        messages = new ArrayList<>();
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public void run() {

        Random random = new Random();

        while (true) {
            //tempo de espera para inserir novo valor
            long x = random.nextInt(1300) + 1;
            //valor a inserir no txt
            int n = random.nextInt(100) + 1;
            //se 1 inserir no sum.txt e 2 para inserir em diff.txt
            int m = random.nextInt(2) + 1;

            if (m == 1) {
                try {
                    writeMessages(managerSum, n);
                    System.out.println("Guardou " + n + " em sum.txt");
                    synchronized (node1) {
                        node1.notify();
                        node1.wait();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    writeMessages(managerDiff, n);
                    System.out.println("Guardou " + n + " em diff.txt");
                    synchronized (node2) {
                        node2.notify();
                        node2.wait();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                sleep(x);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeMessages(FileManager manager, int n) throws IOException {
        messages = manager.readMessages();
        messages.add("" + n);
        manager.writeMessages(messages);
    }
}
