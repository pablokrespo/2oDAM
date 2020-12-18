/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opcional_parking;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orlan
 */
public class CochesHilos extends Thread{
    
 private MonitorParking objM;

    private int pos;

    //Constructor sin parámetros
    public CochesHilos() {

    }

    //Constructor con parámetros y getName()
    public CochesHilos(String nombre, MonitorParking objM) {

        super(nombre);
        this.objM = objM;

    }

    //GETTERS Y SETTERS DE LA POS 
    //(no deja poner/obtener un valor desde un setter/getter cuando trabajamos con hilos)
    //en cambio, si usamos clases que retornen valores parece que sí que funciona
    public synchronized void setPos(int pos) {
        this.pos = pos;
    }

    public synchronized int getPos() {
        return pos;
    }

    @Override
    public void run() {

        System.out.println("HILO CREADO: " + getName());
        try {

            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CochesHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //RECOGEMOS LA POSICIÓN EN EL ARRAY DEL PARKING A TRAVÉS DE UN RETURN EN EL MÉTODO
        //DE "ENTRADA"
        pos = objM.entrada(getName());
        try {
            //tiempo de espera para salir aleatorio
            sleep((int) Math.floor(Math.random() * (5000 - 1000 + 1) + (1000)));
        } catch (InterruptedException ex) {
            Logger.getLogger(CochesHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //PONEMOS LA POSICIÓN EN EL PARKING EN ESE HILO DE VUELTA COMO PARÁMETRO DEL MÉTODO "SALIDA"
        objM.salida(getName(), pos);

    }

}