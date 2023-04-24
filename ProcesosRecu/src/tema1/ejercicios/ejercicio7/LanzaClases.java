package tema1.ejercicios.ejercicio7;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanzaClases {
    public static void main(String[] args) {
        // Creamos las rutas de los procesos que queremos lanzar.
        String []comando1 = {"java", ".\\src\\tema1\\ejercicios\\ejercicio7\\Clase1.java"};
        String []comando2 = {"java", ".\\src\\tema1\\ejercicios\\ejercicio7\\Clase2.java"};
        String []comando3 = {"java", ".\\src\\tema1\\ejercicios\\ejercicio7\\Clase3.java"};
        // Creamos los lanzadores y les añadimos las rutas de los procesos.
        ProcessBuilder pb1 = new ProcessBuilder(comando1);
        ProcessBuilder pb2 = new ProcessBuilder(comando2);
        ProcessBuilder pb3 = new ProcessBuilder(comando3);
        // Creamos una lista de lanzadores para lanzarlos todos de seguido.
        List<ProcessBuilder> lpb = new ArrayList<>();
        // Añadimos a la lista los 3 lanzadores.
        lpb.add(pb1);
        lpb.add(pb2);
        lpb.add(pb3);
        // Le decimos que muestre el mensaje de salida en el archivo que le pasamos.
        pb3.redirectOutput(new File(".\\src\\tema1\\ejercicios\\ejercicio7\\fichero.txt"));
        // Le decimos que muestre los mensajes de error por pantalla.
        pb1.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb2.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb3.redirectError(ProcessBuilder.Redirect.INHERIT);
        try {
            // Lanzamos los procesos.
            ProcessBuilder.startPipeline(lpb);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
