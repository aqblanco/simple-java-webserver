package webserver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorLineaLog {
    private String f;

    /**
     * Crea una nueva instancia del objeto EscritorLineaLog para escribir en el fichero especificado.
     * @param f - El fichero en el que se escribirá.
     */
    public EscritorLineaLog(String f) {
        this.f = f;
    }

    /**
     * Escribe en el fichero que se especificó. Si no existe éste o algún elemento de la ruta hasta el se crea.
     * @param ent - La EntradaLog a escribir en el fichero.
     * @throws IOException - Ocurre algún problema al intentar escribir.
     */
    public void escribir(EntradaLog ent) throws IOException {
        File fi = new File(f);
        BufferedWriter bw = null;
        if(!fi.exists()){
            //Se crea la ruta de directorios
            fi.getParentFile().mkdirs();
            //Se cera el fichero
            fi.createNewFile();
        }

        //Se inicializa el FileWriter con el parámetro de añadir a verdadero
        bw = new BufferedWriter(new FileWriter(f, true));
        bw.write(ent.toString());
        //Se escribe un salto de línea, se limpia el buffer del escritor y se cierra.
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
