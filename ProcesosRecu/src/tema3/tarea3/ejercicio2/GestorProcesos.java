package tema3.tarea3.ejercicio2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GestorProcesos extends Thread{
    private Socket socket;
    private OutputStream os;
    private OutputStreamWriter osw;
    String mensaje;
    public GestorProcesos(String mensaje, Socket socket) {
        this.socket = socket;
        this.mensaje = mensaje;
    }
    @Override
    public void run() {
        try {
            realizarProceso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void realizarProceso() throws IOException {
        os = socket.getOutputStream();
        osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        osw.write(lecturaFichero(mensaje));
        bw.newLine();
        bw.flush();
    }
    public static String lecturaFichero(String web) {
        BufferedReader br;
        String[] contenido;
        String ip = "La direccion introducida no está registrada";
        try {
            br = new BufferedReader(new FileReader(".\\ProcesosRecu\\src\\tema3\\tarea3\\ejercicio2\\direcciones.txt"));
            Scanner sc = new Scanner(br);
            while (sc.hasNext()) {
                contenido = sc.nextLine().split(" ");
                System.out.println(contenido[0]);
                if (contenido[0].equals(web))
                    ip = contenido[1];
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ip;
    }
}