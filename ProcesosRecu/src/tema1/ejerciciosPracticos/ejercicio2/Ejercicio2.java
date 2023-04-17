package tema1.ejerciciosPracticos.ejercicio2;

import java.io.IOException;

public class Ejercicio2 {
    public static void main(String[] args) throws InterruptedException {
        String[] comando1 = {"Notepad.exe",  "C:\\ejercicio2\\numLineas.txt"};
        String[] comando2 = {"java", "CuentaLineas"};
        ProcessBuilder pb1 = new ProcessBuilder(comando1);
        ProcessBuilder pb2 = new ProcessBuilder(comando2);
        pb1.inheritIO();
        pb2.inheritIO();
        try {
            Process p1 = pb1.start();
            int segundos = 0;
            while (p1.isAlive() && segundos<30 ){
                segundos++;
                Thread.sleep(1000);
            }
            if (segundos >= 30){
                p1.destroy();
                System.err.println("El proceso se ha cerrado");
            }
            pb2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
