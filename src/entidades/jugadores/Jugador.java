package entidades.jugadores;

import entidades.comodines.Comodin;
import entidades.preguntas.Pregunta;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Clase abstracta que es de superclase de CPUJugador y HumanoJugador.
 * Esta clase dicta los metodos y atributos principales de ambas subclases.
 * Los atributos son: int puntos y String nombre. Y los metodos principales: puntuar() y responder().
 *
 * @author NoeliaLaguna
 * @version 1.0
 */
public abstract class Jugador {
    private String nombre;
    private int puntos = 0;
    private ArrayList<Comodin> comodines;

    /**
     * Constructor del objeto.
     * Este constructor recibe un String con el nombre y asigna los puntos a 0.
     *
     * @param nombre String que recibe con el nombre del jugador.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
        comodines = new ArrayList<>();
    }

    /**
     * Metodo abstracto. El codigo del metodo se implementa en las subclases.
     *
     * @param p recibe una pregunta a la que responde.
     * @return devuelve un String que es la respuesta a la pregunta recibida por parametros.
     */
    public abstract String responder(Pregunta p);

    /**
     * Metodo para modificar el atributo "puntos" y añadir 1 punto.
     */
    public void puntuar() {
        puntos++;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    @Override
    public String toString() {
        return this.nombre.toUpperCase();
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == null || getClass() != otro.getClass())
            return false;
        Jugador jugador = (Jugador) otro;
        return Objects.equals(nombre, jugador.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    public void setComodines(ArrayList<Comodin> comodines) {
        this.comodines = comodines;
    }

    public ArrayList<Comodin> getComodines() {
        return comodines;
    }
}
