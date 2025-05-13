import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MatematicasPregunta preguntaPrueba = new MatematicasPregunta();
        preguntaPrueba.preguntar();

        Scanner teclado = new Scanner(System.in);

        int jugadoresHumanos = 0;
        int jugadoresCPU;
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

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
            case 1:
                System.out.println("Has elegido la opción \"Jugar una partida\"");
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

                Juego juego = new Juego();
                juego.empezar();
                elegirTipoDePartida(teclado, juego);

                break;
            case 2:
                System.out.println("Has elegido la opción \"Ver el Ranking\".");

                break;
            case 3:
                System.out.println("Has elegido la opción \"Ver el histórico de partidas\".");
                break;
            case 4:
                System.out.println("Has elegido la opción \"Acceder al submenu de jugadores\".");
                accederSubmenuJugadores(teclado, jugadoresHumanos);
                break;
            case 5:
                System.out.println("Has elegido la opción salir. Hasta pronto!!");
                salir = true;
                break;
            default:

            }

        }

    }

    private static void accederSubmenuJugadores(Scanner teclado, int jugadoresHumanos) {
        int opcion;
        System.out.println("""
                Estas en el el submenu de Jugadores. ¿Qué quieres hacer? Elige el número de la opción que prefieras.
                1.- Ver Jugadores: muestra la lista de jugadores registrados.\s
                2.- Añadir jugador: permite añadir al sistema a un nuevo jugador.\s
                3.- Eliminar jugador: permite eliminar del sistema a un jugador registrado.\s
                4.- Volver: vuelve al menú principal.""");

        opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion) {
        case 1:
            System.out.println("Has elegido la opción \"Ver Jugadores\".");
            break;
        case 2:
            System.out.println("Has elegido la opción \"Añadir jugador\".");
            registrarJugadorHumano(teclado);
            break;
        case 3:
            System.out.println("Has elegido la opción \"Eliminar jugador\".");
            break;
        case 4:
            System.out.println("Volver al Menu Principal.");
            break;
        }
    }

    private static void registrarJugadorHumano(Scanner teclado) {
        System.out.println("""
                Ahora debemos registrar los jugadores.
                Dime el nombre del jugador, recuerda, cada nombre es único en el sistema.
                (Si ya estás registrado, el sistema lo detectará al escribir tu nombre).""");
        String nombre = teclado.nextLine();
        JugadorGestor gestor = new JugadorGestor();
        HumanoJugador jug = new HumanoJugador(nombre);
        try {
            gestor.registrar(jug);
        } catch (IOException e) {
            System.err.println("Error al registrar el jugador: " + e);
        }

    }

    private static void elegirTipoDePartida(Scanner teclado, Juego juego) {

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
            int numRondas;

            switch (opcion) {
            case 1:
                System.out.println("Has elegido la opción \"Partida rápida\".");
                numRondas = 3;
                for (int cont = 0; cont <= numRondas; cont++) {
                    juego.jugarRonda();
                }
                break;
            case 2:
                System.out.println("Has elegido la opción \"Partida corta\".");
                numRondas = 5;
                for (int cont = 0; cont <= numRondas; cont++) {
                    juego.jugarRonda();
                }
                break;
            case 3:
                System.out.println("Has elegido la opción \"Partida normal\".");
                numRondas = 10;
                for (int cont = 0; cont <= numRondas; cont++) {
                    juego.jugarRonda();
                }
                break;
            case 4:
                System.out.println("Has elegido la opción \"Partida larga.\".");
                numRondas = 20;
                for (int cont = 0; cont <= numRondas; cont++) {
                    juego.jugarRonda();
                }
                break;
            case 5:
                System.out.println("Has elegido la opción  \"Volver al menu de inicio.\"");
                salir = true;
                break;
            }

        }
    }

}
