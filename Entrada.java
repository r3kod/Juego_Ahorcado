package juego_ahorcado;

import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Entrada {
    static String inicializar(){
        String input = "";
        InputStreamReader flux = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(flux);
        try{
            input = teclado.readLine();
        } catch (Exception e) {
            System.out.println("Entrada incorrecta.");
        }
        return input;
    }
    static int entero(){
        int valor = Integer.parseInt(inicializar());
        return valor;
    }
    static double real(){
        double valor = Double.parseDouble(inicializar());
        return valor;
    }
    static String cadena(){
        String valor = inicializar();
        return valor;
    }
    static char caracter(){
        String valor = inicializar();
        return valor.charAt(0);
    }
}
