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

        List<String> listaConfiguracion = Files.readAllLines(rutaAlArchivoConfig);
        Map<String, Boolean> configuraciones = new HashMap<>();
        String key = "";
        boolean valor;
        for (int cont = 0; cont < listaConfiguracion.size(); cont++) {
            String configSeparar = listaConfiguracion.get(cont);
            String[] configYValor = configSeparar.split("=");
            key = configYValor[0];
            valor = Boolean.parseBoolean(configYValor[1]);
            configuraciones.put(key, valor);
        }

        Configuracion depuracion = new Configuracion();
        depuracion.setDepuracion(configuraciones.get(key));
        return depuracion;
    }

    public void guardar(Configuracion c) {

    }
}
