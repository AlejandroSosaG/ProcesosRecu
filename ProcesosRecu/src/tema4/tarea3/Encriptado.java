package tema4.tarea3;

import javax.crypto.*;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

public class Encriptado {
    public static void main(String[] args) {
        // Pedimos al usuario un mensaje para encriptar.
        System.out.println("Introduzca el mensaje que desea cifrar");
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        byte[] mensaje = msg.getBytes();
        // Guardamos la devolución de cifrar en la variable msgCifrado.
        byte[] msgCifrado = cifrar(mensaje);
        // Mostramos por pantalla el mensaje encriptado.
        System.out.println("Mensaje cifrado: " + Base64.getEncoder().encodeToString(msgCifrado));
    }

    /**
     * Método que se encarga de cifrar el mensaje que le pasamos por parámetro.
     * @param msg
     * @return Devolvemos el mensaje cifrado.
     */
    public static byte[] cifrar(byte[] msg) {
        byte[] mensajeCifrado = new byte[0];
        PublicKey clavePublica = Claves.getClavePublica(Claves.clavePublicaEncriptado);
        PrivateKey clavePrivada = Claves.getClavePrivada(Claves.clavePrivadaEncriptado);
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, clavePrivada);
            // Ciframos el mensaje.
            mensajeCifrado = cipher.doFinal(msg);
            cipher.init(Cipher.ENCRYPT_MODE, clavePrivada);
            Key clave = clavePublica;
            // Calculamos el tamaño de la clave.
            int tamanoClave = (((RSAPublicKey) clave).getModulus().bitLength() + 7) / 8 - 11;
            // Creamos un buffer de salida
            ByteArrayOutputStream bufferSalida = new ByteArrayOutputStream();
            // Recorremos el bucle minetras i sea menor al tamaño del mensaje cifrado.
            for (int i = 0; i < mensajeCifrado.length; ) {
                // Calculamos el tamaño actual de la clave.
                int tam = Math.min(tamanoClave, mensajeCifrado.length - i);
                // Volvemos a cifrar el mensaje con el tamaño actual.
                byte[] msgCifrado = cipher.doFinal(mensajeCifrado, i, tam);
                bufferSalida.write(msgCifrado);
                // Le sumamos a la variable i el valor del tamaño actual de la clave.
                i += tam;
            }
            // Pasamos el mensaje cifrado actual a la variable mensajeCifrado.
            mensajeCifrado = bufferSalida.toByteArray();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Devolvemos el mensaje cifrado.
        return mensajeCifrado;
    }
}
