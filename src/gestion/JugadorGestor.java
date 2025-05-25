package gestion;

import entidades.jugadores.HumanoJugador;
import entidades.jugadores.Jugador;
import utils.Constantes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para implementar la interfaz Pregunta.
 * Se implementan los metodos preguntar y evaluar respuesta.
 *
 * @author Noelia
 * @version 1.0
 */
public class JugadorGestor {

    private final Path rutaAlArchivo;

    public JugadorGestor() throws IOException {
        this.rutaAlArchivo = Paths.get(Constantes.FICHERO_JUGADORES);
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
            LogGestor.logAccion("Jugador añadido " + jug.getNombre().toUpperCase());
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

    public void eliminar(Jugador jug) throws IOException {

        List<String> listaJugadores;

        listaJugadores = Files.readAllLines(rutaAlArchivo);

        if (listaJugadores.contains(jug.getNombre())) {
            listaJugadores.remove(jug.getNombre());
            Files.write(rutaAlArchivo, listaJugadores);
            System.out.println("Se ha eliminado el jugador correctamente.");
            LogGestor.logAccion("Jugador eliminado " + jug.getNombre().toUpperCase());
        } else {
            System.err.println("No se ha encontrado al jugador.");
        }

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
