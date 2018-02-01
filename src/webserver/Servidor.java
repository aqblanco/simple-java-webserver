package webserver;

import java.net.*;
import java.io.*;

public class Servidor {

    private int puerto;

    /**
     * Crea una nueva instancia del objeto Servidor en el puerto especificado.
     * @param puerto - Un int que determina el puerto en el que se correrá el servidor.
     */
    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    /**
     * Inicia la ejecución del servidor, dejándolo a la espera de recibir peticiones.
     */
    public void arrancar() {
        ServerSocket sSocket = null;
        try {
            //Se crea el socket del servidor
            sSocket = new ServerSocket(this.puerto);

            while (true) {
                //Se espera a posibles conexiones
                Socket socket = null;
                socket = sSocket.accept();
                //Se crea un objeto HiloServidor, pasándole la nueva conexión
                HiloServidor hServ = new HiloServidor(socket);
                //Se inicia la ejecución con el método start()
                hServ.start();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            //Se cierra el socket del servidor
            try {
                sSocket.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar el socket: " + e.getMessage());
            }
        }
    }
}