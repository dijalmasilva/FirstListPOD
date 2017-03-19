package br.com.dijalmasilva;

import java.io.Serializable;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 11/03/17 - 15:45
 */
public class DataOperation implements Serializable {

    private int typeOperation;
    private int x;
    private int y;

    public DataOperation(int typeOperation, int x, int y) {
        this.typeOperation = typeOperation;
        this.x = x;
        this.y = y;
    }

    public int getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(int typeOperation) {
        this.typeOperation = typeOperation;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
