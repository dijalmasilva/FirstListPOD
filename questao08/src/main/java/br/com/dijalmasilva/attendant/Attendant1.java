package br.com.dijalmasilva.attendant;

import br.com.dijalmasilva.queue.Queue1;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 18/03/17 - 16:23
 */
public class Attendant1 {

    private int peopleAttended = 0;
    private final Queue1 queue1;

    public Attendant1(Queue1 queue1) {
        this.queue1 = queue1;
    }

    public long attend(long people) {
        long a = 0;
        for (int i = 0; i < people; i++) {
            if (this.queue1.removePeople()) {
                peopleAttended++;
                a++;
            } else {
                break;
            }
        }
        return a;
    }

    public int getPeopleAttended() {
        return peopleAttended;
    }
}
