package webserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class IteradorFicheroBinario implements Iterator<byte[]> {

    private FileInputStream lector;

    private int leidos = -1; //El número de bytes leidos por el método read

    private byte[] buf; //Los bytes leidos

    /**
     * Crea un nuevo objeto de tipo IteradorFicheroBinario para el fichero especificado.
     * @param f - String que contiene el nombre del fichero, con su ruta completa.
     * @throws FileNotFoundException - El fichero especificado no existe.
     */
    public IteradorFicheroBinario(String f) throws FileNotFoundException {
        //try {
            lector = new FileInputStream(f);
        /*} catch (FileNotFoundException e) {
            System.err.println("Error al abrir el fichero " + f + ": " + e.getMessage());
        }*/
        buf = new byte[2000];
        try {
            //Se lee el primer fragmento de 2000 bytes del fichero
            leidos = lector.read(buf);
        } catch (IOException e) {
            System.err.println("Error al leer el fichero " + f + ": " + e.getMessage());
        }
    }

    public boolean hasNext() {
        return leidos != -1;
    }

    public byte[] next() {
        try {
            //Asignamos al resultado los bytes leidos en la anterior llamada al método next()
            byte[] resultado = Arrays.copyOf(buf, buf.length);

            //Si no se llegó al final leemos los siguientes bytes
            if (leidos != -1) {
                leidos = lector.read(buf);
                //Si tras leer se llegó al final se cierra el fichero
                if (leidos == -1) lector.close();
            }

            return resultado;
        } catch(IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void remove() {
        throw new UnsupportedOperationException("Operación no permitida.");
    }


}
