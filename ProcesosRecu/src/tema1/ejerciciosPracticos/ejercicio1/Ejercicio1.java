package tema1.ejerciciosPracticos.ejercicio1;

import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        menu(); // Llamamos al método menu.
       sc.close(); // Cerramos el escaner.
    }
    public static void menu() {
        int opc; // En opc guardaremos la opción seleccionada por el usuario.
        String ruta; // En ruta se guardará la ruta a utilizar en las distintas opciones.
        String[] comando; // En comando guardaremos el tipo de archivo.
        // Mostramos el menú con las diversas opciones.
        System.out.println("Elija qué comando desea ejecutar:");
        System.out.println("1. Crear carpeta");
        System.out.println("2. Crear fichero");
        System.out.println("3. Mostrar contenido del directorio");
        // Introducimos la opción seleccionada en opc.
        opc = sc.nextInt();
        // Según la opción introducida debemos lanzar un proceso u otro.
        switch (opc) {
            // Si elige crear una carpeta, debo pedirle también el nombre de la carpeta a crear.
            case 1:
                //Le pedimos al usuario que introduzca la ruta completa de la carpeta que quiere crear.
                System.out.println("Introduzca la ruta completa de la carpeta que desea crear");
                ruta = sc.next();
                sc.nextLine();
                // Declaramos la variable comando donde guardamos el comando para crear la carpeta.
                comando = new String[]{"cmd", "/C", "md", ruta};
                crear(comando); // Llamamos al metodo crear.
                break;
            // Si elige crear un fichero, debo pedirle también el nombre del fichero a crear.
            case 2:
                // Pedimos la ruta.
                System.out.println("Introduzca la ruta");
                // Introducimos el valor en la variable ruta.
                ruta = sc.next();
                sc.nextLine();
                System.out.println("Introduzca el nombre del fichero que quiere crear");
                String fichero = sc.nextLine();
                comando = new String[]{"cmd", "/C", "type", "nul", ">", ruta + "\\" + fichero};
                crear(comando); // Llamamos al método crear.
                break;
                // Si elije mostrar contenido creamos la ruta que queremos utilizar y mostramos por
                // pantalla el contenido del diretorio.
            case 3:
                // Pedimos la ruta.
                System.out.println("Introduzca la ruta");
                // Introducimos el valor en la variable ruta.
                ruta = sc.next();
                sc.nextLine();
                comando = new String[]{"cmd", "/C", "dir", ruta};
                crear(comando);
                break;
                // Si no elige una opción valida se lo hacemos saber.
            default:
                System.out.println("La opción introducida no es correcta");
        }
    }
    public static void crear(String[] comando){
        // Creamos el lanzador con el comando que le pasamos por pantalla.
        ProcessBuilder pb = new ProcessBuilder(comando);
        // Le decimo que muestre la entrada, salida y error por pantalla.
        pb.inheritIO();
        try {
            // Lanzamos el proceso.
            Process p = pb.start();
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
