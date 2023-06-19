package tema4.tarea2;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.*;
public class Metodos {
    /**
     * Generamos la clave de cifrado y descifrado a partir de la contraseña que se le pasa por parámetro.
     * @return Devolvemos la clave.
     */
    public static Key obtenerClave(String contraseña){
        Key clave = new SecretKeySpec(contraseña.getBytes(), 0, 16, "AES");
        return clave;
    }
    /**
     * Método que cifra un mensaje introducido pasado por parámetro usando una clave tambien pasada como parámetro
     * @param clave
     * @param texto
     * @return Se devuelve el texto pasado por parámetro cifrado.
     */
    public static String cifrar(Key clave, String texto){
        String cifrado = "";
        // Creación de escaner.
        Scanner sc = new Scanner(System.in);
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // Iniciamos el cifrado con la clave.
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            // Ciframos el texto pasado por parámetro.
            byte[] cipherText = cipher.doFinal(texto.getBytes());
            // Pasamos el texto cifrado a tipo cadena.
            cifrado = Base64.getEncoder().encodeToString(cipherText);
            // Cerramos el escaner.
            sc.close();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        // Devolvemos el texto cifrado.
        return cifrado;
    }
    /**
     * Método que se encarga de descifrar la cadena pasada por parámetro usando la clave también pasada.
     * @param clave
     * @param texto
     * @return Devolvemos el texto cifrado pasado por parámetro una vez descifrado.
     */
    public static String descifrar(Key clave, String texto){
        String descifrado = "";
        // Creación de un objeto tipo Cipher.
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // Iniciamos el descifrado con la clave pasada por parámetro.
            cipher.init(Cipher.DECRYPT_MODE, clave);
            // Desciframos el texto.
            byte[] textoDescifrado = cipher.doFinal(Base64.getDecoder().decode(texto));
            // Pasamos el mensaje descifrado a tipo cadena.
            descifrado = new String(textoDescifrado);
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
        // Devolvemos el texto descifrado.
        return descifrado;
    }
}