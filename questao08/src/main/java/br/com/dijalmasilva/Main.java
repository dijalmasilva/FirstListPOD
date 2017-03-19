package br.com.dijalmasilva;

import br.com.dijalmasilva.attendant.Attendant1;
import br.com.dijalmasilva.attendant.Attendant2;
import br.com.dijalmasilva.attendant.Attendant3;
import br.com.dijalmasilva.function.Engine;
import br.com.dijalmasilva.function.IncomingManager;
import br.com.dijalmasilva.queue.Queue1;
import br.com.dijalmasilva.queue.Queue2;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 18/03/17 - 16:51
 */
public class Main {

    public static void main(String[] args) {

        Queue1 queue1 = new Queue1();
        Queue2 queue2 = new Queue2();
        Attendant1 attendant1 = new Attendant1(queue1);
        Attendant2 attendant2 = new Attendant2(queue1);
        Attendant3 attendant3 = new Attendant3(queue2);
        IncomingManager manager = new IncomingManager(queue1, queue2);
        Engine engine = new Engine(queue1, queue2, attendant1, attendant2, attendant3, manager);
        engine.execute();
    }
}
