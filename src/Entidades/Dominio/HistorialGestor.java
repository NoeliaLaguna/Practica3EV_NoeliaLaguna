package Entidades.Dominio;

import Utils.Constantes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class HistorialGestor {

    private final Path rutaAlArchivo;

    public HistorialGestor() throws IOException {
        this.rutaAlArchivo = Paths.get(Constantes.FICHERO_JUGADORES);
        if (!Files.exists(this.rutaAlArchivo)) {
            Files.createFile(this.rutaAlArchivo);
        }
    }

    //TODO:Revisar con Oscar, he hecho todo en String para que sea mas sencillo.
    public void mostrar() throws IOException {
        List<String> listaPartidasAMostrar = listar();

        for (String partida : listaPartidasAMostrar) {
            System.out.println(partida);
        }

    }

    public List<String> listar() throws IOException {
        List<String> listaPartidasString = Files.readAllLines(rutaAlArchivo);
        for (String partida : listaPartidasString) {
            listaPartidasString.add(partida);
        }
        return listaPartidasString;
    }

    public void registrar(Juego juego) throws IOException {
        List<String> listaPartidas;
        listaPartidas = listar();
        listaPartidas.add(juego.toString());
        for (int cont = 0; cont < listaPartidas.size(); cont++) {
            String partidaARegistrar = listaPartidas.get(cont);
            Files.writeString(rutaAlArchivo, partidaARegistrar);
        }
        System.out.println("Se ha añadido la partida al historial.");
    }

    public void verRanking() {

    }
}
