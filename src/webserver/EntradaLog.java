package webserver;

public abstract class EntradaLog {
    /**
     * La línea de la petición realizada al servidor.
     */
    protected String lPet;
    /**
     * La ip del cliente.
     */
    protected String ip;
    /**
     * La fecha y la hora de la petición.
     */
    protected String fechaHora;
    /**
     * El código de estado devuelto por el servidor.
     */
    protected String codEst;

    /**
     * Establece el valor del campo que contiene la línea de petición.
     * @param lPet
     */
    public void setLPet(String lPet) {
        this.lPet = lPet;
    }

    /**
     * Establece el valor del campo que contiene la ip del cliente.
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Establece el valor del campo que contiene la fecha y la hora en que se realizó la petición.
     * @param fechaHora
     */
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Establece el valor del campo que contiene el código de estado devuelto por el servidor.
     * @param codEst
     */
    public void setCodEst(String codEst) {
        this.codEst = codEst;
    }

    @Override
    public abstract String toString();
}
