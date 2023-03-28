package tema1.ejercicios.ejercicio7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Clase2 {
    public static void main(String[] args) {
        String ip;
        String []array;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            ip = sc.nextLine();
            array = ip.split("\\.");
            if (Integer.parseInt(array[0]) >= 0 && Integer.parseInt(array[0]) <= 223){
                System.out.println(ip);
            }
        }
    }
}
