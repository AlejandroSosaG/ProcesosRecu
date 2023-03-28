package tema1.ejercicios.ejercicio7;

import java.util.Scanner;

public class Clase3 {
    public static void main(String[] args) {
        String ip;
        String []array;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            ip = sc.nextLine();
            array = ip.split("\\.");
            if (Integer.parseInt(array[0]) >= 0 && Integer.parseInt(array[0]) < 128){
                System.out.println(ip + " es de clase A");
            } else if (Integer.parseInt(array[0]) >= 128 && Integer.parseInt(array[0]) < 192) {
                System.out.println(ip + " es de clase B");
            }else if (Integer.parseInt(array[0]) >= 192 && Integer.parseInt(array[0]) < 223){
                System.out.println(ip + " es de clase C");
            }
        }
    }
}
