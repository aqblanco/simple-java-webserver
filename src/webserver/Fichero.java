package webserver;

import java.io.File;
import java.io.IOException;

public class Fichero {
    
    private String f;
    
    /**
     * Crea una nueva instancia de la clase Fichero para obtener información acerca de un fichero.
     * @param f
     */
    public Fichero(String f) {
        this.f = f;
    }
    
    /**
     * Obtiene la extensión del fichero.
     * @return Un String con la extensión del fichero.
     */
    public String getExtension() {
        int punto = f.lastIndexOf(".");
        return f.substring(punto + 1);
    }
    
    /**
     * Obtiene el tamaño del fichero.
     * @return Un long con el tamaño en bytes del fichero.
     */
    public long getTamanho() {
        String rutaF = null;
        
        File dir = new File(".");
        try {
            rutaF = dir.getCanonicalPath() + "/" + f;
        } catch (IOException e) {}
        
        return new File(rutaF).length();
    }

    /**
     * Obtiene el tipo MIME del fichero.
     * @return Un String con el tipo MIME del fichero.
     */
    public String getTipoMIME() {
        //Se obtiene la extensión del fichero y se pasa a minúsculas
        String ext = this.getExtension().toLowerCase();
        String tipo = "";

        if(ext.equals("html")) tipo = "text/html";
        else if(ext.equals("txt")) tipo = "text/plain";
        else if(ext.equals("jpg")) tipo = "image/jpeg";
        else if(ext.equals("gif")) tipo = "image/gif";
        else tipo = "application/octet-stream";
        return(tipo);
    }
}
