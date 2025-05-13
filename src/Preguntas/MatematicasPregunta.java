package Preguntas;

import Utils.Constantes;

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
    @Override
    public void preguntar() {

        //Consultar con el profe.

        int numeroOperandos = (int) (Math.random() * 5);
        double numero;
        int operador;
        StringBuilder operacion = new StringBuilder();
        while (numeroOperandos <= 1) {
            numeroOperandos = (int) (Math.random() * 5);
        }

        for (int cont = 0; cont <= numeroOperandos; cont++) {
            numero = Math.random() * 10;
            numero = (double) Math.round(numero * 100) / 100;
            if (cont < numeroOperandos) {
                operador = (int) (Math.random() * 4);
                operacion.append(numero).append(Constantes.OPERADORES[operador]);
            } else {
                operacion.append(numero);//Consultar con el profe.
            }
        }

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

        //Me he descargado la librería exp4j pero no se usarla.

        return false;
    }
}
