package webserver;

import java.io.FileNotFoundException;

public class MetodoHEAD extends Metodo {

    /**
     * Crea una nueva instancia de la clase MetodoHEAD para tratar el fichero especificado.
     * @param f - el fichero con el que trabajar.
     * @throws FileNotFoundException - El fichero solicitado no existe el el servidor.
     */
    public MetodoHEAD(String f) throws FileNotFoundException {
        super(f);
    }

    /**
     * Comprueba si llegamos al final del fichero.
     * @return Un boolean que nos indica si se llego al final del fichero o no.
     */
    @Override
    public boolean hayLineas() {
        //Devolvemos false, ya que en el método HEAD no se lee el contenido del fichero
        return(false);
    }

    /**
     * Lee un fragmento del fichero.
     * @return Los bytes leídos del fichero.
     */
    @Override
    public byte[] leerFichero() {
        return(null);
    }
}
