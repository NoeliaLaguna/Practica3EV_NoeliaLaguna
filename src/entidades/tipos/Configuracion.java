package entidades.tipos;

/**
 * Clase para crear un objeto de Configuracion a partir del fichero configuracion.properties.
 * Solo contiene atributos y metodos get y set.
 *
 * @author Noelia
 * @version 1.0
 */
public class Configuracion {
    private boolean depuracion;

    public boolean isDepuracion() {
        return depuracion;
    }

    public void setDepuracion(boolean depuracion) {
        this.depuracion = depuracion;
    }
}