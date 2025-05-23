package Entidades.Dominio;

import Entidades.Tipos.HumanoJugador;
import Utils.Constantes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistorialGestor {

    private final Path rutaAlArchivoHistorial;

    public HistorialGestor() throws IOException {
        this.rutaAlArchivoHistorial = Paths.get(Constantes.FICHERO_HISTORIAL);
        if (!Files.exists(this.rutaAlArchivoHistorial)) {
            Files.createFile(this.rutaAlArchivoHistorial);
        }
    }

    public void mostrar() throws IOException {
        List<String> listaPartidasAMostrar = listar();

        for (String partida : listaPartidasAMostrar) {
            System.out.println(partida);
        }

    }

    public List<String> listar() throws IOException {
        List<String> listaPartidasString = Files.readAllLines(rutaAlArchivoHistorial);
        return listaPartidasString;
    }

    public void registrar(Juego juego) throws IOException {
        List<String> listaPartidas = listar();
        listaPartidas.add(juego.toString());
        Files.write(rutaAlArchivoHistorial, listaPartidas);
        System.out.println("Se ha añadido la partida al historial.");
    }

    /**
     * Este metodo utiliza el mismo fichero de historial de partidas para almacenar en un mapa cada jugador con su valor de puntos.
     * El for se encarga de leer la lista de partidas e ir actualizando cada Key con el valor sumando los puntos.
     * Después se ordena el mapa usando una lista de "Map.Entry" que permite utilizar un comparador para ordenarlos, ya que la clase HashMap no tiene
     * esta funcionalidad.
     * Por último, se itera la lista y se muestra por pantalla el ranking ordenado.
     */
    public void verRanking(List<HumanoJugador> jugadoresRegistrados) throws IOException {
        List<String> listaPartidas = listar();

        HashMap<String, Integer> contadores = new HashMap<>();

        for (String partidaASeparar : listaPartidas) {
            String[] partidaCompleta = partidaASeparar.split(",");
            for (String jugadorPuntos : partidaCompleta) {
                String[] nombreYPuntos = jugadorPuntos.split(":");
                String nombreJugador = nombreYPuntos[0].toUpperCase();
                int puntosPartida = Integer.parseInt(nombreYPuntos[1]);
                HumanoJugador jugadorAEliminar = new HumanoJugador(nombreJugador);
                if (jugadoresRegistrados.contains(jugadorAEliminar) && !nombreJugador.contains("CPU")) { //No entiendo por qué no funciona.
                    int puntosJugador = puntosPartida;
                    if (contadores.containsKey(nombreJugador)) {
                        puntosJugador += contadores.get(nombreJugador);
                    }
                    contadores.put(nombreJugador, puntosJugador);
                }
            }
        }

        List<Map.Entry<String, Integer>> listaMapaOrdenado = new ArrayList<>(contadores.entrySet());
        listaMapaOrdenado.sort(Map.Entry.<String, Integer>comparingByValue().reversed());
        for (Map.Entry<String, Integer> ranking : listaMapaOrdenado) {
            System.out.printf("%s : %d\n", ranking.getKey(), ranking.getValue());
        }

    }
}
