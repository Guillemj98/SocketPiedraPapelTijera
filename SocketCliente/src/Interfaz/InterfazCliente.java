package Interfaz;

import java.util.Scanner;

import GestorCliente.GestorCliente;

public class InterfazCliente {

    public static void main(String[] args) {

        GestorCliente gc = GestorCliente.getInstance();
        Scanner sc = new Scanner(System.in);

        System.out.println("Aplicación Cliente:");

        String nombre;
        String eleccion;
        String resultado;

        System.out.println("Piedra-Papel-Tijera");
        System.out.println("Ingresa tu nombre de jugador:");
        nombre = sc.nextLine();


        System.out.println("Elige tu jugada:");

        do {
            System.out.println("[1] Piedra");
            System.out.println("[2] Papel");
            System.out.println("[3] Tijera");

            /*
            1 le gana al 3
            2 le gana al 1
            3 le gana al 2
             */

            eleccion = sc.nextLine();

            resultado = gc.enviarEleccion(nombre, eleccion);

            System.out.println(resultado);

        }while(!resultado.contains("Victoria"));


    }

}
