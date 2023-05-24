package tema4.tarea1;

import java.io.*;
import java.util.Scanner;
import java.math.BigInteger;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String usu, contraseña, cuenta, resumen;
        byte[] contraseñaRecogida, contraseñaEncriptada;
        System.out.println("Introduzca su usuario");
        usu = sc.next();
        sc.nextLine();
        System.out.println("Introduzca su contraseña");
        contraseña= sc.nextLine();
        cuenta = usu + contraseña;
        contraseñaRecogida = cuenta.getBytes();
        contraseñaEncriptada = Hash.getDigest(contraseñaRecogida, "SHA-256");
        resumen = String.format("%064x", new BigInteger(1, contraseñaEncriptada));
        escribirFichero(resumen);
        sc.close();
    }
    public static void escribirFichero(String mensaje){
        System.out.println(mensaje);
        try {
            String filePath = ".\\ProcesosRecu/src/tema4/tarea1/credenciales.cre";
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(mensaje);
            bw.newLine();
            bw.close();
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}