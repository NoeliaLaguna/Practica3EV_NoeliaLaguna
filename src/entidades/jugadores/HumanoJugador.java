package entidades.jugadores;

import entidades.preguntas.CronometroPregunta;
import entidades.preguntas.Pregunta;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

public class HumanoJugador extends Jugador {

    public HumanoJugador(String nombre) {
        super(nombre);
    }

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
