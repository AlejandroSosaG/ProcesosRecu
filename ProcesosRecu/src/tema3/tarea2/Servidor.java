package tema3.tarea2;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            // Creamos un datagrama con el puerto que debe aceptar.
            DatagramSocket socket = new DatagramSocket(42000);
            String mensaje = ""; // Variable donde guardaremos el mensaje que nos enviará el cliente.
            // Creamos el fichero donde guardaremos los números de los mensajes recibidos.
            File fichero = new File(".\\ProcesosRecu\\src\\tema3\\tarea2\\mensajes.txt");
            // Creamos objetos de tipo lector.
            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);
            /**
             * Mientras el mensaje recibido no sea FIN recibiremos el paquete creado anteriormente
             * y escribiremos su número en el fichero.
             */
            while (!mensaje.equals("FIN")) {
                byte[] buffer = new byte[64];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                System.out.println("A la espera de recibir datagrama");
                socket.receive(packet);
                mensaje = new String(packet.getData()).trim();
                if (!mensaje.equals("FIN"))
                    bw.write(mensaje.substring(9) + " ");
                bw.newLine();
            }
            // Cerramos los objetos creados.
            bw.close();
            fw.close();
        } catch (SocketException e) {
            System.err.println("Error en la creación del socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error en la recepción del paquete");
            e.printStackTrace();
        }
    }
}