package tema4.tarea3;

import javax.crypto.*;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAKey;
public class Encriptado {
    //declaramos una constante con la ruta del fichero a encriptar
    public static final String RUTA_FICHERO = "ProcesosRecu/src/tema4/tarea3/fichero.txt";
    public static final String RUTA_FICHERO_ENCRIPTADO = "ProcesosRecu/src/tema4/tarea3/ficheroEncriptado.txt";
    public static void encriptarFichero(){
        //Declaramos los arrays de bytes que vamos a utilizar
        byte [] mensajeCifradoEmisor;  //array de bytes que contendrá el mensaje cifrado por la clave privada del emisor
        byte [] mensajeCifradoReceptor;  //array de bytes que contendrá el mensaje cifrado por la clave pública del receptor
        byte[] mensajeOriginal;         //array de bytes que contendrá el mensaje leido en el fichero que se quiere encriptar
        try {
            //Declaramos las claves pública y privada
            PrivateKey clavePrivadaEmisor = ClavesEmisor.getClavePrivada();
            PublicKey clavePublicaReceptor = ClavesReceptor.getClavePublica();
            //Declaramos el cifrador con la clave privada del receptor
            Cipher cipherEmisor = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipherEmisor.init(Cipher.ENCRYPT_MODE, clavePrivadaEmisor);
            //Leemos el fichero encriptado
            mensajeOriginal = leerFichero().readAllBytes();
            //Encriptamos el fichero primero con la clave privada del emisor
            mensajeCifradoEmisor = cifrarContenido(mensajeOriginal, clavePrivadaEmisor);
            //Encriptamos el fichero con la clave pública del receptor
            mensajeCifradoReceptor = cifrarContenido(mensajeCifradoEmisor, clavePublicaReceptor);
            //Guardamos el mensaje cifrado en un fichero
            guardarFichero(mensajeCifradoReceptor);
            System.out.println("Mensaje cifrado correctamente");
        } catch (NoSuchPaddingException e) { //Si el padding no es válido saltará una excepción
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) { //Si el algoritmo no es válido saltará una excepción
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) { //Si el tamaño del bloque no es válido saltará una excepción
            e.printStackTrace();
        } catch (BadPaddingException e) { //Si el padding no es válido saltará una excepción
            e.printStackTrace();
        } catch (InvalidKeyException e) { //Si la clave no es válida saltará una excepción
            e.printStackTrace();
        } catch (IOException e) { //Si hay un error de lectura del fichero saltará una excepción
            e.printStackTrace();
        }catch (Exception e) {  //Si hay un error al encriptar el fichero saltará una excepción
            e.printStackTrace();
        }
    }
    public static byte[] cifrarContenido(byte[] contenido, Key clave) throws Exception {
        // Crear objeto Cipher
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        // Calcular tamaño del bloque
        int tamanoBloque = (((RSAKey)clave).getModulus().bitLength() + 7) / 8 - 11;
        // Inicializar buffer de salida
        ByteArrayOutputStream bufferSalida = new ByteArrayOutputStream();
        // Cifrar el contenido en bloques
        int offset = 0;
        while (offset < contenido.length) {
            int tamanoBloqueActual = Math.min(tamanoBloque, contenido.length - offset);
            byte[] bloqueCifrado = cipher.doFinal(contenido, offset, tamanoBloqueActual);
            bufferSalida.write(bloqueCifrado);
            offset += tamanoBloqueActual;
        }
        // Devolver contenido cifrado completo
        return bufferSalida.toByteArray();
    }
    /**
     * Método que lee un fichero y lo devuelve como un FileInputStream
     * @return Datos del fichero en un FileInputStream
     */
    private static FileInputStream leerFichero (){
        //Declaramos un FileInputStream para leer el fichero
        FileInputStream fis = null;
        try {
            //Inicializamos el FileInputStream con la ruta del fichero
            fis = new FileInputStream(RUTA_FICHERO);
        } catch (FileNotFoundException e) { //Si el fichero no existe saltará una excepción
            System.err.println("Fichero no encontrado");
            e.printStackTrace();
        }
        //Devolvemos el FileInputStream
        return fis;
    }
    /**
     * Método que guarda un array de bytes en un fichero
     * @param mensajeCifrado Array de bytes que se va a guardar en el fichero
     */
    private static void guardarFichero (byte [] mensajeCifrado){
        try {
            //Inicializamos un FileOutputStream con la ruta del fichero
            FileOutputStream fos = new FileOutputStream(RUTA_FICHERO_ENCRIPTADO);
            //Escribimos el array de bytes en el fichero
            fos.write(mensajeCifrado);
            //Cerramos el FileOutputStream
            fos.close();
        } catch (IOException e) { //Si hay un error de escritura del fichero saltará una excepción
            System.err.println("Error de escritura del fichero");
            e.printStackTrace();
        }
    }
}