package Preguntas;

import Entidades.Tipos.CiudadGeolocalizada;
import Utils.Constantes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase para implementar la interfaz Preguntas.Pregunta.
 * Se implementan los metodos preguntar y evaluar respuesta.
 *
 * @author Noelia
 * @version 1.0
 */
public class GeografiaPregunta implements Pregunta {

    private ArrayList<CiudadGeolocalizada> ciudades;
    private ArrayList<CiudadGeolocalizada> ciudadesOpciones;
    private char opcionCorrecta;
    CiudadGeolocalizada objetivo;

    public GeografiaPregunta() {
        this.ciudades = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Path.of("ciudades.csv"));
            for (int i = 1; i < lineas.size(); i++) {
                String[] partes = lineas.get(i).split(",");
                String ciudad = partes[0];
                double lat = Double.parseDouble(partes[1]);
                double lng = Double.parseDouble(partes[2]);
                ciudades.add(new CiudadGeolocalizada(ciudad, lat, lng));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getNumeroIntentos() {
        return 1;
    }

    double haversine(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

    double calcularDistancia(CiudadGeolocalizada c1, CiudadGeolocalizada c2) {

        double dLat = Math.toRadians((c2.getLat() - c1.getLat()));
        double dLong = Math.toRadians((c2.getLng() - c1.getLng()));

        double lat1 = Math.toRadians(c1.getLat());
        double lat2 = Math.toRadians(c2.getLat());

        double a = haversine(dLat) + Math.cos(lat1) * Math.cos(lat2) * haversine(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return Constantes.RADIO_TIERRA * c;
    }

    /**
     * Metodo para lanzar la pregunta.
     */
    @Override
    public void preguntar() {
        Random rnd = new Random();
        ArrayList<Integer> indicesCiudades = new ArrayList<>();
        for (int i = 0; i < ciudades.size(); i++) {
            indicesCiudades.add(i);
        }

        //TODO: Las ciudades objetivo y las opciones se repiten, arreglar esto.
        int indiceObjetivo = rnd.nextInt(0, indicesCiudades.size());
        this.objetivo = ciudades.get(indiceObjetivo);
        indicesCiudades.remove((Integer) indiceObjetivo);

        this.ciudadesOpciones = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int indiceOpciones = rnd.nextInt(0, indicesCiudades.size());
            ciudadesOpciones.add(ciudades.get(indiceOpciones));
            indicesCiudades.remove((Integer) indiceOpciones);
        }

        System.out.printf("Ciudad objetivo: %s\n", objetivo.getNombre());
        char opcion = 'A';
        double distanciaMinima = Double.POSITIVE_INFINITY;

        for (int cont = 0; cont < ciudadesOpciones.size(); cont++) {
            opcion = (char) (opcion + cont);
            System.out.printf("%s) %s\n", opcion, ciudadesOpciones.get(cont).getNombre());
            double distanciaAObjetivo = calcularDistancia(objetivo, ciudadesOpciones.get(cont));
            if (distanciaAObjetivo < distanciaMinima) {
                distanciaMinima = distanciaAObjetivo;
                this.opcionCorrecta = opcion;
            }

        }

    }

    private void mostrarDistanciasOpciones() {
        char opcion = 'A';
        for (int cont = 0; cont < ciudadesOpciones.size(); cont++) {
            opcion = (char) (opcion + cont);
            double distancia = calcularDistancia(this.objetivo, ciudadesOpciones.get(cont)); //Falta redondear a 2 decimales.
            System.out.printf("%s) %s -> %f\n", opcion, ciudadesOpciones.get(cont).getNombre(), distancia);
        }
    }

    /**
     * Metodo para evaluar la respuesta recibida.
     *
     * @param respuesta
     * @return boolean
     */
    @Override
    public boolean evaluarRespuesta(String respuesta) {
        CiudadGeolocalizada ciudadCorrecta = ciudadesOpciones.get(opcionCorrecta - 'A');

        /// HECHO: Comparar respuesta con tu char correcto
        // para sacar la respuesta correcta del array de ciudades
        // teniendo el char correcto, por ejemplo C.
        // imagina que A = 56, B= 57, C = 58.. C-A = 2 -> corresponde con ciudadesOpciones.get('c'-'a')
        // siempre usa la A para restar porque la A es como tu 0
        String opcionCorrectaString = String.valueOf(opcionCorrecta);
        mostrarDistanciasOpciones();
        if (respuesta.equalsIgnoreCase(opcionCorrectaString)) {
            System.out.printf("Correcto!!! La respuesta es: %s.\n", ciudadCorrecta.getNombre());
            return true;
        } else {
            System.out.printf("No es correcto. La respuesta correcta es: %s.\n", ciudadCorrecta.getNombre());
            return false;
        }

        //TODO: Sacar las 4 ciudades de las opciones con si distancia.

    }

}
