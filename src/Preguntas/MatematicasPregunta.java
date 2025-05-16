package Preguntas;

import Utils.Constantes;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Random;

/**
 * Clase para implementar la interfaz Preguntas.Pregunta.
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

    @Override
    public int getNumeroIntentos() {
        return 1;
    }

    @Override
    public void preguntar() {

        //Consultar con el profe.
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
                operacion.append(numero);//Consultar con el profe.
            }
        }

        this.operacion = operacion.toString();

        System.out.println(operacion);

    }

    /**
     * Metodo para evaluar la respuesta recibida.
     *
     * @param respuesta
     * @return boolean
     */
    @Override
    public boolean evaluarRespuesta(String respuesta) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            double resultadoCorrecto = Double.parseDouble(engine.eval(operacion).toString());
            double resultadoUsuario = Double.parseDouble(respuesta);
            return resultadoUsuario == resultadoCorrecto;
        } catch (NumberFormatException | ScriptException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public String getOperacion() {
        return operacion;
    }
}
