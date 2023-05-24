package tema4.tarea2;

import java.io.*;
import java.security.Key;
import java.util.Scanner;

public class Cifrado {
    public static void main(String[] args) {
        String contraseña, texto, textoCifrado;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca la contraseña");
        contraseña = sc.nextLine();
        Key clave = Metodos.obtenerClave(contraseña);
        System.out.println("Introduzca un mensaje");
        texto = sc.nextLine();
        textoCifrado = Metodos.cifrar(clave, texto);
        escribirFichero(textoCifrado);
    }
    /**
     * Método para escribir en un fichero un String recibido por parámetro
     * @param mensaje
     */
    public static void escribirFichero(String mensaje){
        try {
            File fichero = new File(".\\ProcesosRecu/src/tema4/tarea2/textos.txt");
            FileWriter fw = new FileWriter(fichero, true);
            BufferedWriter bw = new BufferedWriter(fw);
            if (!fichero.exists())
                fichero.createNewFile();
            bw.write(mensaje);
            bw.newLine();
            bw.flush();
            bw.close();
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
