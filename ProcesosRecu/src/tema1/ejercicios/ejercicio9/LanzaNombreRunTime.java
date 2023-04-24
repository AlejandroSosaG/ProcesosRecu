package tema1.ejercicios.ejercicio9;

import java.io.*;

public class LanzaNombreRunTime {
    public static void main(String[] args) {
        // Creamos la ruta del proceso que queremos lanzar.
        String comando[] = {"java", ".\\ProcesosRecu\\ProcesosRecu\\src\\tema1\\ejercicios\\ejercicio5\\PreguntaNombre.java"};
        // reamos el lanzador que utilizaremos.
        Runtime rt = Runtime.getRuntime();
        try {
            // reamos un lector de ficheros en el que le pasamos.
            FileReader fr = new FileReader(".\\ProcesosRecu/src/tema1/ejercicios/ejercicio5/nombre.txt");
            BufferedReader br = new BufferedReader(fr);
            // Lanzamos el proceso.
            Process p = rt.exec(comando);
            // Creamos un stream de salida conectado con la entrada estándar del proceso.
            OutputStream os = p.getOutputStream();
            // Creamos un escritor de stream de salida conectado con la entrada estándar del proceso.
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            // Guardamos el contenido del fichero en la variable entrada.
            String entrada = br.readLine();
            // Mietras siga habiendo contenido lo añadimos al escritor.
            while(entrada!=null) {
                bw.write(entrada);
                bw.flush();
                // Añadimos una nueva linea al escritor.
                bw.newLine();
                // Guardamos en la variable entrada el nuevo contenido del fichero.
                entrada = br.readLine();
            }
            // Cerramos el escritor.
            bw.close();
            // Creamos un stream de entrada conectado con la salida estándar del proceso.
            InputStream is = p.getInputStream();
            // Creamos un lector de stream de entrada conectado con la salida estándar del proceso.
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            // Guardamos el contenido del fichero en la variable salida.
            String salida = br.readLine();
            // Mietras siga habiendo contenido lo mostramos por pantalla.
            while(salida!=null) {
                System.out.println(salida);
                salida = br.readLine(); // Guardamos en la variable salida el nuevo contenido del fichero.
            }
            // Cerramos los lectores y escritores.
            fr.close();
            br.close();
            os.close();
            osw.close();
            is.close();
            isr.close();
        }catch (IOException e){
            System.err.println("Ha habido un error al lanzar el proceso");
        }
    }
}
