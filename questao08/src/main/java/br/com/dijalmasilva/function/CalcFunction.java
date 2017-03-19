package br.com.dijalmasilva.function;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 18/03/17 - 16:47
 */
public class CalcFunction {

    public static long calculateFx(double x) {
        double exp = Math.exp(-x);
        double v = (0.833 * exp);
        return Math.round(v);
    }

    public static long calculateGx(double x) {
        double v = 3 * Math.pow(x, 2) + 5;
        return Math.round(v);
    }

    public static long calculateHx(double x) {
        return Math.round(15 * Math.pow(x, x));
    }
}
