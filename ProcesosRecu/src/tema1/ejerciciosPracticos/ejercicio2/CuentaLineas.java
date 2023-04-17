package tema1.ejerciciosPracticos.ejercicio2;

import java.io.*;
import java.util.Scanner;
public class CuentaLineas {
    public static void main(String[] args) {
        int contador = 0;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("C:\\ejercicio2\\numLineas.txt"));
            Scanner sc = new Scanner(bf);
            while (sc.hasNext()){
                contador++;
                sc.next();
            }
            System.out.println(contador);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
