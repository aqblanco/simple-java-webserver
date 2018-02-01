package webserver;

/**
 *
 * @author Hiro
 */
public class EntradaLogErrores extends EntradaLog {

    /**
     * Crea una nueva instancia del objeto EntradaLogErrores con la información que se guarda en una línea del log de errores
     * @param lPet - String que contiene la línea de petición hecha al servidor.
     * @param ip - String que contiene la dirección ip del cliente.
     * @param fechaHora - String que contiene la fecha y la hora en que se realizó la petición.
     * @param codEst - String que contiene el código de estado devuelto por el servidor.
     */
    public EntradaLogErrores(String lPet, String ip, String fechaHora, String codEst) {
        this.lPet = lPet;
        this.ip = ip;
        this.fechaHora = fechaHora;
        this.codEst = codEst;
    }
    
    @Override
    public String toString() {
        return(lPet + " - " + ip + " - " + fechaHora + " - " + codEst + "\n");
    }
}
