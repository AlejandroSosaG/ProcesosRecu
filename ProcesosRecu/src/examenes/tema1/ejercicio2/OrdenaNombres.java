package examenes.tema1.ejercicio2;

import java.io.*;
import java.util.*;

/**
 * Esta clase lee una serie de líneas de la entrada estándar, las cuales están
 * compuestas por los apellidos y los nombres de un listado de alumnos. Estas
 * líneas tienen el formato: Apellido1 Apellido2, Nombre
 * A continuación, pinta por la salida estándar el mismo listado pero ordenado
 * por nombre en vez de por apellido. Además, pinta cada nombre con el siguiente
 * formato: Nombre Apellido1 Apellido2
 */
public class OrdenaNombres {
    public static void main(String[] args) throws FileNotFoundException {
        // Línea que contendrá los datos de un línea.
        // La voy a ir leyendo de la entrada estándar
        String linea;
        File file = new File(args[0]);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        // Creo el Scanner para leer de la entrada estándar
        Scanner sc = new Scanner(br);
        String[] line = new String[2];
        // Mientras haya líneas que leer seguiremos leyendo
        while (sc.hasNextLine()) {
            linea = sc.nextLine();
            line = linea.split(";");
            String[] nombre = line[0].split(",");
            System.out.println(nombre[1] + " " + nombre[0]);
        }
        sc.close();
    }
}