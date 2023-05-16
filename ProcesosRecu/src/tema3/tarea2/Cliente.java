package tema3.tarea2;

import java.io.IOException;
import java.net.*;
import java.util.Random;

public class Cliente {
    public static void main(String[] args) {
        Random r = new Random();
        //  Obtener dirección IP local
        InetAddress direccion;
        try {
            direccion = InetAddress.getLocalHost();
            // Creación del socket
            DatagramSocket socket = new DatagramSocket();
            //  Creación del mensaje
            int numeroMensaje = 0;
            String mensaje;
            while (numeroMensaje<10000){
                mensaje = "Mensaje: " + numeroMensaje;
                byte[] buffer = mensaje.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccion, 42000);
                socket.send(packet);
                numeroMensaje++;
            }
            mensaje = "FIN";
            byte[] buffer = mensaje.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccion, 42000);
            socket.send(packet);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}