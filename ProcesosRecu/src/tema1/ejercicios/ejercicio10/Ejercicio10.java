package tema1.ejercicios.ejercicio10;

public class Ejercicio10 {
    public static void main(String[] args) {
        // Creamos las rutas de los archivos que queremos utilizar.
        String[] comando1 = {"cmd", "/C", "md", "C:\\pruebas\\carpeta1"};
        String[] comando2 = {"cmd", "/C", "type", "null", ">", "C:\\pruebas\\carpeta1\\fichero1.txt"};
        String[] comando3 = {"Notepad.exe", "C:\\pruebas\\carpeta1\\fichero1.txt"};
        // Creamos los lanzadores, les añadimos las rutas de los procesos y les decimos que muestren las entrada, salida y error por pantalla.
        ProcessBuilder pb1 = new ProcessBuilder(comando1);
        pb1.inheritIO();
        ProcessBuilder pb2 = new ProcessBuilder(comando2);
        pb2.inheritIO();
        ProcessBuilder pb3 = new ProcessBuilder(comando3);
        pb3.inheritIO();
        try {
            // Lanzamos los procesos y hacemos los hilos esperen a que terminen los procesos.
            Process p1 = pb1.start();
            p1.waitFor();
            Process p2 = pb2.start();
            p2.waitFor();
            Process p3 = pb3.start();
            p3.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
