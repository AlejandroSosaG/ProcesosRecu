package tema4.tarea2;

import java.io.*;
import java.security.Key;
import java.util.Scanner;

public class Cifrado {
    public static void main(String[] args) {
        String contraseña, texto, textoCifrado;
        // Creación de escaner.
        Scanner sc = new Scanner(System.in);
        // Pedimos al usuario que introduzca una contraseña y un texto para cifrar.
        System.out.println("Introduzca la contraseña");
        contraseña = sc.nextLine();
        // LLamamos al método obtenerClave de la clase Metodos.
        Key clave = Metodos.obtenerClave(contraseña);
        System.out.println("Introduzca un mensaje");
        texto = sc.nextLine();
        // Guardamos la devolución del método cifrar de la clase Metodos en la variable textoCifrado.
        textoCifrado = Metodos.cifrar(clave, texto);
        // LLamamos al método escribirFichero.
        escribirFichero(textoCifrado);
    }
    /**
     * Método para escribir en un fichero el texto pasado por parámetro.
     * @param mensaje
     */
    public static void escribirFichero(String mensaje){
        try {
            // Creamos objetos de tipo archivo y lectura.
            File texto = new File("ProcesosRecu/src/tema4/tarea2/textos.txt");
            FileWriter fw = new FileWriter(texto, true);
            BufferedWriter bw = new BufferedWriter(fw);
            // Escribimos el contendo de mensaje en el fichero.
            bw.write(mensaje);
            bw.newLine();
            bw.flush();
            // Cerramos los objetos creados anteriormente.
            bw.close();
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
