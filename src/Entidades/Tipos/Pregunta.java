package Entidades.Tipos;

import Entidades.Dominio.Configuracion;

public interface Pregunta {

    public void preguntar(Configuracion config);

    public boolean evaluarRespuesta(String respuesta);// throws UnparsableExpressionException, UnknownFunctionException;

    public int getNumeroIntentos();
}
