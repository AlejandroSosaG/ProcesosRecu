package tema3.tarea3.ejercicio2;

import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        try {
            // Creación del socket de tipo servidor.
            ServerSocket servidor = new ServerSocket(42000);
            InputStream is;
            BufferedReader br;
            InputStreamReader ir;
            String mensajeRecibido = "";
            Socket socket;
            /**
             * Mientras el mensaje recibido no sea nulo aceptaremos la conexión entre servidor y cliente,
             * leeremos el mensaje recibido, crearemos un objeto de tipo GestorProcesos y lo lanzaremos.
             */
            while (mensajeRecibido != null) {
                socket = servidor.accept();
                // Creamos objetos de tipos entrada y lectura.
                is = socket.getInputStream();
                ir = new InputStreamReader(is);
                br = new BufferedReader(ir);
                // Leemos el mensaje del cliente
                mensajeRecibido = br.readLine();
                // Generamos un nuevo GestorProcesos y lo lanzamos.
                new GestorProcesos(mensajeRecibido, socket).start();
            }
        } catch (IOException e) {
            System.err.println("Ha habido algún error en la creación del Socket Servidor");
            e.printStackTrace();
        }
    }
}