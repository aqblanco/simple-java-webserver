package webserver;

import java.net.*;
import java.io.*;

public class HiloServidor  extends Thread {

    private Socket socket;

    /**
     * Crea un nuevo hilo para la conexión con el socket especificado.
     * @param s - El socket con el que se establece la conexión.
     */
    public HiloServidor(Socket s) {
        socket = s;
    }

    private LineaPeticion leerLPeticion(String linea) {
        LineaPeticion lPeticion = new LineaPeticion();
        StreamTokenizer st = new StreamTokenizer(new StringReader(linea));
        //Se configura el tokenizer
        st.resetSyntax();
        st.wordChars(33, 255);
        st.whitespaceChars(0, ' ');
        st.eolIsSignificant(true);

        int nPara = 0; //El número del actual parámetro
        try {
            while (st.nextToken() != st.TT_EOL) {
                switch(nPara) {
                    case 0: lPeticion.setMetodo(st.sval);
                    case 1: lPeticion.setUrl(st.sval);
                    case 2: lPeticion.setVersionHTTP(st.sval);
                }
                nPara++;
            }
        } catch (IOException e) {
            System.err.println("Error al procesar la Línea de Petición: " + e.getMessage());
        }
        return(lPeticion);
    }
    
    @Override
    public void run() {
        try {
            //Se establece el canal de entrada
            BufferedReader sEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Se establece el canal de salida
            OutputStream sSalida = socket.getOutputStream();
            //Se recibe el mensaje del cliente
            byte[] respuesta;
            String linea;
            LineaPeticion lPet = null;
            int nLinea = 0;
            while (!(linea = sEntrada.readLine()).equals("")) {
                //Si la línea actual es la línea de petición (la primera) se procesa
                if(nLinea == 0) lPet = leerLPeticion(linea + "\n");
                nLinea++;
            }
          
            try {
                Metodo metodo = lPet.getMetodo();
                String cabecera = lPet.getCabecera();
                //Se escribe la cabecera como bytes
                sSalida.write(cabecera.getBytes());
                while(metodo.hayLineas()) {
                    respuesta = metodo.leerFichero();
                    //Se envia la respuesta al cliente
                    sSalida.write(respuesta);
                }
            } catch (IllegalArgumentException e) {
                //El método requerido no es válido: se produce un error 400
                sSalida.write(lPet.getRespuestaError("400 Bad Request").getBytes());
            } catch (FileNotFoundException e) {
                //El fichero requerido no existe: se produce un error 404
                sSalida.write(lPet.getRespuestaError("404 Not Found").getBytes());
            }
            //Se cierran los flujos
            sSalida.close();
            sEntrada.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            //Se cierra el socket
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar el socket: " + e.getMessage());
            }
        }
    }

}