import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public interface Pregunta {

    public void preguntar();

    public boolean evaluarRespuesta(String respuesta) throws UnparsableExpressionException, UnknownFunctionException;
}
