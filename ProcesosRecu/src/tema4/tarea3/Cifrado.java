package tema4.tarea3;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.*;
import java.util.*;

public class Cifrado {
    private static final String clavePublica = "ProcesosRecu/src/tema4/tarea3/clavePublica.key";
    private static final String clavePrivada = "ProcesosRecu/src/tema4/tarea3/clavePrivada.key";
    public static void main(String[] args) {
        KeyPair claves = generarClaves();
        guardarClaves(claves);
        System.out.println("Introduzca el mensaje");
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        String msgCifrado = cifrar(msg);
        System.out.println(msgCifrado);
        System.out.println();
        descifrar(msgCifrado);
    }
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
        }finally {
            // Devolvemos el mensaje cifrado.
            return new String(mensajeCifrado);
        }
    }
    public static void descifrar(String msg) {
        // Tomamos la clave privada
        PrivateKey clavePrivada = getClavePrivada();
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            // Desciframos con la clave privada
            cipher.init(Cipher.DECRYPT_MODE, clavePrivada);
            byte[] mensajeCifrado = msg.getBytes();
            // Se obtiene el mensaje descifrado
            byte[] mensaje = cipher.doFinal(Base64.getDecoder().decode(mensajeCifrado));
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
        }finally {
            return claves;
        }
    }
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
        }finally {
            return clavePublica;
        }
    }
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
