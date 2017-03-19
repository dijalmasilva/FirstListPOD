package br.com.dijalmasilva;

import br.com.dijalmasilva.files.FileManagerException;
import br.com.dijalmasilva.nodes.Node0;
import br.com.dijalmasilva.nodes.Node1;
import br.com.dijalmasilva.nodes.Node2;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 19/03/17 - 16:28
 */
public class Main {

    public static void main(String[] args) throws FileManagerException {

        Node1 node1 = new Node1();
        node1.start();
        Node2 node2 = new Node2();
        node2.start();
        Node0 node0 = new Node0(node1, node2);
        node0.start();

//        //check sum.txt
//        File sum_file = new File("/opt/shared/sum.txt");
//        if (sum_file.exists()) {
//            System.out.println("Can write: " + sum_file.canWrite());
//            System.out.println("Can read: " + sum_file.canRead());
//        } else {
//            System.out.println("Sum file not found");
//        }
//
//        //check diff.txt
//        File diff_file = new File("/opt/shared/diff.txt");
//        if (diff_file.exists()) {
//            System.out.println("Can write: " + diff_file.canWrite());
//            System.out.println("Can read: " + diff_file.canRead());
//        } else {
//            System.out.println("Diff file not found");
//        }
    }
}
