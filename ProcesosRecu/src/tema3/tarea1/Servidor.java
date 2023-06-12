package tema3.tarea1;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        String contenido = ""; // Esta variable guardará la información que pasa el cliente.
        System.out.println("Sevidor: Abriendo conexión");
        // Creamos un objeto de tipo servidor.
        ServerSocket socketServidor;
        try {
            // Le introducimos al servidor un número de puerto.
            socketServidor = new ServerSocket(1500);
            System.out.println("Sevidor: aceptando conexión");
            // Creamos un objeto de tipo socket y aceptamos la conexión con el servidor.
            Socket socketCliente = socketServidor.accept();
            System.out.println("Sevidor: Abriendo flujos de entrada y salida");
            // Creamos objetos de tipos entrada, salida y lectores.
            InputStream is = socketCliente.getInputStream();
            OutputStream os = socketCliente.getOutputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            // Creamos una variable donde guardamos la ruta del archivo a leer.
            String ruta = br.readLine();
            // Creamos un archivo y un lector para la ruta que nos pasa el cliente.
            File archivo = new File (ruta);
            FileReader fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            String linea; // En esta variable donde introduciremos el mensaje línea por línea.
            // Mientras el lector no termine de leer el archvo guardaremos cada línea en la variable contenido.
            while ((linea = br.readLine())!=null) contenido += ("\n" + linea);
            System.out.println("Sevidor envia mensaje al cliente");
            // Creamos objetos de tipo escritura.
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            // Enviamos al cliente el contenido del fichero.
            bw.write(contenido);
            bw.newLine();
            bw.flush();
            // Cerramos los objetos creados anteriormente.
            fr.close();
            br.close();
            isr.close();
            is.close();
            os.close();
            socketCliente.close();
            socketServidor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}