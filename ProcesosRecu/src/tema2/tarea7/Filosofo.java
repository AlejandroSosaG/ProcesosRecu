package tema2.tarea7;

public class Filosofo extends Thread{
    // Creamos un objeto Mesa.
    private Mesa mesa;
    private int comensal; // Este es el número del comensal.
    private int indiceComensal; // Este es el índice del comensal.

    /**
     * Construimos un objeto Filosofo pasándole dos parámetros.
     * @param m Objeto Mesa ya creado anteriormente.
     * @param comensal número de comensal.
     */
    public Filosofo(Mesa m, int comensal){
        this.mesa = m;
        this.comensal = comensal;
        this.indiceComensal = comensal - 1;
    }
    public static void main(String[] args) {
        // Creamos un nuevo objeto de tipo mesa.
        Mesa m = new Mesa(5);
        /**
         * Recorremos el bucle que se encarga de crear filosofos y lanzarlos 5 veces.
         */
        for (int i = 0; i < 5; i++) {
            Filosofo f = new Filosofo(m, i);
            f.start();
        }
    }
    public void run(){
        /**
         * Mediante un bucle infinito llamamos a los distintos métodos de la clase.
         */
        while(true){
            this.pensando();
            this.mesa.cogerTenedores(this.indiceComensal);
            this.comiendo();
            // Mostramos un mensaje por pantalla que diga el filósofo que termina de comer y los tenedores que deja libres.
            this.mesa.dejarTenedores(this.indiceComensal);
            System.out.println("Filosofo " + comensal +  " deja de comer, tenedores libres: " + (this.mesa.tenedorIzquierda(this.indiceComensal) + 1) + ", " + (this.mesa.tenedorDerecha(this.indiceComensal) + 1) );
        }
    }
    /**
     * Método que muestra un mensaje diciendo que el filósofo está pensando y espera un tiempo aleatorio
     * de hasta 5 segundos.
     */
    public void pensando(){
            System.out.println("Filosofo " + comensal + " esta pensando");
            try {
                this.sleep((long) (Math.random()*5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    /**
     * Método que dice el filósofo que está pensando y que espera una cantidad aleatoria de hasta 5 segundos.
     */
    public void comiendo(){
        System.out.println("Filosofo " + comensal + " esta comiendo con los tenedores "  + (this.mesa.tenedorIzquierda(this.indiceComensal) + 1) + " y " + (this.mesa.tenedorDerecha(this.indiceComensal) + 1) );
        try {
            this.sleep((long) (Math.random()*5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
