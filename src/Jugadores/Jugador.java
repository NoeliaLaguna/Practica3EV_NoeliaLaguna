package Jugadores;

import Preguntas.Pregunta;

public abstract class Jugador {
    private String nombre;
    private int puntos = 0;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }

    public String responder(Pregunta p) {
        String respuesta = "";
        return respuesta;
    }

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
}
