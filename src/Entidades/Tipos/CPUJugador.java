package Entidades.Tipos;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

import java.util.Random;

public class CPUJugador extends Jugador {

    private Random rnd = new Random();

    public CPUJugador(String nombre) {
        super(nombre);
    }

    @Override
    public String responder(Pregunta p) {
        String resultado = "";
        if (p instanceof MatematicasPregunta pregunta) {
            Calculable e = null;
            try {
                e = new ExpressionBuilder(pregunta.getOperacion()).build();
            } catch (UnknownFunctionException ex) {
                System.err.println("Función desconocida, no es posible calcular." + ex);
                ex.printStackTrace();
            } catch (UnparsableExpressionException ex) {
                System.err.println("No es posible parsear la funcion. " + ex);
                ex.printStackTrace();
            }
            double result = e.calculate();
            int resultInt = (int) result;
            System.out.printf("El resultado es %d", resultInt);

        } else if (p instanceof GeografiaPregunta pregunta) {
            //TODO: Implementar la respuesta de la CPU.

        } else if (p instanceof MasterMindPregunta pregunta) {
            resultado = String.valueOf(rnd.nextInt(100, 1000));
        } else if (p instanceof CronometroPregunta pregunta) {
            return "0";
        }

        System.out.println(resultado);
        return resultado;
    }

}
