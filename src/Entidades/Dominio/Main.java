package Entidades.Dominio;

import Entidades.Tipos.HumanoJugador;

import java.io.IOException;
import java.util.Scanner;

import static Utils.MetodosEstaticos.stringConComprobacionDigitNoDecimales;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        JugadorGestor gestorJugador;
        HistorialGestor gestorhistorial;
        LogGestor gestorLogs;
        ConfigGestor gestorConfig;

        try {
            gestorJugador = new JugadorGestor();
            gestorhistorial = new HistorialGestor();
            gestorConfig = new ConfigGestor();
            gestorLogs = new LogGestor();
            gestorhistorial.verRanking();
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        boolean salir = false;

        while (!salir) {
            System.out.println("\n***Bienvenido al Ultimate Quiz.***\n");
            System.out.println("""
                    ¿Qué quieres hacer? Elige el número de la opción que prefieras.
                    1.- Jugar partida.
                    2.- Ver el ranking.
                    3.- Ver el histórico de partidas.
                    4.- Acceder al submenu de Jugadores.
                    5.- Salir""");
            String opcionEscrita = stringConComprobacionDigitNoDecimales();
            int opcion = Integer.parseInt(opcionEscrita);

            switch (opcion) {
            case 1:

                try {
                    if (gestorJugador.sinJugadores()) {
                        System.err.println("No tienes jugadores registrados.");
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    System.out.println("Has elegido la opción \"Jugar una partida\" \n");
                    Juego juego = new Juego(gestorJugador, gestorhistorial, gestorConfig, gestorLogs);
                    juego.ejecutar();

                } catch (IOException e) {
                    System.err.println("Error al iniciar el juego.");
                }

                break;
            case 2:
                System.out.println("Has elegido la opción \"Ver el Ranking\".");
                try {
                    gestorhistorial.verRanking();
                } catch (IOException e) {
                    System.err.println("Error al intentar mostrar el ranking." + e);
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("Has elegido la opción \"Ver el histórico de partidas\".");
                try {
                    gestorhistorial.mostrar();
                } catch (IOException e) {
                    System.err.println("Error al intentar mostrar el historial." + e);
                    e.printStackTrace();
                }
                break;
            case 4:
                System.out.println("Has elegido la opción \"Acceder al submenu de jugadores\".");

                try {
                    accederSubmenuJugadores(gestorJugador);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                System.out.println("Has elegido la opción salir. Hasta pronto!!");
                salir = true;
                break;
            default:

            }

        }

    }

    private static void accederSubmenuJugadores(JugadorGestor jugadorGestor) throws IOException {
        Scanner teclado = new Scanner(System.in);
        int opcion;
        System.out.println("""
                Estas en el el submenu de Jugadores. ¿Qué quieres hacer? Elige el número de la opción que prefieras.
                1.- Ver Jugadores: muestra la lista de jugadores registrados.\s
                2.- Añadir jugador: permite añadir al sistema a un nuevo jugador.\s
                3.- Eliminar jugador: permite eliminar del sistema a un jugador registrado.\s
                4.- Volver: vuelve al menú principal.""");

        String opcionEscrita = stringConComprobacionDigitNoDecimales();
        opcion = Integer.parseInt(opcionEscrita);

        switch (opcion) {
        case 1:
            System.out.println("Has elegido la opción \"Ver Jugadores\".");
            break;
        case 2:
            System.out.println("Has elegido la opción \"Añadir jugador\".");
            String nombre;
            do {
                System.out.println("""
                        Ahora debemos registrar los jugadores.
                        Dime el nombre del jugador, recuerda, cada nombre es único en el sistema y no puede contener espacios.
                        (Si ya estás registrado, el sistema lo detectará al escribir tu nombre).""");

                nombre = teclado.nextLine();
            } while (nombre.contains(" "));

            HumanoJugador jug = new HumanoJugador(nombre);
            try {
                jugadorGestor.registrar(jug);
            } catch (IOException e) {
                System.err.println("Error al registrar el jugador: " + e);
            }

            break;
        case 3:
            System.out.println("Has elegido la opción \"Eliminar jugador\".");
            break;
        case 4:
            System.out.println("Volver al Menu Principal.");
            break;
        }
    }
}

