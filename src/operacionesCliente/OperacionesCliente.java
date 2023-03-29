package operacionesCliente;

import cliente.Cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class OperacionesCliente {
    private static final String IP = "172.25.13.19";
    private static final int PORT = 8081;

    public static void lanzarCliente(){
        try (Socket socket = new Socket(IP, PORT)){
            // Retornamos la salida al servidor
            PrintWriter salida = new PrintWriter(socket.getOutputStream(),true);
            //Cachamos las entradas de los usuarios
            Scanner scanner = new Scanner(System.in);
            String datosDeEntrada;
            String cliente = "Sin conexiones";

            Cliente iniciarCliente = new Cliente(socket);
            //Creamos una instancia de Thread para lanzar el hilo
            new Thread(iniciarCliente).start();

            do {

                if (cliente.equals("Sin conexiones")) {
                    System.out.println("Ingresa el nombre usuario: ");
                    datosDeEntrada = scanner.nextLine();
                    cliente = datosDeEntrada;
                    salida.println(datosDeEntrada);
                    //finalizamos si escribimos Salir
                    if (datosDeEntrada.equals("Salir")) {
                        break;
                    }
                }
                else {
                    String mensaje = ( "Usuario: " + cliente + " -> " + "Mensaje : " );
                    System.out.println(mensaje);
                    datosDeEntrada = scanner.nextLine();
                    salida.println(mensaje + " " + datosDeEntrada);
                    if (datosDeEntrada.equals("Salir")) {
                        //fin de bloque de código
                        break;
                    }
                }

            } while (!datosDeEntrada.equals("Salir"));

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + Arrays.toString(e.getStackTrace()));
        }
    }
}
