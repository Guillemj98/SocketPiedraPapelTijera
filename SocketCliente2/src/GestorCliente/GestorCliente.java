package GestorCliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class GestorCliente {
	    public static GestorCliente instance;

	    private static final String IP_SERVER = "localhost";
	    private static final int PUERTO = 2077;

	    private GestorCliente(){

	    }

	    public static GestorCliente getInstance(){
	        if(instance == null){
	            instance = new GestorCliente();
	        }
	        return instance;
	    }

	    public String enviarEleccion(String nombre, String eleccion){

	        String respuesta = "";

	        InetSocketAddress socketAddress = new InetSocketAddress(IP_SERVER, PUERTO);

	        try{
	            Socket socketAlServidor = new Socket();
	            System.out.println("Cliente: Esperando a que el servidor acepte la conexión");
	            socketAlServidor.connect(socketAddress);
	            System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER
	                    + " por el puerto " + PUERTO);

	            PrintStream out = new PrintStream(socketAlServidor.getOutputStream());
	            out.println(nombre + "-" + eleccion);

	            InputStreamReader isr = new InputStreamReader(socketAlServidor.getInputStream());
	            BufferedReader in = new BufferedReader(isr);

	            respuesta = in.readLine();

	            if(respuesta.contains("Victoria")){
	                socketAlServidor.close();
	            }

	        }catch (UnknownHostException e) {
	            System.err.println("CLIENTE: No encuentro el servidor en la dirección" + IP_SERVER);
	            e.printStackTrace();
	        } catch (IOException e) {
	            System.err.println("CLIENTE: Error de entrada/salida");
	            e.printStackTrace();
	        } catch (Exception e) {
	            System.err.println("CLIENTE: Error -> " + e);
	            e.printStackTrace();
	        }
	        return respuesta;
	    }


	}



