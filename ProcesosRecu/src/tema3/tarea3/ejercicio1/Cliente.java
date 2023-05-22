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
            boolean numeroAdivinado = false; // Esta variable dirá si el número se ha adivinado.
            /**
             * Mientras el número no se haya adivinado el usuario introducirá números, los enviaremos al servidor,
             * mostraremos la respuesta recibida y si la respuesta dice que el número enviado es igual al secreto
             * pasaremos la variable numeroAdivinado a true.
             */
            while (!numeroAdivinado) {
                System.out.println("Introduzca un número");
                // Creación del número que escribirá el usuario.
                int num = sc.nextInt();
                String numero = String.valueOf(num);
                // Creación del array de bytes de salida.
                byte[] bufferSalida = numero.getBytes();
                // Creamos el paquete que enviaremos al servidor con el número introducido.
                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccion,puertoServidor);
                socket.send(paqueteSalida);
                // Creación del array de bytes y el paquete donde recibiremos la respuesta del servidor.
                byte[] bufferEntrada = new byte[64];
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length, direccion,puertoServidor);
                System.out.println("A la espera de recibir datagrama");
                socket.receive(paqueteEntrada);
                // Creamos una cadena donde guardaremos la respuesta y la mostraremos por pantalla.
                String respuesta = new String(bufferEntrada).trim();
                System.out.println(respuesta);
                // Si la respuesta dice que es igual al número secreto ponemos numeroAdivinado a true.
                if(respuesta.equals("Es igual al número secreto"))
                    numeroAdivinado = true;
            }
            // Cerramos los objetos creados anteriormente.
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