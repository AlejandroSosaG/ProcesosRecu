package tema4.tarea3;

import javax.crypto.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.*;
import java.util.*;

public class Claves {
    // Creamos dos cadenas estáticas que utilizaremos como claves.
    public static final String clavePublicaEncriptado = "ProcesosRecu/src/tema4/tarea3/clavePublicaEncriptado.key";
    public static final String clavePublicadesencriptado = "ProcesosRecu/src/tema4/tarea3/clavePublicaDesencriptado.key";
    public static final String clavePrivadaEncriptado = "ProcesosRecu/src/tema4/tarea3/clavePrivadaEncriptado.key";
    public static final String clavePrivadadesencriptado = "ProcesosRecu/src/tema4/tarea3/clavePrivadaDesencriptado.key";
    public static void main(String[] args) {
        // Guardamos la devolución de generarClaves en el objeto claves.
        KeyPair claves = generarClaves();
        // Llamamos al método guardarClaves pasándole claves por parámetro.
        guardarClavesEncriptado(claves);
        claves = generarClaves();
        guardarClavesDesencriptado(claves);
    }
    /**
     * Método con el que generamos un par de claves a partir de un algoritmo.
     * @return Devolvemos el par de claves generado.
     */
    public static KeyPair generarClaves() {
        KeyPairGenerator generador;
        KeyPair claves = null;
        try {
            generador = KeyPairGenerator.getInstance("RSA");
            generador.initialize(2048);
            claves = generador.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No existe el algoritmo especificado");
            e.printStackTrace();
        }
        return claves;
    }
    /**
     * Método para escribir la clave pública del par de claves pasado por parámetro en el fichero de clave pública
     * y la clave privada del par de claves en el fichero de clave privada.
     * @param claves
     */
    public static void guardarClavesEncriptado(KeyPair claves) {
        try {
            FileOutputStream fos = new FileOutputStream(clavePublicaEncriptado);
            fos.write(claves.getPublic().getEncoded());
            fos.flush();
            fos = new FileOutputStream(clavePrivadaEncriptado);
            fos.write(claves.getPrivate().getEncoded());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Método para escribir la clave pública del par de claves pasado por parámetro en el fichero de clave pública
     * y la clave privada del par de claves en el fichero de clave privada.
     * @param claves
     */
    public static void guardarClavesDesencriptado(KeyPair claves) {
        try {
            FileOutputStream fos = new FileOutputStream(clavePublicadesencriptado);
            fos.write(claves.getPublic().getEncoded());
            fos.flush();
            fos = new FileOutputStream(clavePrivadadesencriptado);
            fos.write(claves.getPrivate().getEncoded());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método se encarga de generar una clave pública a partir de un fichero.
     * @return Se devuelve la clave pública creada.
     */
    public static PublicKey getClavePublica(String clave) {
        File ficheroClavePublica = new File(clave);
        PublicKey clavePublica = null;
        try {
            byte[] bytesPublica = Files.readAllBytes(ficheroClavePublica.toPath());
            KeyFactory kf = KeyFactory.getInstance("RSA");
            EncodedKeySpec eks = new X509EncodedKeySpec(bytesPublica);
            clavePublica = kf.generatePublic(eks);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return clavePublica;
    }

    /**
     * Método que genera una clave privada utilizando un fichero.
     * @return Devolvemos la clave privada generada.
     */
    public static PrivateKey getClavePrivada(String clave) {
        File ficheroClavePrivada = new File(clave);
        PrivateKey clavePrivada = null;
        try {
            byte[] bytesPrivada = Files.readAllBytes(ficheroClavePrivada.toPath());
            KeyFactory kf = KeyFactory.getInstance("RSA");
            EncodedKeySpec eks = new PKCS8EncodedKeySpec(bytesPrivada);
            clavePrivada = kf.generatePrivate(eks);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return clavePrivada;
    }
}
