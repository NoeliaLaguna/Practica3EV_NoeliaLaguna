import Jugadores.Jugador;
import Preguntas.Pregunta;

import java.util.List;
import java.util.Scanner;

public class Juego {

    private List<Jugador> jugadores;
    private List<Pregunta> preguntas;
    private int rondas;
    private boolean depuracion;

    public void empezar(Scanner teclado) {
        int jugadoresHumanos;
        int jugadoresCPU;

        System.out.println("¿Cuantos Jugadores  quieres que tenga la partida?");
        int numJugadores = teclado.nextInt();
        teclado.nextLine();
        while (numJugadores > 4 || numJugadores < 2) {
            System.out.println("Lo siento, el máximo de jugadores es 4 y el mínimo es 2. Por favor, elija el numero de jugadores.");
            numJugadores = teclado.nextInt();
            teclado.nextLine();
        }

        System.out.println("¿Cuantos jugadores son humanos?");
        jugadoresHumanos = teclado.nextInt();
        teclado.nextLine();

        System.out.println("¿Cuantos jugadores son CPU?");
        do {
            jugadoresCPU = teclado.nextInt();
            teclado.nextLine();
            if (jugadoresCPU + jugadoresHumanos != numJugadores) {
                System.out.println("El número de jugadores CPU sumado al número de jugadores humanos debe ser igual al número total de jugadores.");
            }
        } while (jugadoresCPU + jugadoresHumanos != numJugadores);

    }

    public void terminar() {

    }

    public void jugarRonda() {

    }

}
