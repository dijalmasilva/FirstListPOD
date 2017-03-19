package br.com.dijalmasilva;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 09/03/17 - 23:28
 */
public class Numbers implements Serializable{

    private int number1;
    private int number2;

    public Numbers() {
        this.number1 = new Random().nextInt(100)+1;
        this.number2 = new Random().nextInt(100)+1;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }
}
