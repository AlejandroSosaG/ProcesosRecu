package tema1.ejercicios.ejercicio7;

public class Clase1 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(aleatorio() + "." + aleatorio() + "." + aleatorio() + "." + aleatorio());
        }
    }
    public static int aleatorio(){
        int num;
        num = (int) (Math.random()*256);
        return num;
    }
}
