package tema4.tarea3;

import java.io.*;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.*;
public class ClavesReceptor {
    //Declaramos las constantes con los nombres de los ficheros donde se guardan las claves
    private static final String FICHEROCLAVEPUBLICA = "ProcesosRecu/src/tema4/tarea3/clavePublicaReceptor.key";
    private static final String FICHEROCLAVEPRIVADA = "ProcesosRecu/src/tema4/tarea3/clavePublicaReceptor.key";
    /**
     * Método que genera las claves con el algoritmo RSA
     * @return cCaves generadas
     */
    public static KeyPair generarClavesReceptor() {
        //Declaramos las variables
        KeyPairGenerator generador;
        KeyPair claves = null;
        try {
            //Creamos el generador de claves con el algoritmo RSA
            generador = KeyPairGenerator.getInstance("RSA");
            //Inicializamos el generador de claves con un tamaño de 4096 porque es el doble de 2048
            generador.initialize(4096);
            //Generamos las claves
            claves = generador.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {  //Si no existe el algoritmo especificado saltará una excepción
            e.printStackTrace();
        }
        return claves;
    }
    /**
     * Método que guarda las claves en ficheros
     * @param claves KeyPair con las claves a guardar
     */
    public static void guardarClaves(KeyPair claves) {
        //Declaramos la variable para escribir en el fichero
        FileOutputStream fos;
        try {
            //Creamos el flujo de salida para el fichero de la clave pública
            fos = new FileOutputStream(FICHEROCLAVEPUBLICA);
            //Escribimos la clave pública en el fichero
            fos.write(claves.getPublic().getEncoded());
            //Creamos el flujo de salida para el fichero de la clave privada
            fos = new FileOutputStream(FICHEROCLAVEPRIVADA);
            //Escribimos la clave privada en el fichero
            fos.write(claves.getPrivate().getEncoded());
            //Cerramos el flujo de salida
            fos.close();
        } catch (FileNotFoundException e) { //Si no se encuentra el fichero saltará una excepción
            e.printStackTrace();
        } catch (IOException e) { //Si se produce un error durante la escritura en el fichero saltará una excepción
            e.printStackTrace();
        }
    }
    /**
     * Método que lee la clave pública del fichero
     * @return Clave pública
     */
    public static PublicKey getClavePublica() {
        //Declaramos laa variables para leer el fichero
        File ficheroClavePublica = new File(FICHEROCLAVEPUBLICA);
        PublicKey clavePublica = null;
        try {
            //Leemos el fichero de la clave pública
            byte[] bytesClavePublica = Files.readAllBytes(ficheroClavePublica.toPath());
            //Creamos el objeto para generar claves con el algoritmo RSA
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            //Creamos la especificación de la clave pública
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytesClavePublica);
            //Generamos la clave pública
            clavePublica = keyFactory.generatePublic(publicKeySpec);
        } catch (IOException e) { //Si se produce un error durante la lectura del fichero saltará una excepción
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) { //Si no existe el algoritmo especificado saltará una excepción
            e.printStackTrace();
        } catch (InvalidKeySpecException e) { //Si la clave no es válida saltará una excepción
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
        //Declaramos laa variables para leer el fichero
        File ficheroClavePrivada = new File(FICHEROCLAVEPRIVADA);
        PrivateKey clavePrivada = null;
        try {
            //Leemos el fichero de la clave privada
            byte[] bytesClavePrivada = Files.readAllBytes(ficheroClavePrivada.toPath());
            //Creamos el objeto para generar claves con el algoritmo RSA
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            //Creamos la especificación de la clave privada
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytesClavePrivada);
            //Generamos la clave privada
            clavePrivada = keyFactory.generatePrivate(privateKeySpec);
        } catch (IOException e) { //Si se produce un error durante la lectura del fichero saltará una excepción
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