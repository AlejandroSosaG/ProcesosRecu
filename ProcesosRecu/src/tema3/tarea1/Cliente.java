package tema3.tarea1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        // Creación de escaner.
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca la ruta completa del fichero a leer");
        // Creamos una variable donde guardamos la ruta del fichero.
        String ruta = sc.next();
        // Creamos un objeto de tipo dirección donde guardaremos la dirección IP local.
        InetAddress direccion;
        try {
            direccion = InetAddress.getLocalHost();
            // Creamos un objeto de tipo cliente pasandole la dirección y el puerto.
            Socket socketClliente = new Socket(direccion, 1500);
            System.out.println("Sevidor: Abriendo flujos de entrada y salida");
            // Creamos objetos de tipo entrada, salida y escritura.
            OutputStream os = socketClliente.getOutputStream();
            InputStream is = socketClliente.getInputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            System.out.println("Cliente envia mensaje al servidor");
            // Se envia la ruta del archivo al servidor.
            bw.write(ruta);
            bw.newLine();
            bw.flush();
            // Creamos opbjetos de tipo lectura que utilizaremos para leer la respuesta del servidor.
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println("Contenido del fichero: " + br.readLine());
            // Cerramos los objetos creados anteriormente.
            osw.close();
            is.close();
            os.close();
            bw.close();
            isr.close();
            br.close();
            socketClliente.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}