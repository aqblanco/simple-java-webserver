package webserver;

public class Main {

    public static void main(String[] args) {
        Servidor server = new Servidor(8080);
        server.arrancar();
    }

}