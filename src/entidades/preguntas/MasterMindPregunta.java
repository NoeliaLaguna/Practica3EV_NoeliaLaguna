package entidades.preguntas;

import gestion.ConfigGestor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase para implementar la interfaz Pregunta.
 * Se implementan los metodos preguntar y evaluar respuesta.
 *
 * @author Noelia
 * @version 1.0
 */
public class MasterMindPregunta implements Pregunta {
    private int numeroSecreto;
    int intentosUsados;

    /**
     * Constructor del objeto MastermindPregunta.
     * El constructor inicializa el atributo intentos usados a 0.
     */
    public MasterMindPregunta() {
        this.intentosUsados = 0;
    }

    /**
     * Este metodo devuelve el número de intentos.
     *
     * @return devuelve un Int con el numero de intentos.
     */
    @Override
    public int getNumeroIntentos() {
        return 3;
    }

    /**
     * Metodo para lanzar la pregunta.
     * Se declara un Array arrayDeNumeros de tipo String con longitud 3.
     * <p>
     * También se declara un ArrayList numerosDisponibles que se rellena con numeros del 1 al 9 en formato String.
     * <p>
     * De esta manera, con un for se va rellenando el arrayDeNumeros con el índice de numerosDisponibles, y se van eliminando los numeros de
     * numerosDisponibles, para que no se repitan.
     * <p>
     * Para evitar, que el primer número añadido sea un 0, se comprueba si el índice es 0, los numeros que se generan son de 1 a 9, si no, de 0 a 9.
     * <p>
     * Después se hace una concatenacion de los String para formar un unico String con el número, y se hace un casteo a int, que se almacena en el
     * atributo de la clase numeroSecreto.
     * <p>
     * Si el modo depuracion está activado, además, se muestra el número secreto por pantalla.
     */
    @Override
    public void preguntar() {
        System.out.println("***Te ha tocado la pregunta MASTERMIND.*** \n"
                + "Tienes que decir un numero de 3 cifras y te diré cuántas cifras están correctamente ubicadas y "
                + "cuántas cifras están correctas pero mal ubicadas.");

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
        if (ConfigGestor.getConfig().isDepuracion()) {
            System.out.printf("El numero secreto es: %d\n", numeroSecreto);
        }

    }

    /**
     * Metodo para evalúar la respuesta recibida.
     * Este metodo transforma la respuesta recibida en un array de char. Hace lo mismo con el númeroSecreto.
     * Se declara una variable booleana acierto, y se inicializa en false.
     * <p>
     * Inicializa dos contadores a 0:
     * <p>
     * - contNumeroYPosicion, que indica que el número es correcto y en la posicion correcta.
     * <p>
     * - contSoloNumero, que indica que el número es correcto, pero la posicion es incorrecta.
     * <p>
     * Con un for se actualizan los contadores, recorriendo ambos arrays.
     * Primero con un if, se detecta si número y posicion son iguales, si no es asi, se recorre el arrayNumero por cada una de las posiciones de
     * arrayRespuesta y se modifica en contSoloNumero.
     * Si contNumeroYPosicion tiene el mismo valor que la longitud del arrayNumero, se modifica la variable acierto a true.
     * <p>
     * En caso de que no se haya acertado, se indica en número de cada contador y además, se suma 1 al atributo intentosUsados.
     * Si intentosUsados es igual a getNumeroIntentos(), se muestra el mensaje de que se han agotado los intentos y se muestra la respuesta correcta.
     *
     * @param respuesta Se recibe una respuesta a la pregunta en tipo String
     * @return boolean Se devuelve el valor de la variable acierto.
     */
    @Override
    public boolean evaluarRespuesta(String respuesta, boolean tieneComodinDisponible) {

        boolean acierto = false;

        char[] arrayRespuesta = respuesta.toCharArray();
        String numeroEnString = String.valueOf(numeroSecreto);
        char[] arrayNumero = numeroEnString.toCharArray();
        int contNumeroYPosicion = 0;
        int contSoloNumero = 0;

        for (int i = 0; i < arrayRespuesta.length; i++) {
            if (arrayRespuesta[i] == arrayNumero[i]) {
                contNumeroYPosicion++;
                if (contNumeroYPosicion == arrayNumero.length) {
                    acierto = true;
                }
            } else {
                for (int cont = 0; cont < arrayNumero.length; cont++) {
                    if (i != cont && arrayRespuesta[i] == arrayNumero[cont]) {
                        contSoloNumero++;
                    }
                }
            }
        }

        if (acierto) {
            System.out.printf("Has acertado!! El numero secreto es %d\n", numeroSecreto);
        } else {
            intentosUsados++;
            System.out.printf("Tienes %d numeros acertados y bien colocados. Y tienes %d numeros acertados pero mal colocados. \n",
                    contNumeroYPosicion, contSoloNumero);
            if (getNumeroIntentos() == intentosUsados) {
                System.out.printf("Se han acabado los intentos. El número es %d \n", numeroSecreto);
            }
        }

        return acierto;

    }
}