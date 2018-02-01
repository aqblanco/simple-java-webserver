package webserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

class IteradorFicheroTexto implements Iterator<String> {

    private BufferedReader lector;

    private String linea;

    public IteradorFicheroTexto(String f) throws FileNotFoundException {

        lector = new BufferedReader(new FileReader(f));
        try {
            linea = lector.readLine();
        } catch (IOException ex) {
            //Logger.getLogger(IteradorFicheroTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean hasNext() {
        return linea != null;
    }

    public String next() {
        try {
            String resultado = linea;

            if (linea != null) {
                linea = lector.readLine();
                if (linea == null)
                    lector.close();
            }

            return resultado;
        } catch(IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

}
