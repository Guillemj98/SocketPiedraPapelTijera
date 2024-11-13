package modelo.gestor;


// El gestor servidor esta hecho Singletone.
public class GestorServidor {
	private static GestorServidor instance;
	private static int contadorJugador1 =0;
	private static int contadorJugador2 =0;

	public GestorServidor() {
		
	}
	public static GestorServidor obtenerInstancia() {
		if(instance ==null) {
			return instance = new GestorServidor();
		}
		return instance;
	}
	
	
	public String partida(String stringRecibido) {
		
		String resultado = "";
		
		String[] partido = stringRecibido.split("/");
		String []cliente1 = partido[0].split("-");
		String []cliente2 = partido[1].split("-");
		
		String nombreCliente1 = cliente1[0];
		String eleccionCliente1 = cliente1[1];
		
		String nombreCliente2 = cliente2[0];
		String eleccionCliente2 = cliente2[1];
		
		switch (eleccionCliente1) {
		case "1":
			if(eleccionCliente2.equals("1")) {
				resultado = "Empate";
			}else if(eleccionCliente2.equals("2")) {
				resultado = nombreCliente2 + " Gana";
				contadorJugador2++;
			}else if(eleccionCliente2.equals("3")) {
				resultado = nombreCliente1 + " Gana";
				contadorJugador1++;
			}
			break;
		case "2":
			if(eleccionCliente2.equals("1")) {
				resultado = nombreCliente1 + " Gana";
				contadorJugador1++;
			}else if(eleccionCliente2.equals("2")) {
				resultado = "Empate";
				
			}else if(eleccionCliente2.equals("3")) {
				resultado = nombreCliente2 + " Gana";
				contadorJugador2++;
				
			}		
			break;
		case "3":
			if(eleccionCliente2.equals("1")) {
				resultado = nombreCliente2 + " Gana";
				contadorJugador2++;
			}else if(eleccionCliente2.equals("2")) {
				resultado = nombreCliente1 + " Gana";
				contadorJugador1++;
			}else if(eleccionCliente2.equals("3")) {
				resultado = "Empate";
				
			}
	
			break;

		default:
			break;
		}
		
		if(contadorJugador1 == 3) {
			resultado = "Victoria de " + nombreCliente1;
		}else if(contadorJugador2 == 3) {
			resultado = "Victoria de " + nombreCliente2;
		}
		
		
		return resultado;
	}
	
	

}
