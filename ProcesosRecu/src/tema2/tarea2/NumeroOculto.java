package tema2.tarea2;

import java.util.Scanner;

public class NumeroOculto extends Thread{
    public static int numOculto; // Variable donde guardaremos el número secreto.
    public static boolean adivinado = false; // Variable que utilizaremos para saber si alguien ha adivinado el número.
    public static void main(String[] args) {
        numOculto = (int) (Math.random()*101); // Guardamos un valor aleatorio entre 0 y 100.
        /**
         * Recorremos el bucle hasta que i sea mayor o igual a 10 mientras i va aumentando.
         */
        for (int i = 0; i < 10; i++) {
            // Creamos un nuevo hilo y le ponemos nombre.
            NumeroOculto hilo = new NumeroOculto();
            hilo.setName("hilo " + (i+1));
            // Lanzamos el hilo actual.
            hilo.start();
        }
    }
    @Override
    public void run(){
        int num, sol = 0;
        Scanner sc = new Scanner(System.in);
        while (sol == 0){
            // La máquina irá proponiendo números entre 0 y 100 hasta que algún hilo lo adivine.
            num = (int) (Math.random()*101);
            sol = propuestaNumero(num);
            // A continuación mostramos por pantalla el número propuesto y el resultado de esa propuesta.
            System.out.println("El " + Thread.currentThread().getName() + " propone el número: " + num);
            System.out.println("Solución: " + sol);
            try {
                // Hacemos que el hilo actual espere 1 segundo.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Al terminar se muestra quien ha ganado y cuál era el número.
        if (sol == 1)
            System.out.println("El " + this.getName() + " ha adivinado el número oculto: " + numOculto);
    }

    /**
     * Metodo que utilizaremos para comprobar si el hilo actual ha acertado el número o no.
     * @param num
     * @return Devolvemos el resultado de la comparación.
     */
    public static int propuestaNumero(int num){
        int sol = 0; // Variable en la que guardaremos el resultado y que inicializamos con valor 0.
        // Si el número ha sido adivinado guardamos en sol el valor -1.
        if (adivinado){
            sol = -1;
            // Si no ha sido adivinado y num es igual al número secreto guardamos 1 en sol.
        }else if (num == numOculto){
            sol = 1;
            adivinado = true;
        }
        return sol;
    }
}
