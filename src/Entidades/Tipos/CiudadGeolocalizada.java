package Entidades.Tipos;

public class CiudadGeolocalizada {
    private String nombre;
    private final double lat;
    private final double lng;

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
