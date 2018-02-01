package webserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FormatoBinario extends Formato {

    private String fichero;
    private IteradorFicheroBinario it;

    public FormatoBinario(String f) throws FileNotFoundException {
        File dir = new File(".");
        try {
            this.fichero = dir.getCanonicalPath() + "/" + f;
        } catch (IOException ex) {
            //Logger.getLogger(FormatoHTML.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            it = new IteradorFicheroBinario(fichero);
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
