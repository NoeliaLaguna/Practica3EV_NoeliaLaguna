package gestion;

import entidades.tipos.Configuracion;
import utils.Constantes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase final que se utiliza para la gestion de la configuracion del juego.
 * Se utilizan atributos y metodos estáticos.
 * Se implementan los metodos: leer(), comprobarFicheroExiste(), getConfig(), cambiarConfigAString() y guardar().
 *
 * @author Noelia
 * @version 1.0
 */
public final class ConfigGestor {

    private static final Path rutaAlArchivoConfig = Paths.get(Constantes.FICHERO_CONFIGURACION);
    private static final Configuracion CONFIG = new Configuracion();

    private static void comprobarFicheroExiste() throws IOException {

        if (!Files.exists(rutaAlArchivoConfig)) {
            Files.createFile(rutaAlArchivoConfig);
        }
    }

    public static void leer() throws IOException {
        comprobarFicheroExiste();
        List<String> listaConfiguracion = Files.readAllLines(rutaAlArchivoConfig);
        Map<String, String> configuraciones = new HashMap<>();
        String key;
        String valor;
        for (String configSeparar : listaConfiguracion) {
            String[] configYValor = configSeparar.split("=");
            if (configYValor.length == 2) {
                key = configYValor[0];
                valor = configYValor[1];
                configuraciones.put(key, valor);
            }
        }

        CONFIG.setDepuracion((Boolean.parseBoolean(configuraciones.get("depuracion"))));

    }

    /**
     * @return Devuelve la configuración actual
     */
    public static Configuracion getConfig() {
        return CONFIG;
    }

    /**
     * @return
     */
    private static String cambiarConfigAString() {
        String resultado = "";
        resultado += String.format("depuracion=%s", CONFIG.isDepuracion() ? "true" : "false");
        return resultado;
    }

    public static void guardar() throws IOException {
        comprobarFicheroExiste();
        String configEnString = cambiarConfigAString();
        Files.writeString(rutaAlArchivoConfig, configEnString);
    }
}
