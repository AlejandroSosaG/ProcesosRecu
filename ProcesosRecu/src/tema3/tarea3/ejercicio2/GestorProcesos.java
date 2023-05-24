package tema3.tarea3.ejercicio2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GestorProcesos extends Thread{
    private Socket socket;
    private OutputStream os;
    private OutputStreamWriter osw;
    String mensaje;

    /**
     * Constructor con parámetros.
     * @param mensaje
     * @param socket
     */
    public GestorProcesos(String mensaje, Socket socket) {
        this.socket = socket;
        this.mensaje = mensaje;
    }
    @Override
    public void run() {
        try {
            // Llamamos al método realizarProceso.
            realizarProceso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que enviará el mensaje devuelto por el método lecturaFichero.
     * @throws IOException
     */
    public void realizarProceso() throws IOException {
        // Creamos objetos de tipo salida y escritura.
        os = socket.getOutputStream();
        osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        // Mandamos la llamada al método lecturaFichero pasándole por parámetro la variable mensaje.
        osw.write(lecturaFichero(mensaje));
        bw.newLine();
        bw.flush();
    }

    /**
     * Método que se encarga de leer el fichero y buscar la ruta de la dirección que se le pasa por parámetro.
     * @param web
     * @return Devuelve la dirección del nombre pasado por parámetro o un mensaje diciendo que esa dirección no está.
     */
    public static String lecturaFichero(String web) {
        BufferedReader br;
        String[] contenido;
        String ip = "La direccion introducida no está registrada";
        try {
            // Creamos objetos de lectura.
            br = new BufferedReader(new FileReader(".\\ProcesosRecu\\src\\tema3\\tarea3\\ejercicio2\\direcciones.txt"));
            Scanner sc = new Scanner(br);
            /**
             * Recorremos el bucle mientras no haya nada más que leer del fichero,
             */
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