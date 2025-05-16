package Preguntas;

/**
 * Clase para implementar la interfaz Preguntas.Pregunta.
 * Se implementan los metodos preguntar y evaluar respuesta.
 *
 * @author Noelia
 * @version 1.0
 */
public class MasterMindPregunta implements Pregunta {
    /**
     * Metodo para lanzar la pregunta.
     */
    @Override
    public void preguntar() {

        int numeroSecreto = (int) (Math.random() * 1000);
        String numeroEnString = String.valueOf(numeroSecreto);
        char[] arrayDeNumeros = numeroEnString.toCharArray();
        while (arrayDeNumeros.length < 3) {
            numeroSecreto = (int) (Math.random() * 1000);
            numeroEnString = String.valueOf(numeroSecreto);
            arrayDeNumeros = numeroEnString.toCharArray();
        }
        while (arrayDeNumeros[0] == arrayDeNumeros[1] || arrayDeNumeros[0] == arrayDeNumeros[2] || arrayDeNumeros[1] == arrayDeNumeros[2]) {
            numeroSecreto = (int) (Math.random() * 1000);
            numeroEnString = String.valueOf(numeroSecreto);
            arrayDeNumeros = numeroEnString.toCharArray();
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

        return false;
    }
}
