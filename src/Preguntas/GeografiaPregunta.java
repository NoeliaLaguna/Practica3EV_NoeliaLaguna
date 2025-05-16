package Preguntas;

/**
 * Clase para implementar la interfaz Preguntas.Pregunta.
 * Se implementan los metodos preguntar y evaluar respuesta.
 *
 * @author Noelia
 * @version 1.0
 */
public class GeografiaPregunta implements Pregunta {
    /**
     * Metodo para lanzar la pregunta.
     */
    @Override
    public void preguntar() {

        //Preguntar al profe. Estas variables las meto en el metodo o las pongo como atributos?
        int latitud;
        int longitud;

    }

    /**
     * Metodo para evaluar la respuesta recibida.
     *
     * @param respuesta
     * @return boolean
     */
    @Override
    public boolean evaluarRespuesta(String respuesta) {
        return false;
    }

}
