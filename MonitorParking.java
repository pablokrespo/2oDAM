/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//CLASE MONITOR 
//EL ARRAY CON LAS PLAZAS SERÁ EL RECURSO COMPARTIDO
//SOLO SE ADMITE UNA ENTRADA Y UNA SALIDA A LA VEZ
//se supone que hay un método para entrar y otro para salir, de manera que 
//ambos métodos se llaman en la clase de los hilos separados por un sleep()

package opcional_parking;
import java.lang.reflect.Array;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author orlan
 */
public class MonitorParking {
    
 private int numPlazas = Parking.numPlazas;
    private int plazasArray = numPlazas;
    //Hacemos el Array Parking "String" para poder acceder a los nombres de los "hilos coche"
    private String[] arrayParking = new String[plazasArray];
    //(lo usaremos como contador y como "semáforo" para el acceso de los hilos al recurso compartido)
    private int contP = numPlazas;
    //para guardar la posición donde se aparcó el coche
    private int pos = 0;

    public MonitorParking() {

    }

    //A este método accederán varios hilos a la vez
    //también le hace falta ser synchronized
    public synchronized void mostrarArray() {

        for (int i = 0; i < arrayParking.length; i++) {

            System.out.print("[" + arrayParking[i] + "]");

        }
        System.out.println("\n");

    }

    //Para inicializar el array con valores "0"
    public void inicializarArray() {

        for (int i = 0; i < arrayParking.length; i++) {

            arrayParking[i] = "0";

        }

    }

    public synchronized Integer entrada(String nombreC) {

        //COMPROBAMOS QUE HAYA PLAZAS LIBRES
        while (contP == 0) {

            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitorParking.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        //recorremos el array en busca de sitios libres (0)
        for (int i = 0; i < arrayParking.length; i++) {

            if (arrayParking[i] == "0") {

                arrayParking[i] = nombreC;

                System.out.println("ENTRADA: " + nombreC + " aparca en plaza " + i);
                contP--;
                System.out.println("PLAZAS LIBRES: " + contP);
                //mostramos el parking
                System.out.println("PARKING: ");
                mostrarArray();
                //salimos del for y guardamos la posición del coche en el array
                pos = i;
                break;

            }

        }

        return pos;

    }

    //Recogemos la posición del coche que va a salir del el array como parámetro
    public synchronized void salida(String nombreC, int posicion) {

        System.out.println("SALIDA: " + nombreC + " saliendo");

        arrayParking[posicion] = "0";
        contP++;
        System.out.println("PLAZAS LIBRES: " + contP);
        System.out.println("PARKING:");
        mostrarArray();

        //NOTIFICAMOS DE QUE HAY UNA NUEVA PLAZA DISPONIBLE
        notifyAll();

    }

}
