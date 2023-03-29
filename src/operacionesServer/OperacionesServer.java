package operacionesServer;

import servidor.Servidor;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class OperacionesServer {
    private static final int PORT = 8081;
    public static void lanzarServidor(){
        //lista de los clientes
        ArrayList<Servidor> listaDeHilos = new ArrayList<>();

        try (ServerSocket serversocket = new ServerSocket(PORT)){
            while(true) {
                Socket socket = serversocket.accept();
                Servidor servidor = new Servidor(socket, listaDeHilos);
                //lanzar hilo
                listaDeHilos.add(servidor);
                servidor.start();
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + Arrays.toString(e.getStackTrace()));
        }
    }
}
