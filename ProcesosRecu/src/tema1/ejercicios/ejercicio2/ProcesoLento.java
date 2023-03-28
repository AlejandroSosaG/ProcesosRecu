package tema1.ejercicios.ejercicio2;

import java.io.File;
import java.io.IOException;

public class ProcesoLento {
    public static void main(String[] args) {
        String comando[] = {"java", "C:\\Users\\asosa\\Documents\\ProcesosRecu\\src\\tema1\\ejercicios\\ejercicio2\\EjemplosLento.java"};
        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.directory(new File("C:\\Users\\asosa\\Documents\\ProcesosRecu"));
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        try {
            Process p = pb.start();

            while(p.isAlive()) {
                System.out.println("Sigue en ejecución");
                Thread.sleep(3000);
            }
            System.out.println("El proceso ha terminado");
        }catch (IOException e){
            System.err.println("Ha habido un error al lanzar el proceso");
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
