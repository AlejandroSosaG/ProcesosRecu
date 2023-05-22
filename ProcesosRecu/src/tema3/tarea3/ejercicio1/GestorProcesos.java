package tema3.tarea3.ejercicio1;

import java.io.IOException;
import java.net.*;

public class GestorProcesos extends Thread{
    DatagramSocket socket;
    DatagramPacket datagramaEntrada;
    int numSecreto;

    /**
     * Contructor con todos los parámetros.
     * @param socket
     * @param datagramaEntrada
     * @param numSecreto
     */
    public GestorProcesos(DatagramSocket socket, DatagramPacket datagramaEntrada, int numSecreto) {
        super();
        this.socket = socket;
        this.datagramaEntrada = datagramaEntrada;
        this.numSecreto = numSecreto;
    }

    /**
     * Contructor por defecto.
     */
    public GestorProcesos() {
    }

    @Override
    public void run() {
        // LLamamos al método realizarProceso.
        realizarProceso();
    }

    /**
     * Este método se encarga de comparar los números y enviar la respuesta al cliente.
     */
    public void realizarProceso() {
        try {
            String mensajeRecibido = new String(datagramaEntrada.getData()).trim();
            String sol = "";
            // Si el número introducido es igual al secreto lo diremos en la respuesta.
            if (Integer.parseInt(mensajeRecibido)==this.numSecreto){
                sol = "Es igual al número secreto";
            } else {
                // Si el número introducido es menor al secreto lo diremos en la respuesta.
                if(Integer.parseInt(mensajeRecibido)<this.numSecreto){
                    sol = "Es menor al número secreto";
                // Si el número introducido es mayor al secreto lo diremos en la respuesta.
                }else
                    sol = "Es mayor al número secreto";
            }
            byte[] mensajeEnviado = sol.getBytes();
            DatagramPacket packetSalida = new DatagramPacket(mensajeEnviado, mensajeEnviado.length,datagramaEntrada.getAddress(), datagramaEntrada.getPort());
            // Enviamos el mensaje al cliente.
            socket.send(packetSalida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}