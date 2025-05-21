package Entidades.Dominio;

import Utils.Constantes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
        return listaPartidasString;
    }

    public void registrar(Juego juego) throws IOException {
        List<String> listaPartidas = listar();
        listaPartidas.add(juego.toString());
        Files.write(rutaAlArchivo, listaPartidas);
        /*for (int cont = 0; cont < listaPartidas.size(); cont++) {
            String partidaARegistrar = listaPartidas.get(cont);
            Files.writeString(rutaAlArchivo, partidaARegistrar);
        }*/
        System.out.println("Se ha añadido la partida al historial.");
    }

    public void verRanking() throws IOException {
        List<String> listaPartidas = listar();

        HashMap<String, Integer> contadores = new HashMap<>();

        // contador por nombre
        // map<string,Integer> -> vas almacenando
        // recorrer lineas

        // split por ','

        // split por ':'

        /*if(contadores.containsKey("j1")){
            contadores.put("j1", contadores.get("j1")+3);
        }
        else{
            contadores.put("j1", 3);
        }*/
        contadores.put("j1", 1);
        contadores.put("j2", 4);
        contadores.put("j3", 2);

        List<Integer> puntuaciones = new ArrayList<>(contadores.values());
        Collections.sort(puntuaciones.reversed());
        List<String> jugadoresVisitados = new ArrayList<>();
        for (Integer p : puntuaciones) {
            for (String k : contadores.keySet()) {
                if (!jugadoresVisitados.contains(k)) {
                    // escribir k y p
                    // agregar a jugadores visitados k
                }
            }
        }

        //List<Map.Entry<String,Integer>> listaParaOrdenar = new ArrayList<>(contadores.entrySet());
        //listaParaOrdenar.sort(Map.Entry.<>comparingByValue().reversed());

        // pasar de map a arraylist
        // ordenar y mostrar con posicion 1. j1 -> 3 ptos

    }
}
