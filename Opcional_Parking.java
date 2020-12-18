/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opcional_parking;

/**
 *
 * @author orlan
 */
public class Opcional_Parking {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      //OBJETO PARKING QUE RECIBE COMO PARÁMETROS Nº DE PLAZAS Y Nº DE COCHES
        Parking objP = new Parking(6, 10);

        objP.crearHilos();

    }

}