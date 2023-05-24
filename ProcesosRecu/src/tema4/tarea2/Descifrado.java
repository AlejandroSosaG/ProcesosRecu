package tema4.tarea2;

import java.io.*;
import java.security.Key;
import java.util.Scanner;

public class Descifrado {
    public static void main(String[] args) {
        String contraseña, texto, textoDescifrado;
        // Creación de escaner.
        Scanner sc = new Scanner(System.in);
        // Pedimos al usuario la contraseña para descifrar.
        System.out.println("Introduzca la contraseña");
        contraseña = sc.nextLine();
        // Guardamos en clave la devolución del método obtenerClave de la clase Metodos.
        Key clave = Metodos.obtenerClave(contraseña);
        // Guardamos la devolución del método leerFichero en texto.
        texto = leerFichero();
        // Guardamos en textoDescifrado la devolución del método descifrar de la clase Metodos.
        textoDescifrado = Metodos.descifrar(clave, texto);
        // Muestra por pantalla el contenido de la variable textoDescifrado.
        System.out.println(textoDescifrado);
    }
    /**
     * Método que se encarga de leer un fichero y devolver una cadena con el contenido del fichero.
     * @return Se devuelve el contenido del fichero que leemos.
     */
    public static String leerFichero() {
        String contenido = "";
        try {
            // Creamos objetos de tipo fichero, lectura y escaner.
            File fichero = new File(".\\ProcesosRecu/src/tema4/tarea2/textos.txt");
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);
            Scanner sc = new Scanner(br);
            // Leemos el fichero.
            while (sc.hasNext()) {
                contenido = sc.nextLine();
            }
            // Cerramos los objetos creados anteriormente.
            sc.close();
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        // Devolvemos el contenido del fichero.
        return contenido;
    }
}
