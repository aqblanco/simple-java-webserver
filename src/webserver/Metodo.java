package webserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Metodo {

    /**
     * El fichero que se requiere.
     */
    protected String f;

    /**
     * El iterador del fichero.
     */
    protected IteradorFicheroBinario it;

    private String dirServ; //El directorio en el que se ejecuta el Servidor

    /**
     * Crea una nueva instancia de la clase Metodo para tratar el fichero especificado.
     * @param f - el fichero con el que trabajar.
     * @throws FileNotFoundException
     */
    public Metodo(String f) throws FileNotFoundException {
        this.f = f;
        //Se obtiene el directorio sobre el que se ejecuta el Servidor
        File dir = new File(".");
        try {
            dirServ = dir.getCanonicalPath();
        } catch (IOException e) {}
        it = new IteradorFicheroBinario(dirServ + "/" + f);
    }

    /**
     * Comprueba si se llega al final del fichero.
     * @return Un boolean que nos indica si se llego al final del fichero o no.
     */
    public abstract boolean hayLineas();
    /**
     * Lee un fragmento del fichero.
     * @return Los bytes leídos del fichero.
     */
    public abstract byte[] leerFichero();
    
}
