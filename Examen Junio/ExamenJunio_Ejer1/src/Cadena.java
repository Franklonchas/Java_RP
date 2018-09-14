/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fran De La Torre
 */
public class Cadena {

    String cadena;
    int constanteAscii;
    String cadenaFinal = "";

    public Cadena(String cadena) {
        this.cadena = cadena;
    }

    public void rotaCadena(int posicion, int orientacion) {
        if (orientacion == 1) {
            System.out.println("Orientacion a la derecha");
            for (int i = posicion; i >= 0; i--) {
                this.cadena = this.cadena + this.cadena.charAt(i);
            }
        } else if (orientacion == 2) {
            System.out.println("Orientacion a la izquierda");
            for (int i = 0; i < posicion; i++) {
                this.cadena = this.cadena + this.cadena.charAt(i);
            }
        }
    }

    public char cambiaCaracter(char n, int pos) {
        int charToascii = (int) n;
        constanteAscii = charToascii + pos;
        char asciiToChar = (char) constanteAscii;
        return asciiToChar;
    }

    public void encriptaCadena(int valorRotacion, String cadena) {
        for (int i = 0; i < cadena.length(); i++) {
            int charToascii = (int) cadena.charAt(i);
            constanteAscii = charToascii + valorRotacion;
            char asciiToChar = (char) constanteAscii;
            cadena = cadena + asciiToChar;
        }
        this.cadena = cadena;
    }

    public void desencriptaCadena(int valorRotacion, String cadena) {
        for (int i = 0; i < cadena.length(); i++) {
            int charToAscii = (int) cadena.charAt(i);
            constanteAscii = charToAscii - valorRotacion;
            char asciiToChar = (char) constanteAscii;
            cadena = cadena + asciiToChar;
        }
        this.cadena = cadena;
    }

    public String getCadena() {
        return cadena;
    }

}
