package entidades.preguntas;

public interface Pregunta {

    public void preguntar();

    public boolean evaluarRespuesta(String respuesta);// throws UnparsableExpressionException, UnknownFunctionException;

    public int getNumeroIntentos();
}
