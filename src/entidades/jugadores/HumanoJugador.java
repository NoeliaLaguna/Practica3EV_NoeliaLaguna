package entidades.jugadores;

import entidades.preguntas.CronometroPregunta;
import entidades.preguntas.Pregunta;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Clase que extiende de Jugador.
 * La clase implementa el metodo responder que recibe de la superclase.
 *
 * @author NoeliaLaguna
 * @version 1.0
 */
public class HumanoJugador extends Jugador {

    /**
     * Constructor de la clase HumanoJugador.
     *
     * @param nombre recibe un String con el nombre del Jugador.
     */
    public HumanoJugador(String nombre) {
        super(nombre);
    }

    /**
     * Metodo para responder una pregunta introducida por parametros.
     * Se utiliza un if para implementar una respuesta dependiendo de la clase de la pregunta:
     * - CronometroPregunta: Espera una entrada de teclado y almacena el momento en el que se pulsa. Después espera de nuevo una entrada de teclado
     * y almacena de nuevo el momento. Finalmente, calcula la duracion entre los dos momentos y lo almacena en una variable de tipo long en
     * milisegundos, y transforma ese valor a String.
     * - En cualquier otro caso, recibe una entrada de teclado y la devuelve como respuesta.
     *
     * @param p recibe la pregunta a la que responder.
     * @return Devuelve un String con la respuesta a la pregunta.
     */
    @Override
    public String responder(Pregunta p) {

        Scanner teclado = new Scanner(System.in);
        if (p instanceof CronometroPregunta) {

            teclado.nextLine();
            System.out.println("");

            System.out.print("Inicio del contador");

            ((CronometroPregunta) p).setInicio(LocalTime.now());

            teclado.nextLine();
            System.out.println("Fin del contador.");
            ((CronometroPregunta) p).setFin(LocalTime.now());

            long milisegundos = Duration.between(((CronometroPregunta) p).getInicio(), ((CronometroPregunta) p).getFin()).toMillis();
            return String.valueOf(milisegundos);
        } else {
            return teclado.nextLine();
        }

    }

}
