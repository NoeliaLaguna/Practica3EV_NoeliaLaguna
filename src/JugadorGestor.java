import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JugadorGestor {

    public void registrar(Jugador jug) throws IOException {
        List<String> listaJugadores;

        Path rutaAlArchivo = Paths.get("JugadoresRegistrados.txt"); //Revisar esto, da error de accessDeniedException

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

    public static void mostrar() throws IOException {

        Path rutaAlArchivo = Paths.get("JugadoresRegistrados.txt"); //Revisar esto, da error de accessDeniedException
        if (!Files.exists(rutaAlArchivo)) {
            Files.createFile(rutaAlArchivo);
        }

        List<String> listaJugadores = Files.readAllLines(rutaAlArchivo);
        for (String nombreJugador : listaJugadores) {
            System.out.println("\n" + nombreJugador + "\n");
        }

    }

    public void eliminar(Jugador jug) {

    }

    public List<HumanoJugador> listar() {
        List<HumanoJugador> jugadoresHumanos = new ArrayList<>();

        return jugadoresHumanos;
    }
}
