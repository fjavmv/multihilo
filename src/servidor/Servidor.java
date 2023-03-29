package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Servidor extends Thread {
    private Socket socket;
    private ArrayList<Servidor> listaDeHilos;
    private PrintWriter salida;

    public Servidor(Socket socket, ArrayList<Servidor> listaDeHilos) {
        this.socket = socket;
        this.listaDeHilos = listaDeHilos;
    }

    @Override
    public void run() {
        try {
            //Leemos el flujo de entrada del cliente
            BufferedReader entrada = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            //Devolvemos  la salidad al cliente pasando la salida al cliente
            // y limpiando el buffer de manera automatica
            salida = new PrintWriter(socket.getOutputStream(),true);

            //Server infinito
            while(true) {
                String cadenaDeSalida = entrada.readLine();
                //Finalizamos el servidor
                if(cadenaDeSalida.equals("Salir")) {
                    break;
                }
                mostrarMensaje(cadenaDeSalida);
                System.out.println("Recibido por el servidor " + cadenaDeSalida);

            }

        } catch (Exception e) {
            System.out.println("Ha ocurrido un erros: " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void mostrarMensaje(String cadenaDeSalida) {
        for( Servidor servidor: listaDeHilos) {
            servidor.salida.println(cadenaDeSalida);
        }

    }
}
