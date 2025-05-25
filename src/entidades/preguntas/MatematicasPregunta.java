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
 * Clase para implementar la interfaz Pregunta.
 * Se implementan los metodos preguntar y evaluar respuesta.
 *
 * @author Noelia
 * @version 1.0
 */
public class MatematicasPregunta implements Pregunta {

    private String operacion = "";
    private int respuestaCorrecta;

    @Override
    public int getNumeroIntentos() {
        return 1;
    }

    /**
     * Metodo para lanzar la pregunta.
     * <p>
     * Se genera un número int aleatorio entre 4 y 9 que será el número de operandos de la operacion.
     * <p>
     * Después se utiliza un for para rellenar el atributo operación.
     * <p>
     * Dentro del for se genera un número aleatorio para el operando y otro número aleatorio que será el índice de la lista de operadores
     * disponibles en Constantes. Este número y operador se concatenarán al String operacion.
     * <p>
     * Se utiliza un if, para saber si la posicion en la operacion es la última, y en la última posicion no generar operador, solo el último número.
     * <p>
     * Después se utiliza la libreria exp4j para formar una expresion Calculable (que es una interfaz).
     * <p>
     * Esta expresion Calculable tiene el metodo calculate() que calcula el resultado de la expresion.
     * <p>
     * Tras calcular el resultado, este se asigna al atributo respuestaCorrecta.
     * <p>
     * Si el modo depuracion está activado (true), se muestra el resultado por pantalla.
     */
    @Override
    public void preguntar() {

        Random numeroRandom = new Random();
        int numeroOperandos = numeroRandom.nextInt(4, 9);
        int numero;
        int operador;

        for (int cont = 0; cont < numeroOperandos; cont++) {
            numero = numeroRandom.nextInt(2, 13);
            if (cont < (numeroOperandos - 1)) {
                operador = numeroRandom.nextInt(0, 3);
                this.operacion = this.operacion.concat(String.valueOf(numero)).concat(Constantes.OPERADORES[operador]);
            } else {
                this.operacion = this.operacion.concat(String.valueOf(numero));
            }
        }

        Calculable expresion = null;
        try {
            expresion = new ExpressionBuilder(this.operacion).build();
        } catch (UnknownFunctionException ex) {
            System.err.println("Función desconocida, no es posible calcular." + ex);
            LogGestor.logError(ex);
            ex.printStackTrace();
        } catch (UnparsableExpressionException ex) {
            System.err.println("No es posible parsear la funcion. " + ex);
            LogGestor.logError(ex);
            ex.printStackTrace();
        }

        double result = 0;
        if (expresion != null) {
            result = expresion.calculate();
        }
        this.respuestaCorrecta = (int) result;

        System.out.println(operacion);

        if (ConfigGestor.getConfig().isDepuracion()) {
            System.out.printf("Le respuesta es: %d\n", respuestaCorrecta);
        }

    }

    /**
     * Metodo para evaluar la respuesta recibida.
     * <p>
     * Recibe una respuesta por parametros que castea a int.
     * <p>
     * Después compara la respuesta recibida con el atributo respuestaCorrecta.
     *
     * @param respuesta es la respuesta en tipo String que se debe evaluar.
     * @return boolean Si respuesta y respuestaCorrecta son iguales devuelve true, si no, devuelve false.
     */
    @Override
    public boolean evaluarRespuesta(String respuesta, boolean tieneComodinDisponible) {

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
