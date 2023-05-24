package tema4.tarea2;

import java.io.*;
import java.security.Key;
import java.util.Scanner;

public class Descifrado {
    public static void main(String[] args) {
        String contraseña, texto, textoDescifrado;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca la contraseña");
        contraseña = sc.nextLine();
        Key clave = Metodos.obtenerClave(contraseña);
        texto = leerFichero();
        textoDescifrado = Metodos.descifrar(clave, texto);
        System.out.println(textoDescifrado);
    }
    /**
     * Método que leerá un fichero y devolverá un String con el contenido de este
     * @return
     */
    public static String leerFichero() {
        String contenido = "";
        try {
            File fichero = new File(".\\ProcesosRecu/src/tema4/tarea2/textos.txt");
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);
            Scanner sc = new Scanner(br);
            // Se lee el fichero
            while (sc.hasNext()) {
                contenido = sc.nextLine();
            }
            sc.close();
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contenido;
    }
}
