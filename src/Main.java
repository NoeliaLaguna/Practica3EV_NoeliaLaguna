import Gestores.ConfigGestor;
import Gestores.JugadorGestor;
import Gestores.LogGestor;
import Jugadores.HumanoJugador;

import java.io.IOException;
import java.util.Scanner;

import static Utils.MetodosEstaticos.stringConComprobacionDigit;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        //declarar resto de gestores
        JugadorGestor gestorJugador;
        HistorialGestor gestorhistorial;
        LogGestor gestorLogs;
        ConfigGestor gestorConfig;

        try {
            //crear resto de gestores
            gestorJugador = new JugadorGestor();
            gestorhistorial = new HistorialGestor();
            gestorConfig = new ConfigGestor();
            gestorLogs = new LogGestor();

        } catch (IOException ex) {
            ex.printStackTrace();

            // si no somos capaces de crear los gestores
            return;
        }

        boolean salir = false;

        while (!salir) {
            System.out.println("***Bienvenido al Ultimate Quiz.***");
            System.out.println("""
                    ¿Qué quieres hacer? Elige el número de la opción que prefieras.
                    1.- Jugar partida.
                    2.- Ver el ranking.
                    3.- Ver el histórico de partidas.
                    4.- Acceder al submenu de Jugadores.
                    5.- Salir""");
            String opcionEscrita = stringConComprobacionDigit();
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
                    System.out.println("Has elegido la opción \"Jugar una partida\"");
                    Juego juego = new Juego(gestorJugador);

                    juego.ejecutar();
                } catch (IOException e) {
                    System.err.println("Error al iniciar el juego.");
                }

                //juego.empezar(teclado);
                //elegirTipoDePartida(teclado, juego); //No sé si debería meterlo en juego o dejarlo aqui.

                break;
            case 2:
                System.out.println("Has elegido la opción \"Ver el Ranking\".");

                break;
            case 3:
                System.out.println("Has elegido la opción \"Ver el histórico de partidas\".");
                break;
            case 4:
                System.out.println("Has elegido la opción \"Acceder al submenu de jugadores\".");

                try {
                    accederSubmenuJugadores(gestorJugador);
                } catch (IOException e) {
                    throw new RuntimeException(e);
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

        String opcionEscrita = stringConComprobacionDigit();
        opcion = Integer.parseInt(opcionEscrita);

        switch (opcion) {
        case 1:
            System.out.println("Has elegido la opción \"Ver Jugadores\".");
            break;
        case 2:
            System.out.println("Has elegido la opción \"Añadir jugador\".");

            System.out.println("""
                    Ahora debemos registrar los jugadores.
                    Dime el nombre del jugador, recuerda, cada nombre es único en el sistema.
                    (Si ya estás registrado, el sistema lo detectará al escribir tu nombre).""");
            //TODO: Controlar que solamente se pueden meter nombres sin espacios.
            String nombre = teclado.nextLine();
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

