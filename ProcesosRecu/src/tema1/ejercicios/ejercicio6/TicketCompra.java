package tema1.ejercicios.ejercicio6;

import java.util.Scanner;

public class TicketCompra {
    public static void main(String[] args) {
        int codigo, cantidad;
        double precio, total;
        Scanner sc = new Scanner(System.in);
        codigo = sc.nextInt();
        precio = sc.nextDouble();
        cantidad = sc.nextInt();
        total = precio*cantidad;
        System.out.println(codigo + "-" + precio + "-" + cantidad + "-" + total);
    }
}
