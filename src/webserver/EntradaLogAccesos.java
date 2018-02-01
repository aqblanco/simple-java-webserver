package webserver;

public class EntradaLogAccesos extends EntradaLog {

    private String tam;

    /**
     * Crea una nueva instancia del objeto EntradaLogAccesos con la información que se guarda en una línea del log de accesos
     * @param lPet - String que contiene la línea de petición hecha al servidor.
     * @param ip - String que contiene la dirección ip del cliente.
     * @param fechaHora - String que contiene la fecha y la hora en que se realizó la petición.
     * @param codEst - String que contiene el código de estado devuelto por el servidor.
     * @param tam - String que contiene el tamaño en bytes del documento devuelto al cliente.
     */
    public EntradaLogAccesos(String lPet, String ip, String fechaHora, String codEst, String tam) {
        this.lPet = lPet;
        this.ip = ip;
        this.fechaHora = fechaHora;
        this.codEst = codEst;
        this.tam = tam;
    }
    
    /**
     * Establece el valor del campo que contiene el tamaño del fichero que se le leyó.
     * @param tam - El nuevo tamaño.
     */
    public void setTam(String tam) {
        this.tam = tam;
    }

    @Override
    public String toString() {
        return(lPet + " - " + ip + " - " + fechaHora + " - " + codEst + " - " + tam + " bytes\n");
    }
}
