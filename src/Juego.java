import Gestores.JugadorGestor;
import Jugadores.HumanoJugador;
import Jugadores.Jugador;
import Preguntas.Pregunta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Juego {

    private List<Jugador> jugadores;
    private List<Pregunta> preguntas;
    private int rondas;
    private boolean depuracion;
    private JugadorGestor jugadorGestor;
    private ArrayList<HumanoJugador> jugadoresHumanosDisponibles;

    public Juego(JugadorGestor jugadorGestor) {
        this.jugadorGestor = jugadorGestor;
    }

    public void ejecutar() throws IOException {
        this.configurarPartida();
        this.elegirTipoPartida();
        this.empezar();
    }

    private void elegirTipoPartida() {
        // TODO
    }

    private void configurarPartida() throws IOException {
        Scanner teclado = new Scanner(System.in);
        this.jugadoresHumanosDisponibles = this.jugadorGestor.listar();
        int jugadoresHumanos = -1;
        int jugadoresCPU = 0;

        int numJugadores = -1;
        do {
            System.out.println("Indica número de jugadores entre 2 y 4");
            numJugadores = teclado.nextInt();
        } while (numJugadores > 4 || numJugadores < 2);

        //int limiteJugadoresHumanos = this.jugadoresHumanosDisponibles.size() >= numJugadores ? numJugadores : numJugadores - this.jugadoresHumanosDisponibles.size();
        int limiteJugadoresHumanos = 0;
        if (this.jugadoresHumanosDisponibles.size() >= numJugadores) {
            limiteJugadoresHumanos = numJugadores;
        } else {
            limiteJugadoresHumanos = this.jugadoresHumanosDisponibles.size();
        }

        do {
            System.out.printf("Indica número de jugadores humanos entre 2 y %d", limiteJugadoresHumanos);
            numJugadores = teclado.nextInt();
        } while (jugadoresHumanos > numJugadores || jugadoresHumanos < 2);

        if (jugadoresHumanos < numJugadores) {
            jugadoresCPU = numJugadores - jugadoresHumanos;
            System.out.printf("El numero de jugadores CPU es: %d", jugadoresCPU);
        }

        //TODO: Seleccionar los jugadores. Podría mostrar por pantalla el listado del fichero de jugadores registrados para que el usuario tenga la info de que juagdores están. Muestra la lista que te da jugadorGestor y escoge entre ellos
        //TODO: Comprobar si el numero de jugador seleccionado esta disponible o no lo has escogido ya. Y sino, volver a preguntar otra vez por una eleccion
        //TODO: Si el jugador esta correctamente registrado, añadirlo a la Lista de jugadores "private List<Jugador> jugadores".

    }

    private void empezar() throws IOException {

        // TODO: generar preguntas aleatorias

    }

    private void terminar() {

        //TODO: Recuento final de puntos. Mostrar por pantalla.
        //TODO: Añadir partida a Histórico.

    }

    public void jugarRonda() {
        //TODO: Hacer pregunta y evaluar la respuesta de todos los jugadores seleccionados para la partida.
        //TODO: Añadir puntos a los jugadores.

    }

}
