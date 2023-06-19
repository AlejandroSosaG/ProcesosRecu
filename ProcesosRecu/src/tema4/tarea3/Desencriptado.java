package tema4.tarea3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAKey;
import javax.crypto.*;
public class Desencriptado {
    // Declaramos una constante con la ruta del fichero encriptado
    public static final String RUTA_FICHERO_ENCRIPTADO = "ProcesosRecu/src/tema4/tarea3/ficheroEncriptado.txt";
    public static final String RUTA_FICHERO_DESCIFRADO = "ProcesosRecu/src/tema4/tarea3/ficheroDescifrado.txt";
    public static void descifrarFichero() {
        try {
            //Declaramos las claves pública y privada
            PrivateKey clavePrivadaReceptor = ClavesReceptor.getClavePrivada();
            PublicKey clavePublicaEmisor = ClavesEmisor.getClavePublica();
            //Declaramos el cifrador con la clave privada del receptor
            Cipher cipherReceptor = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipherReceptor.init(Cipher.DECRYPT_MODE, clavePrivadaReceptor);
            //Leemos el fichero encriptado
            byte[] mensajeCifradoReceptor = leerFichero(RUTA_FICHERO_ENCRIPTADO);
            //Desciframos el mensaje primero con la clave privada del receptor
            byte[] mensajeCifradoEmisor = descifrarContenido(mensajeCifradoReceptor, clavePrivadaReceptor);
            //Luego desciframos con la clave pública del emisor
            byte[] mensajeDescifrado = descifrarContenido(mensajeCifradoEmisor, clavePublicaEmisor);
            //Guardamos el mensaje descifrado en un fichero
            guardarFichero(mensajeDescifrado, RUTA_FICHERO_DESCIFRADO);
            System.out.println("Mensaje descifrado correctamente");
            System.out.println();   //Salto de línea estético
            //Mostramos el mensaje descifrado por pantalla
            System.out.println("Este es el mensaje secreto: ");
            System.out.println(new String(mensajeDescifrado, StandardCharsets.UTF_8));
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Método que descifrará el contenido del fichero cifrado por bloques
     * @param contenidoCifrado Array de bytes que contiene los datos del fichero cifrado
     * @param clave Clave pública o privada
     * @return contenido descifrado en un array de bytes
     */
    public static byte[] descifrarContenido(byte[] contenidoCifrado, Key clave) throws Exception {
        // Crear objeto Cipher
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, clave);
        // Calcular tamaño del bloque
        int tamanoBloque = (((RSAKey) clave).getModulus().bitLength() + 7) / 8;
        // Inicializar buffer de salida
        ByteArrayOutputStream bufferSalida = new ByteArrayOutputStream();
        // Descifrar el contenido en bloques
        int offset = 0;
        while (offset < contenidoCifrado.length) {
            int tamanoBloqueActual = Math.min(tamanoBloque, contenidoCifrado.length - offset);
            byte[] bloqueDescifrado = cipher.doFinal(contenidoCifrado, offset, tamanoBloqueActual);
            bufferSalida.write(bloqueDescifrado);
            offset += tamanoBloqueActual;
        }
        return bufferSalida.toByteArray();
    }
    /**
     * Método que lee un fichero y lo devuelve como un array de bytes.
     * @param ruta Ruta del fichero a leer
     * @return Contenido del fichero en un array de bytes
     * @throws IOException Si ocurre un error de lectura del fichero
     */
    private static byte[] leerFichero(String ruta) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(ruta);
            return fis.readAllBytes();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }
    /**
     * Método que guarda un array de bytes en un fichero.
     * @param contenido Array de bytes que se va a guardar en el fichero
     * @param ruta Ruta del fichero en el que se va a guardar el contenido
     */
    private static void guardarFichero(byte[] contenido, String ruta) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(ruta);
            fos.write(contenido);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }
}