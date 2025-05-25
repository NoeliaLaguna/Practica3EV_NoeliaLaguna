package entidades.preguntas;

import entidades.tipos.CiudadGeolocalizada;
import gestion.ConfigGestor;
import gestion.LogGestor;
import utils.Constantes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase para implementar la interfaz Pregunta.
 * Se implementan los metodos preguntar y evaluar respuesta.
 *
 * @author Noelia
 * @version 1.0
 */
public class GeografiaPregunta implements Pregunta {

    private static final int NUMERO_OPCIONES_CIUDADES = 4;
    private ArrayList<CiudadGeolocalizada> ciudades;
    private ArrayList<CiudadGeolocalizada> ciudadesOpciones;
    private char opcionCorrecta;
    CiudadGeolocalizada objetivo;
    private char ultimaRespuesta;

    /**
     * Constructor del objeto GeografiaPregunta.
     * El metodo lee el fichero "ciudades.csv" y almacena las ciudades en un arrayList.
     * Creando también objetos de "CiudadGeolocalizada" y asignandole una latitud y una longitud.
     */
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
            LogGestor.logError(ex);
            ex.printStackTrace();
        }
    }

    @Override
    public int getNumeroIntentos() {
        return 1;
    }

    /**
     * Metodo para implementar la fórmula de Haversine.
     *
     * @param val procede de la variable dLong del metodo "calcularDistancia"
     * @return devuelve un double resultado de la operación.
     */
    double haversine(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

    /**
     * Metodo que calcula la distancia aproximada en kilómetros entre dos ciudades dadas. Utilizando la latitud y longitud de ambas ciudades.
     * Para ello utiliza la fórmula de "aproximación de distancia equirectangular" y aplica la fórmula de Haversine (especificada en otro metodo),
     * para obetener una mayor precisión.
     *
     * @param c1 Corresponde a la ciudad 1.
     * @param c2 Corresponde a la ciudad 2
     * @return el metodo devuelve un double con la distancia entre ambas ciudades.
     */
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
     * Metodo para lanzar la pregunta de Geografia.
     * <p>
     * Se utiliza un Arraylist de numeros enteros que se rellena con índices de cada una de las ciudades de la lista de ciudades.
     * Se saca un número aleatorio de la lista de índices y se utiliza como índice para sacar la ciudad con dicho índice de la lista.
     * Luego ese número se elimina de la lista de índices, de manera, que ese índice no volverá a salir, asi se consigue que no se repita la ciudad
     * elegida.
     * <p>
     * La primera ciudad que se saca, se asigna al atributo "objetivo".
     * Después se utiliza un for de 0 a 4 (que es una constante) para añadir las ciudades a la lista "ciudadesOpciones".
     * <p>
     * Además, en este mismo metodo se calcula la respuesta correcta y se almacena en el atributo "opcionCorrecta".
     * Se calcula utilizando un for (que también se utiliza para mostrar las opciones por pantalla) que recorre todas las opciones y almacenando
     * la distancia más corta.
     * <p>
     * Por último, el metodo tiene un if en el que, si el modo depuracion está activado, muestra la respuesta correcta.
     */
    @Override
    public void preguntar() {
        Random rnd = new Random();
        ArrayList<Integer> indicesCiudades = new ArrayList<>();
        for (int i = 0; i < ciudades.size(); i++) {
            indicesCiudades.add(i);
        }

        int indiceObjetivo = rnd.nextInt(0, indicesCiudades.size());
        this.objetivo = ciudades.get(indiceObjetivo);
        indicesCiudades.remove((Integer) indiceObjetivo);

        this.ciudadesOpciones = new ArrayList<>();

        for (int i = 0; i < NUMERO_OPCIONES_CIUDADES; i++) {
            int indiceOpciones = rnd.nextInt(0, indicesCiudades.size());
            ciudadesOpciones.add(ciudades.get(indicesCiudades.get(indiceOpciones)));
            indicesCiudades.remove(indicesCiudades.get(indiceOpciones));
        }

        System.out.printf("Ciudad objetivo: %s\n", objetivo.getNombre());
        char opcion = 'A';
        double distanciaMinima = Double.POSITIVE_INFINITY;

        for (int cont = 0; cont < ciudadesOpciones.size(); cont++) {

            System.out.printf("\n%s) %s", opcion, ciudadesOpciones.get(cont).getNombre());
            double distanciaAObjetivo = calcularDistancia(objetivo, ciudadesOpciones.get(cont));
            if (distanciaAObjetivo < distanciaMinima) {
                distanciaMinima = distanciaAObjetivo;
                this.opcionCorrecta = opcion;
            }

            opcion++;
        }

        if (ConfigGestor.getConfig().isDepuracion()) {
            System.out.println("");
            mostrarDistanciasOpciones();
            System.out.printf("\nLa respuesta es: %s\n", opcionCorrecta);
        }

    }

    //[a],(b),c,d
    public void preguntarConComodin() {
        int posElegidaRespuestaAnterior = ultimaRespuesta - 'A';
        int posRespuestaCorrecta = opcionCorrecta - 'A';
        ArrayList<Integer> ciudadesOpcionesCopia = new ArrayList<>();

        for (int i = 0; i < ciudadesOpciones.size(); i++) {
            if (!(i == posElegidaRespuestaAnterior || i == posRespuestaCorrecta)) {
                ciudadesOpcionesCopia.add(i);
            }
        }

        int posCiudadComodin = ciudadesOpcionesCopia.get(0);

        System.out.printf("\n%s) %s", (char) ('A' + posRespuestaCorrecta), ciudadesOpciones.get(posRespuestaCorrecta).getNombre());
        System.out.printf("\n%s) %s", (char) ('A' + posCiudadComodin), ciudadesOpciones.get(posCiudadComodin).getNombre());


        /*if (ConfigGestor.getConfig().isDepuracion()) {
            System.out.println("");
            mostrarDistanciasOpciones(ciudadesOpcionesCopia);
            System.out.printf("\nLa respuesta es: %s\n", opcionCorrecta);
        }*/
    }

    /**
     * Metodo para mostrar las opciones de la pregunta, con las distancias calculadas.
     * Se ultiliza un for para recorrer la lista "ciudadesOpciones" y se calcula la distancia de cada una de ellas con el objetivo, y se muestra
     * por pantalla.
     */
    private void mostrarDistanciasOpciones() {
        char opcion = 'A';
        for (int cont = 0; cont < ciudadesOpciones.size(); cont++) {

            double distancia = calcularDistancia(this.objetivo, ciudadesOpciones.get(cont));

            System.out.printf("\n%s) %s -> %.2f", opcion, ciudadesOpciones.get(cont).getNombre(), distancia);
            opcion++;
        }
    }

    /**
     * Metodo para evaluar la respuesta recibida. Este metodo compara el char de la respuesta con el char de la opción correcta, si son iguales,
     * devuelve true. Si no, devuelve false.
     *
     * @param respuesta Se introduce la respuesta tipo String para evaluar.
     * @return boolean Si respuesta y opcionCorectaString son iguales, devuelve true. Si no, devuelve false.
     */
    @Override
    public boolean evaluarRespuesta(String respuesta, boolean tieneComodinDisponible) {
        CiudadGeolocalizada ciudadCorrecta = ciudadesOpciones.get(opcionCorrecta - 'A');

        String opcionCorrectaString = String.valueOf(opcionCorrecta);
        this.ultimaRespuesta = respuesta.charAt(0);

        if (!tieneComodinDisponible) {
            mostrarDistanciasOpciones();
        }
        if (respuesta.equalsIgnoreCase(opcionCorrectaString)) {
            System.out.printf("\nCorrecto!!! La respuesta es: %s.\n", ciudadCorrecta.getNombre());
            return true;
        } else {
            System.out.printf("\nNo es correcto. La respuesta correcta es: %s.\n", ciudadCorrecta.getNombre());
            return false;
        }

    }

    public ArrayList<CiudadGeolocalizada> getCiudades() {
        return ciudades;
    }

    public ArrayList<CiudadGeolocalizada> getCiudadesOpciones() {
        return ciudadesOpciones;
    }

    public char getOpcionCorrecta() {
        return opcionCorrecta;
    }

    public CiudadGeolocalizada getObjetivo() {
        return objetivo;
    }
}
