package tema3.tarea2;

import java.io.IOException;
import java.net.*;
import java.util.Random;

public class Cliente {
    public static void main(String[] args) {
        //  Obtenemos dirección IP local.
        InetAddress direccion;
        try {
            direccion = InetAddress.getLocalHost();
            // Creación del socket.
            DatagramSocket socket = new DatagramSocket();
            //  Creación del mensaje.
            int numeroMensaje = 0;
            String mensaje;
            /**
             * Mientras el número del mensaje no llegue a 10000 generamos paquetes donde guardaremos el mensaje
             * y se lo enviaremos al servidor.
             */
            while (numeroMensaje<10000){
                mensaje = "Mensaje: " + numeroMensaje;
                byte[] buffer = mensaje.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccion, 42000);
                socket.send(packet);
                numeroMensaje++;
            }
            // Guardamos en la variable mensaje la palabra FIN y se la enviamos al servidor.
            mensaje = "FIN";
            byte[] buffer = mensaje.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccion, 42000);
            socket.send(packet);
            // Cerramos el socket.
            socket.close();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}