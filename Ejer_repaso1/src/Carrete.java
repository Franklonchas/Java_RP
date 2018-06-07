/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fran De La Torre
 */
public class Carrete {

    int cantidad;
    int fotosRealizadas;
    int fotosReveladas;
    int fotosVeladas;
    boolean terminado;

    public Carrete(int cantidad) {
        this.cantidad = cantidad;
        this.fotosRealizadas = 0;
        this.fotosReveladas = 0;
        this.fotosVeladas = 0;
        terminado = false;
    }

    public Carrete() {
        this.cantidad = 12;
        this.fotosRealizadas = 0;
        this.fotosReveladas = 0;
        this.fotosVeladas = 0;
        terminado = false;
    }

    public void nueva() {
        if (fotosRealizadas < cantidad) {
            fotosRealizadas++;
        } else {
            System.err.println("El carrete ya esta terminado");
        }
    }

    public boolean sePuedeHacer() {
        if (fotosRealizadas < cantidad) {
            return true;
        }
        terminado = true;
        return false;
    }

    public boolean estaLLeno() {
        if (fotosRealizadas == cantidad) {
            terminado = true;
            return true;
        }
        return false;
    }

    public int numeroFotos() {
        return fotosRealizadas;
    }

    public int revelar() {
        int x;
        for (int i = 0; i < fotosRealizadas; i++) {
            x = (int) (Math.random() * 100 + 1);
            if (x < 98) {
                fotosReveladas++;
            } else {
                fotosVeladas++;
            }
        }
        terminado = true;
        return fotosReveladas;
    }

}
