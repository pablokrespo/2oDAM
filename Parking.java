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
public class Parking {
    
 //define el numero de elementos del array (variable global para que podamos acceder desde otra Clase)
    public static int numPlazas;
    //cuantos coches estarán entrando y saliendo (el número de hilos)
    private int numCoches;

    public Parking(int numPlazas, int numCoches) {
        this.numPlazas = numPlazas;
        this.numCoches = numCoches;
    }

    //MÉTODO DONDE SE CREARÁN LOS HILOS
    public void crearHilos() {
        //Objeto MonitorParking para que los hilos accedan a la misma clase 
        MonitorParking objM = new MonitorParking();
        //Inicializamos el array de las plazas poniendo 0 en todas sus celdas !!!
        objM.inicializarArray();
        //Array para ir creando y almacenando los hilos
        CochesHilos[] arrayCoches = new CochesHilos[numCoches];

        for (int i = 0; i < numCoches; i++) {
            //ALMACENAMOS EN EL ARRAY LOS HILOS DE LA CLASE CochesHilos
            arrayCoches[i] = new CochesHilos("C" + i, objM);

        }
        //INICIALIZAMOS LOS HILOS
        //(Java parece que deja inicializar los hilos en un for sin que haya
        //fallos)
        for (int i = 0; i < arrayCoches.length; i++) {

            arrayCoches[i].start();

        }

    }

}
