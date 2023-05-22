package tema3.tarea3.ejercicio1;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        int puertoServidor = 42000;
        // Creación de escaner.
        Scanner sc = new Scanner(System.in);
        try {
            // Obtenemos la dirección IP local
            InetAddress direccion = InetAddress.getLocalHost();
            // Creación del socket.
            DatagramSocket socket = new DatagramSocket();
            boolean numeroAdivinado = false; // Esta variable
            while (!numeroAdivinado) {
                System.out.println("Introduzca un número");
                // Creación del mensaje
                int num = sc.nextInt();
                String numero = String.valueOf(num);
                // Creación del array de bytes de salida
                byte[] bufferSalida = numero.getBytes();
                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccion,puertoServidor);
                socket.send(paqueteSalida);
                // Creación del array de bytes
                byte[] bufferEntrada = new byte[64];
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length, direccion,puertoServidor);
                System.out.println("A la espera de recibir datagrama");
                socket.receive(paqueteEntrada);
                String respuesta = new String(bufferEntrada).trim();
                System.out.println(respuesta);
                if(respuesta.equals("Es igual al número secreto"))
                    numeroAdivinado = true;
            }
            socket.close();
            sc.close();
        } catch (SocketException e) {
            System.err.println("Error al conectar con el servidor.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("No se ha podido enviar o recibir el paquete");
            e.printStackTrace();
        }
    }
}