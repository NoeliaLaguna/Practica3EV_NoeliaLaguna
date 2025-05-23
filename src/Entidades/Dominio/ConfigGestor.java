package Entidades.Dominio;

import Utils.Constantes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigGestor {

    private final Path rutaAlArchivoConfig;

    public ConfigGestor() throws IOException {
        this.rutaAlArchivoConfig = Paths.get(Constantes.FICHERO_CONFIGURACION);
        if (!Files.exists(this.rutaAlArchivoConfig)) {
            Files.createFile(this.rutaAlArchivoConfig);
        }
    }

    public Configuracion leer() throws IOException {
        Configuracion config = new Configuracion();

        List<String> listaConfiguracion = Files.readAllLines(rutaAlArchivoConfig);
        Map<String, String> configuraciones = new HashMap<>();
        String key = "";
        String valor = "";
        for (String configSeparar : listaConfiguracion) {
            String[] configYValor = configSeparar.split("=");
            if (configYValor.length == 2) {
                key = configYValor[0];
                valor = configYValor[1];
                configuraciones.put(key, valor);
            }
        }

        config.setDepuracion(Boolean.parseBoolean(configuraciones.get("depuracion")));

        return config;
    }

    private String serializar(Configuracion config) {
        String resultado = "";
        resultado += String.format("depuracion=%s", config.isDepuracion() ? "true" : "false");
        return resultado;
    }

    public void guardar(Configuracion config) throws IOException {
        String configuracionSerializada = serializar(config);
        Files.writeString(rutaAlArchivoConfig, configuracionSerializada);
    }
}
