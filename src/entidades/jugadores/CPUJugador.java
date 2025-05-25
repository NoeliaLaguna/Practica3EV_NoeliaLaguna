package entidades.jugadores;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import entidades.preguntas.*;
import gestion.LogGestor;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que extiende de Jugador. Cuenta con un atributo "numRandom", que utiliza para generar un número aleatorio en el metodo respuesta.
 * La clase implementa el metodo responder que recibe de la superclase.
 *
 * @author NoeliaLaguna
 * @version 1.0
 */
public class CPUJugador extends Jugador {

    private Random numRandom = new Random();

    /**
     * Constructor de la clase CPUJugador.
     *
     * @param nombre recibe un String con el nombre del Jugador.
     */
    public CPUJugador(String nombre) {
        super(nombre);
    }

    /**
     * Metodo para responder una pregunta introducida por parametros.
     * Se utiliza un if para implementar una respuesta dependiendo de la clase de la pregunta:
     * <p>
     * - MatematicasPregunta: Realiza el cálculo de la operacion matematica recibida mediante el metodo calculate().
     * <p>
     * - GeografiaPregunta: Selecciona una opcion aleatoria entre A,B,C o D, mediante un número aleatorio que se utiliza para seleccionar el indice
     * en el ArrayList de opciones.
     * <p>
     * - MasterMindPregunta: Genera un número aleatorio de 3 cifras (entre 100 y 999).
     * <p>
     * - CronometroPregunta: Siempre devuelve como resultado 0.
     * <p>
     *
     * @param p recibe una pregunta a la que responde.
     * @return devuelve un String que será la respuesta a la pregunta.
     */
    @Override
    public String responder(Pregunta p) {
        String resultado = "";
        if (p instanceof MatematicasPregunta pregunta) {
            Calculable expresion = null;
            try {
                expresion = new ExpressionBuilder(pregunta.getOperacion()).build();
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
            int resultInt = (int) result;
            resultado = String.valueOf(resultInt);

        } else if (p instanceof GeografiaPregunta pregunta) {
            ArrayList<Character> opciones = new ArrayList<>();
            char opcion = 'A';
            for (int cont = 0; cont < pregunta.getCiudadesOpciones().size(); cont++) {
                opciones.add(opcion);
                opcion++;
            }
            int opcionElegida = numRandom.nextInt(0, pregunta.getCiudadesOpciones().size());

            resultado = String.valueOf(opciones.get(opcionElegida));

        } else if (p instanceof MasterMindPregunta) {
            resultado = String.valueOf(numRandom.nextInt(100, 1000));
        } else if (p instanceof CronometroPregunta) {
            resultado = "0";
        }

        System.out.println(resultado);
        return resultado;
    }

}
