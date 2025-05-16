import Gestores.JugadorGestor;
import Jugadores.CPUJugador;
import Jugadores.HumanoJugador;
import Jugadores.Jugador;
import Preguntas.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static Utils.MetodosEstaticos.stringConComprobacionDigit;

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
        int numRondas = this.elegirTipoPartida();
        this.empezar(numRondas);
    }

    private int elegirTipoPartida() {
        // TODO: Crear el menu de elección de partida.
        int numRondas = -1;
        Scanner teclado = new Scanner(System.in);

        boolean salir = false;
        System.out.println("""
                ¿Que tipo de partida quieres jugar? Elige el numero de la opción que prefieras.
                1.- partida rápida (3 Rondas)
                2.- partida corta (5 Rondas)
                3.- partida normal (10 Rondas)
                4.- Partida larga (20 Rondas)
                5.- Volver al menu de inicio.
                """);

        while (!salir) {
            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
            case 1:
                System.out.println("Has elegido la opción \"Partida rápida\".");
                numRondas = 3;
                break;
            case 2:
                System.out.println("Has elegido la opción \"Partida corta\".");
                numRondas = 5;
                break;
            case 3:
                System.out.println("Has elegido la opción \"Partida normal\".");
                numRondas = 10;
                break;
            case 4:
                System.out.println("Has elegido la opción \"Partida larga.\".");
                numRondas = 20;
                break;
            case 5:
                System.out.println("Has elegido la opción  \"Volver al menu de inicio.\"");
                salir = true;
                break;
            }

        }
        return numRondas;
    }

    private void configurarPartida() throws IOException {
        Scanner teclado = new Scanner(System.in);
        this.jugadoresHumanosDisponibles = this.jugadorGestor.listar();
        int jugadoresHumanos = -1;
        int jugadoresCPU = 0;

        int numJugadores = -1;
        do {
            System.out.println("Indica número de jugadores entre 2 y 4");
            String opcionEscrita = stringConComprobacionDigit();
            numJugadores = Integer.parseInt(opcionEscrita);

        } while (numJugadores > 4 || numJugadores < 2);

        //int limiteJugadoresHumanos = this.jugadoresHumanosDisponibles.size() >= numJugadores ? numJugadores : numJugadores - this.jugadoresHumanosDisponibles.size();
        int limiteJugadoresHumanos = 0;
        if (this.jugadoresHumanosDisponibles.size() >= numJugadores) {
            limiteJugadoresHumanos = numJugadores;
        } else {
            limiteJugadoresHumanos = this.jugadoresHumanosDisponibles.size();
        }

        do {
            System.out.printf("Indica número de jugadores humanos entre 2 y %d\n", limiteJugadoresHumanos);

            String opcionEscrita = stringConComprobacionDigit();
            jugadoresHumanos = Integer.parseInt(opcionEscrita);
        } while (jugadoresHumanos > numJugadores || jugadoresHumanos < 2);

        if (jugadoresHumanos < numJugadores) {
            jugadoresCPU = numJugadores - jugadoresHumanos;
            System.out.printf("El numero de jugadores CPU es: %d \n", jugadoresCPU);

        }

        //HECHO: Seleccionar los jugadores. Podría mostrar por pantalla el listado del fichero de jugadores registrados para que el usuario tenga la info de que
        // jugadores están. Muestra la lista que te da jugadorGestor y escoge entre ellos.
        //HECHO: Comprobar si el numero de jugador seleccionado esta disponible o no lo has escogido ya. Y sino, volver a preguntar otra vez por una elección.
        //HECHO: Si el jugador esta correctamente registrado, añadirlo a la Lista de jugadores "private List<Jugador> jugadores".
        //HECHO: Falta manejar que pasa si el usuario introduce un numero superior o inferior a los de la lista.

        jugadores = new ArrayList<>(); //Es correcto inicializar el array aqui?

        int opcionAntigua = -1;
        int opcionLista = -1;
        for (int cont = 1; cont <= jugadoresHumanos; cont++) {
            String opcionEscrita;
            do {
                jugadorGestor.mostrar();
                System.out.println("Elige un jugador de los que están registrados para jugar, indicando el número en la lista.");
                System.out.println("****Recuerda que no se puede repetir el jugador que ya has elegido.****");
                opcionEscrita = stringConComprobacionDigit();
                opcionLista = Integer.parseInt(opcionEscrita);
            } while (opcionLista > jugadoresHumanosDisponibles.size() || opcionLista <= 0);

            while (opcionAntigua == opcionLista) {
                System.out.println("Esa opción ya la has elegido. Recuerda que no se puede repetir. Por favor elije otro numero.");
                opcionEscrita = stringConComprobacionDigit();
                opcionLista = Integer.parseInt(opcionEscrita);
            }
            HumanoJugador jugadorAAnyadir = jugadoresHumanosDisponibles.get(opcionLista - 1);
            jugadores.add(jugadorAAnyadir);
            System.out.println("Jugador añadido correctamente a la partida.");
            opcionAntigua = opcionLista;

        }

        //HECHO: Falta añadir las CPU a la lista de jugadores.
        if (jugadores.size() < numJugadores) {
            for (int cont = 0; cont < jugadoresCPU; cont++) {
                CPUJugador CPU = new CPUJugador("CPU" + (cont + 1));
                jugadores.add(CPU);
            }
        }

    }

    private void empezar(int numRondas) throws IOException {

        //TODO: Ordenar jugadores de forma aleatoria.
        //jugadores.sort(); //No recuerdo bien como se usaba el Comparator.

        for (int cont = 0; cont <= numRondas; cont++) {
            this.jugarRonda();
        }

    }

    private void terminar() {

        //TODO: Recuento final de puntos. Mostrar por pantalla.
        //TODO: Añadir partida a Histórico.

    }

    public void jugarRonda() {
        //HECHO: Hacer pregunta al azar y evaluar la respuesta de todos los jugadores seleccionados para la partida.
        //HECHO: Añadir puntos a los jugadores.

        // Con el for nos aseguramos que haya una pregunta para cada jugador.
        for (int cont = 0; cont < jugadores.size(); cont++) {
            Random aleatorio = new Random();
            String respuesta;
            int tipoDePregunta = aleatorio.nextInt(1, 5);
            switch (tipoDePregunta) {
            case 1:
                MatematicasPregunta preguntaMates = new MatematicasPregunta();
                preguntaMates.preguntar();
                if (jugadores.get(cont) instanceof HumanoJugador) {
                    HumanoJugador jugadorRondaHumano = (HumanoJugador) jugadores.get(cont);
                    respuesta = jugadorRondaHumano.responder(preguntaMates);
                } else {
                    CPUJugador jugadorRondaCPU = (CPUJugador) jugadores.get(cont);
                    respuesta = jugadorRondaCPU.responder(preguntaMates);
                }

                if (preguntaMates.evaluarRespuesta(respuesta)) {
                    jugadores.get(cont).puntuar();
                }
                break;
            case 2:
                MasterMindPregunta preguntaMastermind = new MasterMindPregunta();
                if (jugadores.get(cont) instanceof HumanoJugador) {
                    HumanoJugador jugadorRondaHumano = (HumanoJugador) jugadores.get(cont);
                    respuesta = jugadorRondaHumano.responder(preguntaMastermind);

                } else {
                    CPUJugador jugadorRondaCPU = (CPUJugador) jugadores.get(cont);
                    respuesta = jugadorRondaCPU.responder(preguntaMastermind);

                }

                if (preguntaMastermind.evaluarRespuesta(respuesta)) {
                    jugadores.get(cont).puntuar();
                }
                break;
            case 3:
                GeografiaPregunta preguntaGeografia = new GeografiaPregunta();
                if (jugadores.get(cont) instanceof HumanoJugador) {
                    HumanoJugador jugadorRondaHumano = (HumanoJugador) jugadores.get(cont);
                    respuesta = jugadorRondaHumano.responder(preguntaGeografia);

                } else {
                    CPUJugador jugadorRondaCPU = (CPUJugador) jugadores.get(cont);
                    respuesta = jugadorRondaCPU.responder(preguntaGeografia);

                }
                if (preguntaGeografia.evaluarRespuesta(respuesta)) {
                    jugadores.get(cont).puntuar();
                }
                break;
            case 4:
                CronometroPregunta preguntaCronometro = new CronometroPregunta();
                if (jugadores.get(cont) instanceof HumanoJugador) {
                    HumanoJugador jugadorRondaHumano = (HumanoJugador) jugadores.get(cont);
                    respuesta = jugadorRondaHumano.responder(preguntaCronometro);

                } else {
                    CPUJugador jugadorRondaCPU = (CPUJugador) jugadores.get(cont);
                    respuesta = jugadorRondaCPU.responder(preguntaCronometro);

                }
                if (preguntaCronometro.evaluarRespuesta(respuesta)) {
                    jugadores.get(cont).puntuar();
                }
                break;

            }
        }

    }

}
