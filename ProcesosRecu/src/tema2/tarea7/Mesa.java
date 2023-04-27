package tema2.tarea7;

public class Mesa {
    private boolean[] tenedores;
    public Mesa(int numTenedores){
        this.tenedores = new boolean[numTenedores];
    }
    public int tenedorIzquierda(int i){
        return i;
    }
    public int tenedorDerecha(int i){
        if(i == 0){
            return this.tenedores.length - 1;
        }else{
            return i - 1;
        }
    }
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
    public synchronized void dejarTenedores(int comensal){
        tenedores[tenedorIzquierda(comensal)] = false;
        tenedores[tenedorDerecha(comensal)] = false;
        notifyAll();
    }
}
