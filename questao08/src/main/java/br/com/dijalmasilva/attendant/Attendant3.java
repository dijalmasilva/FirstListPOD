package br.com.dijalmasilva.attendant;

import br.com.dijalmasilva.queue.Queue2;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 18/03/17 - 16:28
 */
public class Attendant3 {

    private int peopleAttended = 0;
    private final Queue2 queue2;

    public Attendant3(Queue2 queue2) {
        this.queue2 = queue2;
    }

    public long attend(long people) {
        long a = 0;
        for (int i = 0; i < people; i++) {
            if (this.queue2.removePeople()) {
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
