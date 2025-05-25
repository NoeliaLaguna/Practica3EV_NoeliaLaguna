package entidades.preguntas;

/**
 * Clase interfaz Pregunta que declara los metodos que se implementan en las clases que utilizan la interfaz.
 * Se declaran los metodos preguntar(), evaluarRespuesta(), getNumeroIntentos().
 *
 * @author Noelia
 * @version 1.0
 */
public interface Pregunta {

    void preguntar();

    boolean evaluarRespuesta(String respuesta, boolean tieneComodinDisponible);// throws UnparsableExpressionException, UnknownFunctionException;

    int getNumeroIntentos();
}
