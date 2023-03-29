package tema1.ejercicios.ejercicio9;

import java.io.*;

public class LanzaNombreRunTime {
    public static void main(String[] args) {
        String comando[] = {"java", ".\\src\\tema1\\ejercicios\\ejercicio5\\PreguntaNombre.java"};
        Runtime rt = Runtime.getRuntime();

        try {
            FileReader fr = new FileReader(".\\src\\tema1\\ejercicios\\ejercicio5\\nombre.txt");
            Process p = rt.exec(comando);
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            OutputStream os = p.getOutputStream();
            String linea = br.readLine();
            System.out.println(linea);  
        }catch (IOException e){
            System.err.println("Ha habido un error al lanzar el proceso");
            e.printStackTrace();
        }
    }
}
