package tema2.tarea1;

public class HiloDurmiente extends Thread{
    public static void main(String[] args) {
        /**
         * Recorremos el bucle hasta que i sea mayor o igual a 5 mientras i va incrementando.
         */
        for (int i = 0; i < 5; i++) {
            // Creamos un nuevo hilo y le ponemos nombre.
            Thread hilo = new HiloDurmiente();
            hilo.setName("hilo " + (i+1));
            // Lanzamos el hilo actual.
            hilo.start();
        }
    }
    @Override
    public void run(){
        // Bucle infinito.
        while (true){
            // Mostramos por pantalla un mensaje.
            System.out.println("Soy el bucle " + this.getName() + " y estoy trabajando");
            try {
                // Hacemos que el hilo actual espere una cantidad aleatoria entre 1 y 10 segundos.
                Thread.sleep((int) (Math.random()*10+1)*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
