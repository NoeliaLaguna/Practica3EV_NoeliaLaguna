package Entidades.Dominio;

import Entidades.Tipos.*;
import Utils.Constantes;

import java.io.IOException;
import java.util.*;

import static Utils.MetodosEstaticos.stringConComprobacionDigitNoDecimales;

public class Juego {

    private List<Jugador> jugadores;
    private List<Pregunta> preguntas;
    private int rondas;
    private boolean depuracion;
    private JugadorGestor jugadorGestor;
    private HistorialGestor gestorHistorial;
    private ConfigGestor gestorConfig;
    private LogGestor gestorLogs;
    private ArrayList<HumanoJugador> jugadoresHumanosDisponibles;

    public Juego(JugadorGestor jugadorGestor, HistorialGestor gestorHistorial, ConfigGestor gestorConfig, LogGestor gestorLogs) {
        this.jugadorGestor = jugadorGestor;
        this.gestorHistorial = gestorHistorial;
        this.gestorConfig = gestorConfig;
        this.gestorLogs = gestorLogs;
    }

    public void ejecutar() throws IOException {
        this.configurarPartida();
        this.elegirTipoPartida();
        this.empezar();
    }

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

        int opcion = teclado.nextInt();
        teclado.nextLine();

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
            break;
        }

    }

    private void configurarPartida() throws IOException {
        Scanner teclado = new Scanner(System.in);
        this.jugadoresHumanosDisponibles = this.jugadorGestor.listar();
        int jugadoresHumanos = -1;
        int jugadoresCPU = 0;

        //TODO:controlar si se introduce numero decimal.
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
                opcionEscrita = stringConComprobacionDigitNoDecimales();
                opcionLista = Integer.parseInt(opcionEscrita);
            } while (opcionLista > jugadoresHumanosDisponibles.size() || opcionLista <= 0);

            while (opcionAntigua == opcionLista) {
                System.out.println("Esa opción ya la has elegido. Recuerda que no se puede repetir. Por favor elije otro numero.");
                opcionEscrita = stringConComprobacionDigitNoDecimales();
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

    private void empezar() throws IOException {

        //HECHO: Ordenar jugadores de forma aleatoria.
        Collections.shuffle(jugadores);

        for (int cont = 0; cont <= this.rondas; cont++) {
            System.out.printf("***INICIO DE LA RONDA %d***\n", (cont + 1));
            this.jugarRonda();
            System.out.printf("***FIN DE LA RONDA %d***\n", (cont + 1));
        }

    }

    private void terminar() {

        //TODO: Recuento final de puntos. Mostrar por pantalla.

        //HECHO: Añadir partida a Histórico. Para hacerlo he tenido que añadir al constructor de Juego todos los gestores.
        try {
            gestorHistorial.registrar(this);
        } catch (IOException e) {
            System.err.println("Error al registrar la partida." + e);
            e.printStackTrace();
        }

    }

    public void jugarRonda() {
        //HECHO: Hacer pregunta al azar y evaluar la respuesta de todos los jugadores seleccionados para la partida.
        //HECHO: Añadir puntos a los jugadores.

        // Con el for nos aseguramos que haya una pregunta para cada jugador.
        //HECHO: Hay que poner mensajes indicando a quien le toca jugar.
        for (int cont = 0; cont < jugadores.size(); cont++) {
            Jugador jugador = jugadores.get(cont);
            System.out.printf("Es el turno del jugador: %s \n", jugadores.get(cont).getNombre());

            Random aleatorio = new Random();
            String respuesta;
            int tipoDePregunta = 3; //aleatorio.nextInt(1, 5);
            Pregunta pregunta = null;
            switch (tipoDePregunta) {
            case 1 -> pregunta = new MatematicasPregunta(); //HECHO: Hay que decir que tipo de pregunta ha salido.
            case 2 -> pregunta = new MasterMindPregunta();
            case 3 -> pregunta = new GeografiaPregunta();
            case 4 -> pregunta = new CronometroPregunta();
            default -> new MatematicasPregunta();
            }

            boolean acierto = false;

            if (pregunta != null) {
                pregunta.preguntar();

                for (int i = 0; i < pregunta.getNumeroIntentos() && !acierto; i++) {
                    System.out.println("Escribe tu respuesta:");
                    respuesta = jugador.responder(pregunta);
                    acierto = pregunta.evaluarRespuesta(respuesta);
                    if (acierto) {
                        jugador.puntuar();
                    }
                }

            }
        }

    }

}
