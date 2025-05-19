package Entidades.Dominio;

import Entidades.Tipos.HumanoJugador;
import Entidades.Tipos.Jugador;
import Utils.Constantes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JugadorGestor {

    private final Path rutaAlArchivo;

    public JugadorGestor() throws IOException {
        this.rutaAlArchivo = Paths.get(Constantes.FicheroJugadores); //Revisar esto, da error de accessDeniedException
        if (!Files.exists(this.rutaAlArchivo)) {
            Files.createFile(this.rutaAlArchivo);
        }
    }

    public void registrar(Jugador jug) throws IOException {
        List<String> listaJugadores;

        if (!Files.exists(rutaAlArchivo)) {
            Files.createFile(rutaAlArchivo);
        }

        listaJugadores = Files.readAllLines(rutaAlArchivo);

        if (!listaJugadores.contains(jug.getNombre())) {
            listaJugadores.add(jug.getNombre());
            Files.write(rutaAlArchivo, listaJugadores);
            System.out.println("Se ha añadido el jugador correctamente.");
        } else {
            System.err.println("Este jugador ya ha sido añadido.");
        }

    }

    public void mostrar() throws IOException {

        ArrayList<HumanoJugador> jugadores = listar();
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), jugadores.get(i).getNombre());
        }

    }

    public void eliminar(Jugador jug) {

    }

    public boolean sinJugadores() throws IOException {
        return listar().isEmpty();
    }

    public ArrayList<HumanoJugador> listar() throws IOException {
        ArrayList<HumanoJugador> jugadores = new ArrayList<>();
        List<String> listaJugadores = Files.readAllLines(rutaAlArchivo);
        for (String nombreJugador : listaJugadores) {
            HumanoJugador j = new HumanoJugador(nombreJugador);
            jugadores.add(j);

        }
        return jugadores;
    }
}
