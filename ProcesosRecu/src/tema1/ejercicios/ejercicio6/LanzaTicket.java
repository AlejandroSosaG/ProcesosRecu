package tema1.ejercicios.ejercicio6;

import java.io.File;
import java.io.IOException;
public class LanzaTicket {
    public static void main(String[] args) {
        String []comando = {"java", ".\\src\\tema1\\ejercicios\\ejercicio6\\TicketCompra.java"};
        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(new File(".\\src\\tema1\\ejercicios\\ejercicio6\\Ticket.txt")));
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        try {
            Process p = pb.start();
            p.waitFor();
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
