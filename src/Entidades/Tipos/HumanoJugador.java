package Entidades.Tipos;

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
            ((CronometroPregunta) p).setInicio(LocalTime.now());
            System.out.println("Inicio del contador...");

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
