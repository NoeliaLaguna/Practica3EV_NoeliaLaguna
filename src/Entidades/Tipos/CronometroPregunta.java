package Entidades.Tipos;

import java.time.LocalTime;
import java.util.Random;

/**
 * Clase para implementar la interfaz Pregunta.
 * Se implementan los metodos preguntar y evaluar respuesta.
 *
 * @author NoeliaLaguna
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

    //TODO:Esto no lo hace bien a veces no se muestra el mensaje al pulsar enter, REVISAR.

    /**
     * Metodo para lanzar la pregunta por pantalla.
     * En este caso se genera un número aleatorio del 1 al 5 que se le asigna al atributo "segundos".
     * Después muestra por pantalla el mensaje al usuario.
     */
    @Override
    public void preguntar() {
        Random segundosRndm = new Random();
        this.segundos = segundosRndm.nextInt(1, 6);
        System.out.printf("Debes pulsar ENTER una vez. Una vez pulses ENTER, tienes que esperar %d segundos y pulsar ENTER de nuevo. \n"
                + "Yo contaré el tiempo y si aciertas te doy los puntos. Tienes un margen de error de ± 0.5 segundos.\n", segundos);
    }

    /**
     * Metodo para evaluar una respuesta recibida en tipo String.
     * En este caso se evalua si los milisegundos recibidos por parametros son iguale al atributo segundos de esta clase, con un margen de error de
     * ±0.5 segundos.
     * <p>
     *
     * @param respuesta Se recibe un String el cual se evalua en el metodo para saber si es correcto o no.
     * @return boolean Si la respuesta recibida en parametros es correcta se devuelve True, o si no es asi, False.
     */
    @Override
    public boolean evaluarRespuesta(String respuesta) {

        // evaluacion
        long millisRespuesta = Long.parseLong(respuesta);

        System.out.printf("Has pulsado en %.2f segundos\n", millisRespuesta / 1000d);

        //TODO: EVALUAR EL TIEMPO CON EL MARGEN DE ERROR (± 0.5 segundos).

        if ((segundos * 1000L) - millisRespuesta <= Math.abs(500)) {
            System.out.println("Te llevas el punto!!");
        } else {
            System.out.println("Lo siento, te has pasado por mas de 0.5 segundos, no te llevas el punto.");
        }

        return (segundos * 1000L) - millisRespuesta <= Math.abs(500);

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
