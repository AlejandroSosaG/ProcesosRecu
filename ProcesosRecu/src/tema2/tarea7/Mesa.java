package tema2.tarea7;

public class Mesa {
    // Creamos una lista de tenedores compartidos por los filósofos.
    private boolean[] tenedores;
    public Mesa(int numTenedores){
        this.tenedores = new boolean[numTenedores];
    }
    // Creamos el tenedor izquierdo para que los filósofos lo puedan usar.
    public int tenedorIzquierda(int i){
        return i;
    }
    // Creamos el tenedor derecho para que los filósofos lo puedan usar.
    public int tenedorDerecha(int i){
        if(i == 0){
            return this.tenedores.length - 1;
        }else{
            return i - 1;
        }
    }
    /**
     * Método que se encarga de bloquear dos tenedores mientras se están usando.
     * @param comensal
     */
    public synchronized void cogerTenedores(int comensal){
        while(tenedores[tenedorIzquierda(comensal)] || tenedores[tenedorDerecha(comensal)]){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tenedores[tenedorIzquierda(comensal)] = true;
        tenedores[tenedorDerecha(comensal)] = true;
    }
    /**
     * Este método se encarga de liberar los tenedores bloqueados por los filósofos.
     * @param comensal
     */
    public synchronized void dejarTenedores(int comensal){
        tenedores[tenedorIzquierda(comensal)] = false;
        tenedores[tenedorDerecha(comensal)] = false;
        this.notifyAll();
    }
}
