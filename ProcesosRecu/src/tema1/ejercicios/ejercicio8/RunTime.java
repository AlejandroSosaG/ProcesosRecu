package tema1.ejercicios.ejercicio8;

import java.io.*;
public class RunTime {

    public static void main(String[] args) {
        // Creamos la ruta del proceso que queremos lanzar.
        String comando[] = {"java", ".\\src\\tema1\\ejercicios\\ejercicio2\\EjemplosLento.java"};
        // Creamos el lanzador que utilizaremos.
        Runtime rt = Runtime.getRuntime();
        try {
            // Creamos un escritor de ficheros en el que le pasamos.
            FileWriter fw = new FileWriter(".\\src\\tema1\\ejercicios\\ejercicio8\\salidaProcesoLento2");
            BufferedWriter bw = new BufferedWriter(fw);
            // Lanzamos el proceso.
            Process p = rt.exec(comando);
            // Creamos un stream de entrada conectado con la salida estándar del proceso.
            InputStream is = p.getInputStream();
            // Creamos un lector de stream de entrada conectado con la salida estándar del proceso.
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            // Guardamos el contenido del fichero en la variable linea.
            String linea = br.readLine();
            // Mietras siga habiendo contenido mostramos por pantalla un mensaje y lo añadimos al escritor.
            while (!linea.isEmpty()){
                System.out.println("El proceso está en ejecución");
                bw.write(linea);
                // Añadimos una nueva linea al escritor.
                bw.newLine();
                linea = br.readLine(); // Guardamos en la variable linea el nuevo contenido del fichero.
            }
            // Mostramos por pantalla un mensaje final.
            System.out.println("El proceso ha terminado");
        }catch (IOException e){
            System.err.println("Ha habido un error al lanzar el proceso");
            e.printStackTrace();
        }
    }
}
