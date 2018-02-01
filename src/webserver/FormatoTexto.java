package webserver;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormatoTexto extends Formato {

    private String fichero;
    private IteradorFicheroTexto it;

    public FormatoTexto(String f) throws FileNotFoundException {
        File dir = new File(".");
        try {
            this.fichero = dir.getCanonicalPath() + "/" + f;
        } catch (IOException ex) {
            //Logger.getLogger(FormatoHTML.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            it = new IteradorFicheroTexto(fichero);
        } catch (IOException ex) {
            //Logger.getLogger(FormatoHTML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String leerLinea() {
        return it.next();
    }

    @Override
    public boolean hayLineas() {
        return it.hasNext();
    }

}