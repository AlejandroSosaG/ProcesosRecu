package tema4.tarea3;

import javax.crypto.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.*;
import java.util.*;

public class Cifrado {
    // Creamos dos cadenas estáticas que utilizaremos como claves.
    private static final String clavePublica = "ProcesosRecu/src/tema4/tarea3/clavePublica.key";
    private static final String clavePrivada = "ProcesosRecu/src/tema4/tarea3/clavePrivada.key";
    public static void main(String[] args) {
        // Guardamos la devolución de generarClaves en el objeto claves.
        KeyPair claves = generarClaves();
        // Llamamos al método guardarClaves pasándole claves por parámetro.
        guardarClaves(claves);
        // Pedimos al usuario un mensaje para encriptar y desencriptar.
        System.out.println("Introduzca el mensaje");
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        // Guardamos la devolución de cifrar en la variable msgCifrado.
        String msgCifrado = cifrar(msg);
        // Mostramos por pantalla el mensaje encriptado.
        System.out.println(msgCifrado);
        System.out.println();
        // Llamamos al método descifrar al que le pasamos msgCifrado como parámetro.
        descifrar(msgCifrado);
    }

    /**
     * Método que se encarga de cifrar el mensaje que le pasamos por parámetro.
     * @param msg
     * @return Devolvemos el mensaje cifrado.
     */
    public static String cifrar(String msg) {
        PublicKey clavePublica = getClavePublica();
        byte[] mensajeCifrado = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, clavePublica);
            byte[] mensaje = msg.getBytes(StandardCharsets.UTF_8);
            // Se cifra el mensaje.
            mensajeCifrado = cipher.doFinal(mensaje);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        // Devolvemos el mensaje cifrado.
        return new String(mensajeCifrado);
    }

    /**
     * Este método muestra por pantalla el mensaje que se pasa por parámetro una vez descodificado.
     * @param msg
     */
    public static void descifrar(String msg) {
        // Creamos una clave privada.
        PrivateKey clavePrivada = getClavePrivada();
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            // Desciframos con la clave privada
            cipher.init(Cipher.DECRYPT_MODE, clavePrivada);
            byte[] mensajeCifrado = msg.getBytes();
            // Se obtiene el mensaje descifrado
            byte[] mensaje = cipher.doFinal(mensajeCifrado);
            // Lo imprimimos por pantalla.
            System.out.println(new String(mensaje));
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
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
    public static void guardarClaves(KeyPair claves) {
        try {
            FileOutputStream fos = new FileOutputStream(clavePublica);
            fos.write(claves.getPublic().getEncoded());
            fos.flush();
            fos = new FileOutputStream(clavePrivada);
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
    public static PublicKey getClavePublica() {
        File ficheroClavePublica = new File(clavePublica);
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
    public static PrivateKey getClavePrivada() {
        File ficheroClavePrivada = new File(clavePrivada);
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
