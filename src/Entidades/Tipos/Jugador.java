package Entidades.Tipos;

import java.util.Objects;

public abstract class Jugador {
    private String nombre;
    private int puntos = 0;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }

    public abstract String responder(Pregunta p);

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
}
