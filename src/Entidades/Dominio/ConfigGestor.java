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

    //TODO: No tengo muy claro si lo estoy haciendo bien.

    private final Path rutaAlArchivoConfig;
    Configuracion config;

    public ConfigGestor() throws IOException {
        this.rutaAlArchivoConfig = Paths.get(Constantes.FICHERO_CONFIGURACION);
        if (!Files.exists(this.rutaAlArchivoConfig)) {
            Files.createFile(this.rutaAlArchivoConfig);
        }
        config = new Configuracion();
    }

    public Configuracion leer() throws IOException {
        return config;
    }

    public void guardar(Configuracion c) throws IOException {

        List<String> listaConfiguracion = Files.readAllLines(rutaAlArchivoConfig);
        Map<String, Boolean> configuraciones = new HashMap<>();
        String key = "";
        boolean valor;
        for (String configSeparar : listaConfiguracion) {
            String[] configYValor = configSeparar.split("=");
            key = configYValor[0];
            valor = Boolean.parseBoolean(configYValor[1]);
            configuraciones.put(key, valor);
        }

        config.setDepuracion(configuraciones.get(key));
    }
}
