package modelo.vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


import modelo.gestor.GestorServidor;

public class VistaServidor {
	public static final int PUERTO = 2077;

	public static void main(String[] args) {
		InputStreamReader entrada = null;
		InputStreamReader entradaCliente2 = null;
		PrintStream salida = null;
		Socket socketAlCliente1 = null;
		Socket socketAlCliente2 = null;
		GestorServidor gc = GestorServidor.obtenerInstancia();
		InetSocketAddress direccion = new InetSocketAddress(PUERTO);
		System.out.println("╔════════════════════════════════╗");
	    System.out.println("║      Piedra, Papel o Tijera    ║");
	    System.out.println("╚════════════════════════════════╝");
	    try{
	    	
	    	ServerSocket serverSocket = new ServerSocket();
	    	serverSocket.bind(direccion);
	    	while(true) {
		    	System.out.println("SERVIDOR: Esperando peticion por el puerto: " + PUERTO);
	    		socketAlCliente1 = serverSocket.accept();
	    		System.out.println("Cliente 1 conectado");
	    		socketAlCliente2 = serverSocket.accept();
	    		System.out.println("Cliente 2 conectado");
	    		
	    		System.out.println("SERVIDOR: Petición recibida");
	    		entrada = new InputStreamReader(socketAlCliente1.getInputStream());
	    		entradaCliente2 = new InputStreamReader(socketAlCliente2.getInputStream());
	    		BufferedReader bf = new BufferedReader(entrada);
	    		BufferedReader bf2 = new BufferedReader(entradaCliente2);
	    	
	    
	    		String recibido = bf.readLine();
	    		String recibido2 = bf2.readLine();
	    		
	    		String mensaje = recibido + "/" + recibido2;
	    		String victorias = gc.partida(mensaje);
	    		
	    		
	    		
	    		salida = new PrintStream(socketAlCliente1.getOutputStream());
	    		salida.println(victorias);// Metemos dentro del los parentesis el gestor creado para 
	    		// determinar quien es el gandor de la partida
	    		salida = new PrintStream(socketAlCliente2.getOutputStream());
	    		salida.println(victorias);
	    		
	    		if(victorias.contains("Victoria")) {
	    			serverSocket.close();
	    		}
	    	}
	    	
	    	
	    } catch (IOException e) {
			
			e.printStackTrace();
		}
		
		

	}

}
