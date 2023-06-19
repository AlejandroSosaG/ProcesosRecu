package tema4.tarea3;

import java.io.*;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.*;
public class ClavesEmisor {
    //Declaramos las constantes con los nombres de los ficheros donde se guardan las claves
    private static final String FICHERO_CLAVE_PRIVADA = "ProcesosRecu/src/tema4/tarea3/clavePrivadaEmisor.key";
    private static final String FICHERO_CLAVE_PUBLICA = "ProcesosRecu/src/tema4/tarea3/clavePublicaEmisor.key";
    /**
     * Método que genera las claves con el algoritmo RSA
     * @return KeyPair con las claves generadas
     */
    public static KeyPair generarClavesEmisor() {
        //Declaramos las variables
        KeyPairGenerator generador;
        KeyPair claves = null;
        try {
            //Creamos el generador de claves con el algoritmo RSA
            generador = KeyPairGenerator.getInstance("RSA");
            //Inicializamos el generador de claves con un tamaño de 2048
            generador.initialize(2048);
            //Generamos las claves
            claves = generador.generateKeyPair();
        } catch (NoSuchAlgorithmException e) { //Si no existe el algoritmo especificado saltará una excepción
            System.err.println("No existe el algoritmo especificado");
            e.printStackTrace();
        }
        //Devolvemos las claves generadas
        return claves;
    }
    /**
     * Método que guarda las claves en ficheros
     * @param claves claves a guardar
     */
    public static void guardarClaves(KeyPair claves) {
        //Declaramos la variable para escribir en el fichero
        FileOutputStream fos;
        try {
            //Creamos el flujo de salida para el fichero de la clave pública
            fos = new FileOutputStream(FICHERO_CLAVE_PUBLICA);
            //Escribimos la clave pública en el fichero
            fos.write(claves.getPublic().getEncoded());
            //Cerramos el flujo de salida
            fos.close();
            //Creamos el flujo de salida para el fichero de la clave privada
            fos = new FileOutputStream(FICHERO_CLAVE_PRIVADA);
            //Escribimos la clave privada en el fichero
            fos.write(claves.getPrivate().getEncoded());
            //Cerramos el flujo de salida
            fos.close();
        } catch (FileNotFoundException e) { //Si no se encuentra el fichero saltará una excepción
            System.err.println("No se encuentra el fichero.");
            e.printStackTrace();
        } catch (IOException e) { //Si se produce un error durante la escritura saltará una excepción
            System.err.println("Se ha producido un error durante la escritura en el fichero.");
            e.printStackTrace();
        }
    }
    /**
     * Método que lee la clave pública del fichero
     * @return Clave pública
     */
    public static PublicKey getClavePublica() {
        //Declaramos las variables
        File ficheroClavePublica = new File(FICHERO_CLAVE_PUBLICA); //Fichero de la clave pública
        PublicKey clavePublica = null;
        try {
            //Leemos el fichero de la clave pública y lo guardamos en un array de bytes
            byte[] bytesClavePublica = Files.readAllBytes(ficheroClavePublica.toPath());
            //Creamos el objeto KeyFactory con el algoritmo RSA
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            //Creamos la clave pública con el objeto KeyFactory y la clave pública en formato PKCS8
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytesClavePublica);
            //Generamos la clave pública
            clavePublica = keyFactory.generatePublic(publicKeySpec);
        } catch (IOException e) { //Si se produce un error durante la lectura saltará una excepción
            System.err.println("Se ha producido en la lectura del fichero");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) { //Si no existe el algoritmo especificado saltará una excepción
            System.err.println("No existe el algoritmo especificado");
            e.printStackTrace();
        } catch (InvalidKeySpecException e) { //Si la clave no es válida saltará una excepción
            System.err.println("La clave indicada no es válida");
            e.printStackTrace();
        }
        //Devolvemos la clave pública
        return clavePublica;
    }
    /**
     * Método que lee la clave privada del fichero
     * @return Clave privada
     */
    public static PrivateKey getClavePrivada() {
        //Declaramos las variables
        File ficheroClavePrivada = new File(FICHERO_CLAVE_PRIVADA);
        PrivateKey clavePrivada = null;
        try {
            //Leemos el fichero de la clave privada y lo guardamos en un array de bytes
            byte[] bytesClavePrivada = Files.readAllBytes(ficheroClavePrivada.toPath());
            //Creamos el objeto KeyFactory con el algoritmo RSA
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            //Creamos la clave privada con el objeto KeyFactory y la clave privada en formato PKCS8
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytesClavePrivada);
            //Generamos la clave privada
            clavePrivada = keyFactory.generatePrivate(privateKeySpec);
        } catch (IOException e) { //Si se produce un error durante la lectura saltará una excepción
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) { //Si no existe el algoritmo especificado saltará una excepción
            e.printStackTrace();
        } catch (InvalidKeySpecException e) { //Si la clave no es válida saltará una excepción
            e.printStackTrace();
        }
        //Devolvemos la clave privada
        return clavePrivada;
    }
}