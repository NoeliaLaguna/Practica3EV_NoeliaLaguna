package Entidades.Dominio;

import Entidades.Tipos.*;
import Utils.Constantes;

import java.io.IOException;
import java.util.*;

import static Utils.MetodosEstaticos.stringConComprobacionDigitNoDecimales;

/**
 * Clase principal que gestiona el desarrollo de una partida del juego.
 * <p>
 * Esta clase controla el flujo completo de la partida, incluyendo la configuración inicial, selección del tipo de partida, manejo de rondas,
 * preguntas, puntuaciones y registro del historial al finalizar.
 * <p>
 * Utiliza distintos gestores para la gestión de jugadores, historial,
 * configuración y logs. También permite partidas mixtas entre jugadores humanos
 * y jugadores controlados por la CPU.
 *
 * @author NoeliaLaguna
 * @version 1.0
 */
public class Juego {

    private List<Jugador> jugadores;
    private List<Pregunta> preguntas;
    private int rondas;
    private boolean depuracion;
    private JugadorGestor gestorJugador;
    private HistorialGestor gestorHistorial;
    private ConfigGestor gestorConfig;
    private LogGestor gestorLogs;
    private ArrayList<HumanoJugador> jugadoresHumanosDisponibles;

    /**
     * Crea una nueva instancia de Juego con los gestores necesarios para su manejo y el manejo de los ficheros.
     * <p>
     *
     * @param gestorJugador   Se recibe el gestor necesario para acceder a los metodos de gestion de los jugadores desde la clase juego.
     * @param gestorHistorial Se recibe el gestor necesario para acceder a los metodos de gestion del historial desde la clase juego.
     * @param gestorConfig    Se recibe el gestor necesario para acceder a los metodos de gestion de la configuración desde la clase juego.
     * @param gestorLogs      Se recibe el gestor necesario para acceder a los metodos de gestion de los logs desde la clase juego.
     */
    public Juego(JugadorGestor gestorJugador, HistorialGestor gestorHistorial, ConfigGestor gestorConfig, LogGestor gestorLogs) {
        this.gestorJugador = gestorJugador;
        this.gestorHistorial = gestorHistorial;
        this.gestorConfig = gestorConfig;
        this.gestorLogs = gestorLogs;
    }

    /**
     * Este metodo se encarga de la ejecución de todos los metodos necesarios para el desarrollo de la partida.
     * Invoca los metodos configurarPartida(), elegirTipoPartida(), empezar() y terminar().
     */
    public void ejecutar() throws IOException {
        this.configurarPartida();
        this.elegirTipoPartida();
        this.empezar();
        this.terminar();
    }

    /**
     * Este metodo se encarga de la selección del tipo de partida. Es un switch en el que el usuario elige que tipo de partida quiere jugar y en
     * base al tipo de partida se modifica el atributo rondas con el numero correspondiente.
     */
    private void elegirTipoPartida() {
        // HECHO: Crear el menu de elección de partida.
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

        String opcionEscrita = stringConComprobacionDigitNoDecimales();
        int opcion = Integer.parseInt(opcionEscrita);

        switch (opcion) {
        case 1:
            System.out.println("Has elegido la opción \"Partida rápida\".");
            this.rondas = Constantes.RONDAS_PARTIDA_RAPIDA;
            break;
        case 2:
            System.out.println("Has elegido la opción \"Partida corta\".");
            this.rondas = Constantes.RONDAS_PARTIDA_CORTA;
            ;
            break;
        case 3:
            System.out.println("Has elegido la opción \"Partida normal\".");
            this.rondas = Constantes.RONDAS_PARTIDA_NORMAL;
            ;
            break;
        case 4:
            System.out.println("Has elegido la opción \"Partida larga.\".");
            this.rondas = Constantes.RONDAS_PARTIDA_LARGA;
            break;
        case 5:
            System.out.println("Has elegido la opción  \"Volver al menu de inicio.\"");
            //TODO:Esto esta bien hecho? Hay otra manera de volver al menu de inicio?
            String[] args = {};
            Main.main(args);
            break;
        }

    }

    /**
     * Este metodo se encarga de la selección del número de jugadores haciendo las comprobaciones necesarias.
     * Después muestra la lista de jugadores disponibles al usuario para que elija con los jugadores que quiere jugar. También realizando las
     * comprobaciones necesarias (que no se repita el jugador o que el jugador se ecnuentre en la lista de jugadores registrados).
     * Después se añaden a la lista de jugadores que van a jugar la partida, tanto humanos como CPUs.
     */
    private void configurarPartida() throws IOException {
        Scanner teclado = new Scanner(System.in);
        this.jugadoresHumanosDisponibles = this.gestorJugador.listar();
        int jugadoresHumanos = -1;
        int jugadoresCPU = 0;

        //HECHO:controlar si se introduce numero decimal.
        int numJugadores = -1;
        do {
            System.out.println("Indica número de jugadores entre 2 y 4");
            String opcionEscrita = stringConComprobacionDigitNoDecimales();
            numJugadores = Integer.parseInt(opcionEscrita);

        } while (numJugadores > 4 || numJugadores < 2);

        int limiteJugadoresHumanos = 0;
        if (this.jugadoresHumanosDisponibles.size() >= numJugadores) {
            limiteJugadoresHumanos = numJugadores;
        } else {
            limiteJugadoresHumanos = this.jugadoresHumanosDisponibles.size();
        }

        do {
            System.out.printf("Indica número de jugadores humanos entre 1 y %d\n", limiteJugadoresHumanos);

            String opcionEscrita = stringConComprobacionDigitNoDecimales();
            jugadoresHumanos = Integer.parseInt(opcionEscrita);
        } while (jugadoresHumanos > numJugadores || jugadoresHumanos < 1);

        if (jugadoresHumanos < numJugadores) {
            jugadoresCPU = numJugadores - jugadoresHumanos;
            System.out.printf("El numero de jugadores CPU es: %d \n\n", jugadoresCPU);

        }

        jugadores = new ArrayList<>();

        ArrayList<Integer> opcionesAntiguas = new ArrayList<>();
        int opcionLista = -1;
        for (int cont = 1; cont <= jugadoresHumanos; cont++) {
            String opcionEscrita;
            do {
                gestorJugador.mostrar();
                System.out.println("\n Elige un jugador de los que están registrados para jugar, indicando el número en la lista.");
                System.out.println("****Recuerda que no se puede repetir el jugador que ya has elegido.****\n");
                opcionEscrita = stringConComprobacionDigitNoDecimales();
                opcionLista = Integer.parseInt(opcionEscrita);
            } while (opcionLista > jugadoresHumanosDisponibles.size() || opcionLista <= 0);

            while (opcionesAntiguas.contains(opcionLista)) {
                System.out.println("Esa opción ya la has elegido. Recuerda que no se puede repetir. Por favor elige otro numero.");
                opcionEscrita = stringConComprobacionDigitNoDecimales();
                opcionLista = Integer.parseInt(opcionEscrita);
            }
            HumanoJugador jugadorAAnyadir = jugadoresHumanosDisponibles.get(opcionLista - 1);
            jugadores.add(jugadorAAnyadir);
            System.out.printf("El jugador %s añadido correctamente a la partida. \n\n", jugadorAAnyadir.getNombre());
            opcionesAntiguas.add(opcionLista);

        }

        if (jugadores.size() < numJugadores) {
            for (int cont = 0; cont < jugadoresCPU; cont++) {
                CPUJugador CPU = new CPUJugador("CPU" + (cont + 1));
                jugadores.add(CPU);
            }
        }

    }

    /**
     * Este metodo ordena la lista de jugadores en un orden aleatorio.
     * Después dentro de un bucle "for", usando como limite el atributo rondas, invoca el metodo jugarRonda() que pertenece a esta misma clase.
     */
    private void empezar() throws IOException {

        //HECHO: Ordenar jugadores de forma aleatoria.
        Collections.shuffle(jugadores);

        for (int cont = 0; cont <= this.rondas; cont++) {
            System.out.printf("\n***INICIO DE LA RONDA %d***\n", (cont + 1));
            this.jugarRonda();
            System.out.printf("\n***FIN DE LA RONDA %d***\n", (cont + 1));
        }

    }

    /**
     * Este metodo hace un recuento final de puntos, mostrandolos por pantalla.
     * Después invoca el metodo registrar(), que pertenece a la clase HistorialGestor, donde se registra la partida.
     */
    private void terminar() {
        //TODO: Recuento final de puntos. Mostrar por pantalla.
        System.out.println("\n***RECUENTO FINAL DE PUNTOS***\n");
        System.out.println(this);

        //HECHO: Añadir partida a Histórico. Para hacerlo he tenido que añadir al constructor de Juego todos los gestores.
        try {
            gestorHistorial.registrar(this);
        } catch (IOException e) {
            System.err.println("\nError al registrar la partida." + e);
            e.printStackTrace();
        }

    }

    /**
     * Este metodo crea una de un tipo pregunta al azar usando un switch y número aleatorio de la lista del switch.
     * Después llama al metodo preguntar(), que pertenece a la clase Pregunta, y al metodo evaluarRespuesta().
     * Si el valor que devuelve el metodo evaluarRespuesta() es True, se llama al metodo puntuar, que pertenece a la clase Jugador.
     */
    private void jugarRonda() {
        for (int cont = 0; cont < jugadores.size(); cont++) {
            Jugador jugador = jugadores.get(cont);
            System.out.printf("\n Es el turno del jugador: %s \n \n", jugadores.get(cont).getNombre());

            Random aleatorio = new Random();
            String respuesta;
            int tipoDePregunta = 4; //aleatorio.nextInt(1, 5);
            Pregunta pregunta = null;
            switch (tipoDePregunta) {
            case 1 -> pregunta = new MatematicasPregunta();
            case 2 -> pregunta = new MasterMindPregunta();
            case 3 -> pregunta = new GeografiaPregunta();
            case 4 -> pregunta = new CronometroPregunta();
            default -> new MatematicasPregunta();
            }

            boolean acierto = false;

            if (pregunta != null) {
                pregunta.preguntar();

                for (int i = 0; i < pregunta.getNumeroIntentos() && !acierto; i++) {
                    System.out.println("\nEscribe tu respuesta:");
                    respuesta = jugador.responder(pregunta);
                    acierto = pregunta.evaluarRespuesta(respuesta);
                    if (acierto) {
                        jugador.puntuar();
                    }
                }

            }
        }

    }

    @Override
    public String toString() {
        List<String> puntuaciones = new ArrayList<>();
        for (Jugador j : this.jugadores) {
            puntuaciones.add(j.getNombre() + ":" + j.getPuntos());
        }
        return String.join(",", puntuaciones);
    }

}
