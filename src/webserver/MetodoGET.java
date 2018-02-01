package webserver;

import java.io.FileNotFoundException;

public class MetodoGET extends Metodo {
    
    /**
     * Crea una nueva instancia de la clase MetodoGET para tratar el fichero especificado.
     * @param f - el nombre del fichero con el que trabajar.
     * @throws FileNotFoundException - El fichero solicitado no existe el el servidor.
     */
    public MetodoGET(String f) throws FileNotFoundException {
        super(f);
    }

    /**
     * Comprueba si se llega al final del fichero.
     * @return Un boolean que nos indica si se llego al final del fichero o no.
     */
    public boolean hayLineas() {       
        return(it.hasNext());
    }

    /**
     * Lee un fragmento del fichero.
     * @return Los bytes leídos del fichero.
     */
    public byte[] leerFichero() {
        return(it.next());
    }
}