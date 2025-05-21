package Entidades.Tipos;

import java.time.LocalTime;
import java.util.Random;

/**
 * Clase para implementar la interfaz Entidades.Tipos.Pregunta.
 * Se implementan los metodos preguntar y evaluar respuesta.
 *
 * @author Noelia
 * @version 1.0
 */
public class CronometroPregunta implements Pregunta {

    int segundos;
    private LocalTime inicio;
    private LocalTime fin;
    private final long margen = 500;

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

        // evaluacion
        long millisRespuesta = Long.parseLong(respuesta);

        System.out.printf("Has pulsado en %.2f segundos\n", millisRespuesta / 1000);

        //TODO: EVALUAR EL TIEMPO CON EL MARGEN DE ERROR (± 0.5 segundos).

        return segundos - millisRespuesta == Math.abs(500);

    }

    public int getSegundos() {
        return segundos;
    }

    public long getMargen() {
        return margen;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    public LocalTime getFin() {
        return fin;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }
}
