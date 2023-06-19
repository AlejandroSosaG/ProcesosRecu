package tema3.tarea1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class Servidor {
    public static void main(String[] args) {
        String ruta = "";
        String mensaje;
        try {
            // Creación del socket servidor.
            System.out.println("(Servidor): Abriendo conexión...");
            ServerSocket socketServidor = new ServerSocket(1500);
            while (true) {
                // Esperamos y aceptamos conexiones.
                System.out.println("(Servidor): Esperando peticiones...");
                Socket socketCliente = socketServidor.accept();
                // Abrimos flujos de entrada y salida.
                System.out.println("(Servidor): Abriendo flujos de entrada y de salida...");
                InputStream is = socketCliente.getInputStream();
                OutputStream os = socketCliente.getOutputStream();
                // Intercambiamos datos con el cliente.
                System.out.println("(Servidor): Leo mensaje del cliente...");
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
                BufferedWriter bw = new BufferedWriter(osw);
                ruta = br.readLine();
                mensaje = leeRuta(ruta);
                System.out.println("(Servidor): Envío mensaje al cliente...");
                bw.write(mensaje);
                bw.newLine();
                bw.flush();
                // Cerramos los objetos creados.
                System.out.println("(Servidor): Cierre de flujos de lectura y escritura...");
                br.close();
                isr.close();
                is.close();
                bw.close();
                osw.close();
                os.close();
    			socketCliente.close();
                socketServidor.close();
            }
        } catch (FileNotFoundException fnfe){
            System.out.println("ERROR: Fichero no encontrado");
            fnfe.printStackTrace();
        }catch (IOException e) {
            System.err.println("ERROR: Error al crear el socket en el puerto 50000");
            e.printStackTrace();
        }
    }
    public static String leeRuta(String ruta){
        StringBuilder contenido = new StringBuilder();
        try {
            File file = new File(ruta);
            Scanner sc = new Scanner(file);
            if (file.exists()) {
                while (sc.hasNext()){
                    contenido.append(sc.nextLine()).append(" ");
                }
            }
        }catch (FileNotFoundException e){
            contenido.append("ERROR: Fichero no encontrado");
        }
        return contenido.toString();
    }
}