/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fran De La Torre
 */
public class Camara extends Carrete {

    boolean hayCarrete;
    Carrete carrete;

    public Camara() {
        this.hayCarrete = false;
    }

    public void ponerCarrete() {
        hayCarrete = true;
    }

    public boolean sePuedeHacerFoto() {
        if (hayCarrete == true && sePuedeHacer() == true) {
            return true;
        }
        return false;
    }
}
