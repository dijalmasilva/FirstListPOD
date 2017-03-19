package br.com.dijalmasilva;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 09/03/17 - 22:11
 */
public class Operations {

    /**
     * Calcula o parametro x exponencial de y e o de y exponencial de x, e depois retorna a soma
     * dos dois resultados.
     * @param x - int Primeiro número
     * @param y - int Segundo número
     * @return double
     */
    static double calculate(int x, int y){
        //instancia o objeto de retorno
        double result = 0.0;
        //realiza a operação matemática
        result = Math.pow(x, y) + Math.pow(y, x);
        //Apenas para Log
        System.out.println(result);
        //retorna o objeto
        return result;
    }

    /**
     * Pega os valores em uma String e as retornam em um array de números inteiros
     * @param data - String a ser quebrada
     * @return Array de inteiros
     */
    static int[] decodeData(String data){
        //Separa a String em um array de String, utilizando como caractere
        //divisor a ',' (virgula)
        String[] split = data.split(",");

        //instancia objeto a ser retornado
        int[] i = new int[split.length];
        //Percorre o array de String
        for (int j = 0; j < split.length; j++){
            //guarda o valor no array de números inteiros
            i[j] = Integer.parseInt(split[j]);
        }
        //retorna o objeto
        return i;
    }

    /**
     * Verifica se os valores dos parâmetros possuem o mesmo valor
     * @param x - Primeiro número
     * @param y - Segundo número
     * @return boolean
     */
    static boolean verifyNumbersIdentics(int x, int y){
        //retorna true se tiverem o mesmo valor, caso contrário false
        return x == y;
    }

}
