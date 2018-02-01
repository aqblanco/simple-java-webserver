package webserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LineaPeticion {
    private String metodo;
    private String url;
    private String versionHTTP;

    /**
     * Crea una nueva instancia de la clase LineaPeticion vacía.
     */
    public LineaPeticion() {
        this.metodo = "";
        this.url = "";
        this.versionHTTP = "";
    }

    /**
     * Crea una nueva instancia de la clase LineaPeticion tomando los valores especificados.
     * @param metodo - String que indica el método empleado en la petición.
     * @param url - String que indica la url del documento solicitado.
     * @param vHTTP - String que indica la versión del protocolo HTTP que se usará en la petición.
     */
    public LineaPeticion(String metodo, String url, String vHTTP) {
        this.metodo = metodo;
        this.url = url;
        this.versionHTTP = vHTTP;
    }

    /**
     * Establece el valor del campo metodo al especificado.
     * @param metodo - String que indica el nuevo calor del campo metodo.
     */
    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    /**
     * Establece el valor del campo url al especificado.
     * @param url - String que indica el nuevo calor del campo url.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Establece el valor del campo vHTTP al especificado.
     * @param vHTTP - String que indica el nuevo calor del campo vHTTP.
     */
    public void setVersionHTTP(String vHTTP) {
        this.versionHTTP = vHTTP;
    }

    /**
     * Obtiene el método que se usa en la línea de petición actual.
     * @return Un objeto del tipo del método de la linea (MetodoGET si el método de la línea es GET, etc).
     * @throws IllegalArgumentException - El método introducido en la línea no tiene una clase asociada (no existe).
     * @throws FileNotFoundException - El fichero solicitado no existe el el servidor.
     */
    public Metodo getMetodo() throws IllegalArgumentException, FileNotFoundException {
        Metodo m = null;
        String s = "webserver.Metodo" + metodo;
        try {
            //Obtiene la clase que lleva por nombre el contenido de s
            Class c = Class.forName(s);
            //Crea un objeto de la clase c (hija de Metodo)
            m  = (Metodo) c.getConstructor(String.class).newInstance(this.url);
        } catch (ClassNotFoundException e) {
            //El método no es válido
            throw(new IllegalArgumentException());
        } catch (Exception e) {
            //El fichero no existe
            throw(new FileNotFoundException(url));
        }
        return(m);
    }

    /**
     * Obtiene la url que se solicita en la línea actual.
     * @return Un String con la url solicitada.
     */
    public String getURL() {
        return(this.url);
    }

    /**
     * Obtiene la línea de estado y las de cabecera a partir del fichero requerido.
     * @return La línea de estado junto con las de cabecera.
     */
    public String getCabecera() {
        //Se obtiene el directorio raíz del Servidor
        String dirServ="";
        File dir = new File(".");
        try {
            dirServ = dir.getCanonicalPath();
        } catch (IOException e) {}
        File fi = new File(dirServ + "/" + url);       

        String resp = "";
        String codEst = "HTTP/1.0 200 OK";
        resp += codEst + "\n";
        
        //Se obtiene la fecha
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss zzz");
        resp += "Date: " + sdf.format(cal.getTime()) + "\n";

        //Se obtiene la longitud del fichero
        Fichero fich = new Fichero(url);
        resp += "Content-Length: " + fich.getTamanho() + "\n";

        //Se obtiene el tipo MIME
        resp += "Content-Type: " + fich.getTipoMIME() + "\n\n";

        //Se forma de nuevo la petición para guardarla en el log
        String lPet = metodo + " " + url + " " + versionHTTP;
        //Se formatea la fecha para guardarla en la entrada de log
        SimpleDateFormat sdfLog = new SimpleDateFormat("d/MMM/yyyy HH:mm:ss zzz");
        String fechaHora = sdfLog.format(cal.getTime());
        //Se obtiene la ip del cliente para guardar en el log
        String ip = obtenerIPCliente();
        //Se obtiene el tamaño del fichero mostrado para guardarlo en el log
        String tam = String.valueOf(fich.getTamanho());

        //Se inicializa la entrada de log y se escribe en el fichero
        EntradaLogAccesos eLogAcc = obtenerEntradaLog(lPet, ip, fechaHora, codEst, tam);
        escribirEntradaLog(eLogAcc);

        return(resp);
    }

    /**
     * Obtiene la respuesta HTTP ante un error y el código HTML con el mensaje de error que se produjo (simulando ser un documento html).
     * @param mError - String que contiene el mensaje de error que se mostrará.
     * @return Un String con la respuesta HTTP y el código HTML de la página de error.
     */
    public String getRespuestaError(String mError) {

        String lPet = metodo + " " + url + " " + versionHTTP;

        String resp ="";
        resp = "HTTP/1.0 ";
        resp += mError;

        String codEst = resp;

        resp += "\n";

        //Se obtiene la fecha
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss zzz");
        resp += "Date: " + sdf.format(cal.getTime()) + "\n";

        //Se fija el tipo MIME en text/html
        resp += "Content-Type: text/html\n";

        //Se obtiene el documento de error
        String cont = generarDocumentoError(mError);

        //Se obtiene la longitud del documento de error
        resp += "Content-Length: ";
        //Se obtiene la longitud en bytes del documento de error
        long t = cont.getBytes().length;
        resp += t + "\n\n";

        //Se formatea la fecha para guardarla en la entrada de log
        SimpleDateFormat sdfLog = new SimpleDateFormat("d/MMM/yyyy HH:mm:ss zzz");
        String fechaHora = sdfLog.format(cal.getTime());
        //Se obtiene la ip del cliente para guardar en el log
        String ip = obtenerIPCliente();

         //Se inicializa la entrada de log y se escribe en el fichero
        EntradaLogErrores eLogErr = obtenerEntradaLog(lPet, ip, fechaHora, codEst);
        escribirEntradaLog(eLogErr);

        return(resp + cont);
    }

    private String generarDocumentoError(String mError) {
        String m = "<html>\n";
        m += "\t<body>\n";
        m += "\t\t<h1>" + mError + "</h1>\n";
        m += "\t</body>\n";
        m += "</html>\n";
        return m;
    }

    private String obtenerIPCliente() {
        String dire = "";
        try {
            dire = java.net.InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            dire = "Desconocida";
        }
        return(dire);
    }

    private EntradaLogAccesos obtenerEntradaLog(String lPet, String ip, String fechaHora, String codEst, String tam) {
        return(new EntradaLogAccesos(lPet, ip, fechaHora, codEst, tam));
    }

    private EntradaLogErrores obtenerEntradaLog(String lPet, String ip, String fechaHora, String codEst) {
        return(new EntradaLogErrores(lPet, ip, fechaHora, codEst));
    }

    private void escribirEntradaLog(EntradaLogAccesos ent) {
        String dirServ="";
        File dir = new File(".");
        try {
            dirServ = dir.getCanonicalPath();
        } catch (IOException e) {}

        EscritorLineaLog escritor = new EscritorLineaLog(dirServ + "/logs/logAccesos.log");
        try {
            escritor.escribir(ent);
        } catch (IOException e) {
            System.err.println("Error al escrbir en el log de Accesos: " + e.getMessage());
        }
    }

    private void escribirEntradaLog(EntradaLogErrores ent) {
        String dirServ="";
        File dir = new File(".");
        try {
            dirServ = dir.getCanonicalPath();
        } catch (IOException e) {}
        EscritorLineaLog escritor = new EscritorLineaLog(dirServ + "/logs/logErrores.log");
        try {
            escritor.escribir(ent);
        } catch (IOException e) {
            System.err.println("Error al escrbir en el log de Errores: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return(metodo + " " + url + " " + versionHTTP);
    }
}
