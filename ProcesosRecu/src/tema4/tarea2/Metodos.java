package tema4.tarea2;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.*;
public class Metodos {
    /**
     * Genera la clave de cifrado y descifrado a partir de la contraseña del usuario.
     */
    public static Key obtenerClave(String contraseña){
        Key clave = new SecretKeySpec(contraseña.getBytes(), 0, 3, "AES");
        return clave;
    }
    /**
     * Método que cifrará un mensaje introducido por el usuario. Para ello se recibe como parámetro un objeto
     * de tipo Key con la contraseña. Una vez cifrado se escribe en un fichero.
     * @param clave
     */
    public static String cifrar(Key clave, String texto){
        Scanner sc = new Scanner(System.in);
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // Iniciar el cifrado con la clave
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            //  Llevar a cabo el cifrado
            byte[] cipherText = cipher.doFinal(texto.getBytes());
            // Se escribe en el fichero
            String cifrado = Base64.getEncoder().encodeToString(cipherText);
            sc.close();
            return cifrado;
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
    }
    /**
     * Método para descifrar, como el método cifrar se recibe un objeto Key con la clave. Luego se guarda en una
     * variable el contenido del fichero.
     * @param clave
     */
    public static String descifrar(Key clave, String texto){
        // 2 - Crear un Cipher
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // Iniciar el descifrado con la clave
            cipher.init(Cipher.DECRYPT_MODE, clave);
            // Llevar a cabo el descifrado
            byte[] textoDescifrado = cipher.doFinal(Base64.getDecoder().decode(texto));
            // Imprimimos el mensaje descifrado:
            String descifrado = new String(textoDescifrado);
            return descifrado;
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}