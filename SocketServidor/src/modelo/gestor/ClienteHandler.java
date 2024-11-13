package modelo.gestor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClienteHandler implements Runnable {
    private Socket clienteSocket;
    private GestorServidor gestor;

    public ClienteHandler(Socket clienteSocket, GestorServidor gestor) {
        this.clienteSocket = clienteSocket;
        this.gestor = gestor;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
             PrintStream salida = new PrintStream(clienteSocket.getOutputStream())) {

            String recibido = entrada.readLine();
            System.out.println("Mensaje del cliente: " + recibido);

            // Procesar el mensaje y enviar respuesta
            String resultado = gestor.partida(recibido);
            salida.println(resultado);

        } catch (IOException e) {
            System.err.println("Error al comunicar con el cliente: " + e.getMessage());
        }
    }
}
