package tema1.ejercicios.ejercicio7;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanzaClases {
    public static void main(String[] args) {
        String []comando1 = {"java", ".\\src\\tema1\\ejercicios\\ejercicio7\\Clase1.java"};
        String []comando2 = {"java", ".\\src\\tema1\\ejercicios\\ejercicio7\\Clase2.java"};
        String []comando3 = {"java", ".\\src\\tema1\\ejercicios\\ejercicio7\\Clase3.java"};
        ProcessBuilder pb1 = new ProcessBuilder(comando1);
        ProcessBuilder pb2 = new ProcessBuilder(comando2);
        ProcessBuilder pb3 = new ProcessBuilder(comando3);
        List<ProcessBuilder> lpb = new ArrayList<>();
        lpb.add(pb1);
        lpb.add(pb2);
        lpb.add(pb3);
        pb3.redirectOutput(new File(".\\src\\tema1\\ejercicios\\ejercicio7\\fichero.txt"));
        pb1.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb2.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb3.redirectError(ProcessBuilder.Redirect.INHERIT);
        try {
            ProcessBuilder.startPipeline(lpb);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
