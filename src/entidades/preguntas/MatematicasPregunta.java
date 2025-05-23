package entidades.preguntas;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import gestion.ConfigGestor;
import gestion.LogGestor;
import utils.Constantes;

import java.util.Random;

/**
 * Clase para implementar la interfaz Entidades.Tipos.Pregunta.
 * Se implementan los metodos preguntar y evaluar respuesta.
 *
 * @author Noelia
 * @version 1.0
 */
public class MatematicasPregunta implements Pregunta {
    /**
     * Metodo para lanzar la pregunta.
     */
    private String operacion;
    private int respuestaCorrecta;

    @Override
    public int getNumeroIntentos() {
        return 1;
    }

    @Override
    public void preguntar() {

        Random numeroRandom = new Random();
        int numeroOperandos = numeroRandom.nextInt(4, 9);
        int numero;
        int operador;
        StringBuilder operacion = new StringBuilder();

        for (int cont = 0; cont < numeroOperandos; cont++) {
            numero = numeroRandom.nextInt(2, 13);
            if (cont < (numeroOperandos - 1)) {
                operador = numeroRandom.nextInt(0, 3);
                operacion.append(numero).append(Constantes.OPERADORES[operador]);
            } else {
                operacion.append(numero);
            }
        }

        this.operacion = operacion.toString();

        Calculable e = null;
        try {
            e = new ExpressionBuilder(this.operacion).build();
        } catch (UnknownFunctionException ex) {
            System.err.println("Función desconocida, no es posible calcular." + ex);
            LogGestor.logError(ex);
            ex.printStackTrace();
        } catch (UnparsableExpressionException ex) {
            System.err.println("No es posible parsear la funcion. " + ex);
            LogGestor.logError(ex);
            ex.printStackTrace();
        }

        double result = e.calculate();
        this.respuestaCorrecta = (int) result;

        System.out.println(operacion);

        /*
         * si modo depuracion
         * sout(this.respuestaCorrecta)
         * */
        if (ConfigGestor.getConfig().isDepuracion()) {
            System.out.printf("Le respuesta es: %d\n", respuestaCorrecta);
        }

    }

    /**
     * Metodo para evaluar la respuesta recibida.
     *
     * @param respuesta
     * @return boolean
     */
    @Override
    public boolean evaluarRespuesta(String respuesta) {
        //He cambiado esto para hacerlo con la librería exp4j porque el otro código fallaba.
        int respuestaInt = Integer.parseInt(respuesta);
        if (respuestaInt == this.respuestaCorrecta) {
            System.out.printf("\nCorrecto!! el resultado es %d", this.respuestaCorrecta);
        } else {
            System.out.printf("\nIncorrecto!! el resultado es %d", this.respuestaCorrecta);
        }

        return respuestaInt == this.respuestaCorrecta;
    }

    public String getOperacion() {
        return operacion;
    }
}
