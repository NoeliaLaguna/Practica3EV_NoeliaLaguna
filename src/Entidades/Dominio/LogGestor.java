package Entidades.Dominio;

import Utils.Constantes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogGestor {

    private final Path rutaAlArchivoLogs;

    public LogGestor() throws IOException {
        this.rutaAlArchivoLogs = Paths.get(Constantes.FICHERO_LOGS);
        if (!Files.exists(this.rutaAlArchivoLogs)) {
            Files.createFile(this.rutaAlArchivoLogs);
        }
    }

    public void logear(String accion) throws IOException {
        // fichero log para cada día => el nombre del fichero tiene que ser 2025-05-22.log
        Files.writeString(rutaAlArchivoLogs, accion);

    }
}
