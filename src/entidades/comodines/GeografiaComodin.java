package entidades.comodines;

import entidades.preguntas.GeografiaPregunta;
import entidades.preguntas.Pregunta;

public class GeografiaComodin implements Comodin {

    @Override
    public String getNombre() {
        return "Geografía";
    }

    @Override
    public boolean sePuedeAplicar(Pregunta p) {

        return p instanceof GeografiaPregunta;
    }

    @Override
    public void aplicar(Pregunta p) {
        ((GeografiaPregunta) p).preguntarConComodin();
    }
}
