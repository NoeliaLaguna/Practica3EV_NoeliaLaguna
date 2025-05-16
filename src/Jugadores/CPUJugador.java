package Jugadores;

import Preguntas.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Random;

public class CPUJugador extends Jugador {

    private Random rnd = new Random();

    public CPUJugador(String nombre) {
        super(nombre);
    }

    @Override
    public String responder(Pregunta p) {
        String resultado = "";
        if (p instanceof MatematicasPregunta) {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            try {
                double resultadoCorrecto = Double.parseDouble(engine.eval(((MatematicasPregunta) p).getOperacion()).toString());
                resultado = String.valueOf(resultadoCorrecto);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        } else if (p instanceof GeografiaPregunta) {

        } else if (p instanceof MasterMindPregunta) {
            resultado = String.valueOf(rnd.nextInt(100, 1000));
        } else if (p instanceof CronometroPregunta) {
            return "0";
        }

        System.out.println(resultado);
    }

}
