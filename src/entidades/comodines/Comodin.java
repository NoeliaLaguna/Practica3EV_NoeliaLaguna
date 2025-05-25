package entidades.comodines;

import entidades.preguntas.Pregunta;

public interface Comodin {
    String getNombre();

    boolean sePuedeAplicar(Pregunta p);

    void aplicar(Pregunta p);
}
