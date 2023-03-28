package tema1.ejercicios.ejercicio8;

import java.io.*;
public class RunTime {

    public static void main(String[] args) {
        String comando[] = {"java", ".\\src\\tema1\\ejercicios\\ejercicio2\\EjemplosLento.java"};
        Runtime rt = Runtime.getRuntime();

        try {
            FileWriter fw = new FileWriter(".\\src\\tema1\\ejercicios\\ejercicio8\\salidaProcesoLento2");
            BufferedWriter bw = new BufferedWriter(fw);
            Process p = rt.exec(comando);
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String linea = br.readLine();
            while (!linea.isEmpty()){
                System.out.println("El proceso está en ejecución");
                bw.write(linea);
                bw.newLine();
                linea = br.readLine();
            }
            System.out.println("El proceso ha terminado");
        }catch (IOException e){
            System.err.println("Ha habido un error al lanzar el proceso");
            e.printStackTrace();
        }
    }
}
