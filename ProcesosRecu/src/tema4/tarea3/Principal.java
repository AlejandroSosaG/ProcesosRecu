package tema4.tarea3;

import java.security.KeyPair;
public class Principal {
    public static void main(String[] args) {
        //Creamos las variables.
        KeyPair clavesEmisor = ClavesEmisor.generarClavesEmisor();
        KeyPair clavesReceptor = ClavesReceptor.generarClavesReceptor();
        // Guardamos las claves en un fichero.
        ClavesEmisor.guardarClaves(clavesEmisor);
        ClavesReceptor.guardarClaves(clavesReceptor);
        // Ciframos el fichero.
        Encriptado.encriptarFichero();
        // Desciframos el fichero.
        Desencriptado.descifrarFichero();
    }
}