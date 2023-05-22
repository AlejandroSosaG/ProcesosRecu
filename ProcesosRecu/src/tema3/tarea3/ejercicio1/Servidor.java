package tema3.tarea3.ejercicio1;

import java.io.IOException;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            // Creación de socket.
            DatagramSocket socket = new DatagramSocket(42000);
            // Creamos un número aleatorio entre 0 y 100.
            int numSecreto = (int) (Math.random()*101);
            GestorProcesos gestor;
            /**
             * Mediante un bucle infinito crearemos un paquete para recibir el mensaje del cliente,
             * crearemos un objeto de tipo GestorProcesos y lo lanzaremos.
             */
            while (true){
                System.out.println("Creación del array de bytes");
                byte[] buffer = new byte[64];
                System.out.println("Creación del datagrama");
                DatagramPacket datagramaEntrada = new DatagramPacket(buffer, buffer.length);
                System.out.println("A la espera de recibir datagrama");
                socket.receive(datagramaEntrada);
                gestor = new GestorProcesos(socket, datagramaEntrada, numSecreto);
                gestor.start();
            }
        } catch (SocketException e) {
            System.out.println("Error al crear el Socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al recibir el paquete");
            e.printStackTrace();
        }
    }
}