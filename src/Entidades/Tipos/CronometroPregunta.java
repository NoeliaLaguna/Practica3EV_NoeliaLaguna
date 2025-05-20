package Entidades.Tipos;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase para implementar la interfaz Entidades.Tipos.Pregunta.
 * Se implementan los metodos preguntar y evaluar respuesta.
 *
 * @author Noelia
 * @version 1.0
 */
public class CronometroPregunta implements Pregunta {

    int segundos;
    LocalTime inicio;
    LocalTime fin;

    @Override
    public int getNumeroIntentos() {
        return 1;
    }

    /**
     * Metodo para lanzar la pregunta.
     */
    @Override
    public void preguntar() {
        Random segundosRndm = new Random();
        this.segundos = segundosRndm.nextInt(1, 6);
        System.out.printf("Debes pulsar ENTER una vez. Una vez pulses ENTER, tienes que esperar %d segundos y pulsar ENTER de nuevo. \n" + " Yo contaré el tiempo y si aciertas te doy los puntos.\n", segundos);
    }

    /**
     * Metodo para evaluar la respuesta recibida.
     *
     * @param respuesta
     * @return boolean
     */
    @Override
    public boolean evaluarRespuesta(String respuesta) {
        Scanner teclado = new Scanner(System.in);
        teclado.nextLine();
        inicio = LocalTime.now();
        System.out.println("Inicio");

        teclado.nextLine();
        fin = LocalTime.now();

        long segundosPulsacion = Duration.between(inicio, fin).toSeconds();
        long milisegundos = Duration.between(inicio, fin).toMillis();
        milisegundos = milisegundos - segundosPulsacion * 1000;

        System.out.printf("Has pulsado en %d, %d segundos\n", segundosPulsacion, milisegundos);

        //TODO: EVALUAR EL TIEMPO CON EL MARGEN DE ERROR (± 0.5 segundos).

        return false;
    }

}
