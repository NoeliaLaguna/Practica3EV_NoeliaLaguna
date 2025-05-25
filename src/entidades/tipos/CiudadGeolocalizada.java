package entidades.tipos;

/**
 * Clase para crear un objeto de Ciudad a partir del fichero ciudades.csv.
 * Solo contiene atributos y metodos get.
 *
 * @author Noelia
 * @version 1.0
 */
public class CiudadGeolocalizada {
    private String nombre;
    private final double lat;
    private final double lng;

    /**
     * Constructor de CiudadGeolocalizada.
     *
     * @param nombre recibe el nombre de la ciudad en tipo String.
     * @param lat    recibe la latitud de la ciudad en tipo double.
     * @param lng    recibe la longitud de la ciudad en tipo double.
     */
    public CiudadGeolocalizada(String nombre, double lat, double lng) {
        this.nombre = nombre;
        this.lat = lat;
        this.lng = lng;
    }

    public String getNombre() {
        return nombre;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
