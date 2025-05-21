package Entidades.Tipos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase para implementar la interfaz Entidades.Tipos.Pregunta.
 * Se implementan los metodos preguntar y evaluar respuesta.
 *
 * @author Noelia
 * @version 1.0
 */
public class MasterMindPregunta implements Pregunta {
    private int numeroSecreto;
    int intentosUsados;

    public MasterMindPregunta() {
        this.intentosUsados = 0;
    }

    @Override
    public int getNumeroIntentos() {
        return 3;
    }

    /**
     * Metodo para lanzar la pregunta.
     */
    @Override
    public void preguntar() {

        System.out.println("***Te ha tocado la pregunta MASTERMIND.*** \n" + "Tienes que decir un numero de 3 cifras y te diré cuántas cifras están correctamente ubicadas y " + "cuántas cifras están correctas pero mal ubicadas.");

        String[] arrayDeNumeros = new String[3];
        Random rnd = new Random();

        List<String> numerosDisponibles = new ArrayList<>();
        for (int cont = 0; cont < 10; cont++) {

            numerosDisponibles.add(String.valueOf(cont));
        }

        String numeroSecretoString = "";
        for (int i = 0; i < arrayDeNumeros.length; i++) {
            int indice;
            if (i == 0) {
                indice = rnd.nextInt(1, numerosDisponibles.size());
            } else {
                indice = rnd.nextInt(0, numerosDisponibles.size());
            }
            arrayDeNumeros[i] = numerosDisponibles.get(indice);
            numerosDisponibles.remove(indice);
            String numero = String.valueOf(arrayDeNumeros[i]);
            numeroSecretoString = numeroSecretoString.concat(numero);
        }
        numeroSecreto = Integer.parseInt(numeroSecretoString);

    }

    /**
     * Metodo para evaluar la respuesta recibida.
     *
     * @param respuesta
     * @return boolean
     */
    @Override
    public boolean evaluarRespuesta(String respuesta) {

        boolean acierto = false;

        char[] arrayRespuesta = respuesta.toCharArray();
        String numeroEnString = String.valueOf(numeroSecreto);
        char[] arrayNumero = numeroEnString.toCharArray();
        int contNumeroYPosicion = 0;
        int contSoloposicion = 0;

        for (int i = 0; i < arrayRespuesta.length; i++) {
            if (arrayRespuesta[i] == arrayNumero[i]) {
                contNumeroYPosicion++;
            } else {
                for (int cont = 0; cont < arrayNumero.length; cont++) {
                    if (i != cont && arrayRespuesta[i] == arrayNumero[cont]) {
                        contSoloposicion++;
                    }
                }
            }
        }

        if (acierto) {
            System.out.printf("Has acertado!! El numero secreto es %d\n", numeroSecreto);
        } else {
            intentosUsados++;
            System.out.printf("Tienes %d numeros acertados y bien colocados. Y tienes %d numeros acertados pero mal colocados. \n", contNumeroYPosicion, contSoloposicion);
            if (getNumeroIntentos() == intentosUsados) {
                System.out.printf("Se han acabado los intentos. El número es %d \n", numeroSecreto);
            }
        }

        return acierto;

    }
}