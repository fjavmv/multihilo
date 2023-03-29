package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;

public class Cliente implements Runnable{
    private Socket socket;
    private BufferedReader entrada;
    public Cliente(Socket socket) throws IOException {
        this.socket = socket;
        this.entrada = new BufferedReader( new InputStreamReader(socket.getInputStream()));
    }
    @Override
    public void run() {

        try {
            while(true) {
                String respuesta = entrada.readLine();
                System.out.println("Usuario: " + respuesta);
            }
        } catch (IOException ex) {
            System.out.println("Ha ocurrido un error...." + Arrays.toString(ex.getStackTrace()));
        } finally {
            try {
                entrada.close();
            } catch (Exception ex) {
                System.out.println("Ha ocurrido un error...." + Arrays.toString(ex.getStackTrace()));
            }
        }
    }
}
