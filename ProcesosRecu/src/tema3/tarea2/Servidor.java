package tema3.tarea2;

import java.io.*;
import java.net.*;
public class Servidor {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(42000);
            System.out.println("Creación del arra de bytes");
            System.out.println("A la espera de recibir datagrama");
            String mensaje = "";
            File fichero = new File(".\\ProcesosRecu\\src\\tema3\\tarea2\\mensajes.txt");
            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);
            while(!mensaje.equals("FIN")) {
                byte[] buffer = new byte[64];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                mensaje = new String(packet.getData()).trim();
                if (!mensaje.equals("FIN"))
                    bw.write(mensaje.substring(9) + " ");
                bw.newLine();
            }
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