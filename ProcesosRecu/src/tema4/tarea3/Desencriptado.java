package tema4.tarea3;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;

public class Desencriptado {
    public static void main(String[] args) {
        String msgCifrado = "USXrrCMd1oltVsVTldO/KC5RbSHxU4NIfN3Fk0rlE5WeB50BrS26UUw6LZ9f2J9UZ7Mh4YTPqvL3l74mrouq6LIlERupN9NMtUfOv2OqJOV/WS0/XC6p5Pvoio+DT80Af2PNQ2OVoU3rOu8j2ik0exhoIrx4ETHzo9cU/AK+d5NajPLGCeEZQfyktuDCGrmjoSRTtU8WdYZDuECT9oFYIVbsWLWfilo2Um01rQJBJ501NxLII2492BkNfLywkkkTCRpa0Fab9+u3UNJVxCXFeUwPdr22OP1tCxCt/uXiqo7zc7Px2TCypA27XmUcybc6BSGCrYfLLVe9VFNnFj+r5pxJnDXk/0yoji4MzyVC6bvYYnKP9GHZ/EqND1UyJ8SxjS8t/w2T+mhnX+6t+m9skfjpBkOtHw3o0/L7YzEApj9BOgL+rghzEGYoQvcRxuZnHZCw524YKSGNSSG32GO02qbADTAvO0BVMRW4fAJAc73c4oX2zRnlY4r+IZWutyfvpgk7gC0H15nWBYPfLdvUfFWLDO5ndFtKLjJiF7r178OUQuaY8UK0lB/xW95fzLYGvIzmrSGngO9BRxQLbPaKGwVL4/ADWj+u57ynWabVQ5F1XRBqJ+XY6FNgPuTTwS3qLSkOCW5JrLtnWEppLskDxJtFeNzvyN5WIthQkOShNog=";
        // Llamamos al método descifrar al que le pasamos msgCifrado como parámetro.
        descifrar(msgCifrado);
    }
    /**
     * Este método muestra por pantalla el mensaje que se pasa por parámetro una vez descodificado.
     * @param msg
     */
    public static void descifrar(String msg) {
        // Creamos una clave privada que sacamos de la clase Claves.
        PrivateKey clavePrivada = Claves.getClavePrivada(Claves.clavePrivadadesencriptado);
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            // Desciframos con la clave privada.
            cipher.init(Cipher.DECRYPT_MODE, clavePrivada);
            byte[] mensajeCifrado = Base64.getDecoder().decode(msg);
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
}
