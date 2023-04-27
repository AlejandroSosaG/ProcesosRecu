package tema2.tarea7;

public class Filosofo extends Thread{
    private Mesa mesa;
    private int comensal;
    private int indiceComensal;
    public Filosofo(Mesa m, int comensal){
        this.mesa = m;
        this.comensal = comensal;
        this.indiceComensal = comensal - 1;
    }
    public static void main(String[] args) {
        Mesa m = new Mesa(5);
        for (int i = 0; i < 5; i++) {
            Filosofo f = new Filosofo(m, i);
            f.start();
        }
    }
    public void run(){
        while(true){
            this.pensando();
            this.mesa.cogerTenedores(this.indiceComensal);
            this.comiendo();
            System.out.println("Filosofo " + comensal +  " deja de comer, tenedores libres: " + (this.mesa.tenedorIzquierda(this.indiceComensal) + 1) + ", " + (this.mesa.tenedorDerecha(this.indiceComensal) + 1) );
            this.mesa.dejarTenedores(this.indiceComensal);
        }
    }
    public void pensando(){
            System.out.println("Filosofo " + comensal + " esta pensando");
            try {
                this.sleep((long) (Math.random()*5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    public void comiendo(){
        System.out.println("Filosofo " + comensal + " esta comiendo");
        try {
            this.sleep((long) (Math.random()*5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
