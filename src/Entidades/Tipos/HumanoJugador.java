package Entidades.Tipos;

import java.util.Scanner;

public class HumanoJugador extends Jugador {

    public HumanoJugador(String nombre) {
        super(nombre);
    }

    @Override
    public String responder(Pregunta p) {
        Scanner teclado = new Scanner(System.in);
        return teclado.nextLine();
    }

}
