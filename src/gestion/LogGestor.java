package gestion;

import utils.Constantes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

/*
 * El fichero de log deberá llamarse “salida.log”, y añadirse siempre el contenido al final del mismo. El fichero de log a escribir será, de forma
 * normal, salida.log, siempre y cuando:
 *      1. Esté vacío, o
 *      2. Su última traza corresponda al día actual de ejecución.
 *
 *  Si la última traza corresponde a otro día distinto al día actual, se deberá realizar lo siguiente:
 *           • El fichero actual salida.log deberá renombrarse con “salida.log.[FECHA]”. El formato de [FECHA] será yyyyMMdd (ver ejemplo más
 *              adelante).
 *           • Se empezará a escribir en un nuevo fichero “salida.log”.
 *
 * Por ejemplo, si la última vez que ejecutaste el programa fue ayer (por ejemplo, día 15), se cogerá el fichero de log existente llamado salida
 * .log, y se renombrará con “salida.log.20250515”, y el log de la ejecución actual se volcará en un nuevo fichero “salida.log”. El propósito es
 * tener organizados los logs por días.
 *
 * */

public final class LogGestor {

    private final static Path rutaAlArchivoLogs = Paths.get(Constantes.FICHERO_LOGS);

   /* public LogGestor() throws IOException {
        this.rutaAlArchivoLogs = Paths.get(Constantes.FICHERO_LOGS);

        if (!Files.exists(this.rutaAlArchivoLogs)) {
            Files.createFile(this.rutaAlArchivoLogs);
        } /*else if (Files.size(this.rutaAlArchivoLogs) > 0) {
            //HECHO:Verificar la ultima linea.
            List<String> lineas = Files.readAllLines(rutaAlArchivoLogs);
            String ultimaLinea = lineas.getLast();
            LocalDate fechaHoy = LocalDate.now();
            String fechaHoyString = fechaHoy.format(Constantes.FORMATO_FECHA_RENOMBRADO);

            if (!ultimaLinea.startsWith(fechaHoyString)) {
                String fechaUltimaLinea = ultimaLinea.substring(1, 11); //Esto es para saltar el corchete. Por eso empieza en el 1.
                String nuevoNombre = "salida.log." + fechaUltimaLinea;
                Files.createFile(Path.of(nuevoNombre));
                Files.move(rutaAlArchivoLogs, Path.of(nuevoNombre));
                Files.createFile(this.rutaAlArchivoLogs);
            }

        }
    }*/

    private static void crearNuevoFicheroLog(String ficheroAnterior) throws IOException {
        Files.move(rutaAlArchivoLogs, Path.of(ficheroAnterior));
        Files.createFile(rutaAlArchivoLogs);
    }

    private static void log(String accion) {
        try {

            if (!Files.exists(rutaAlArchivoLogs)) {
                Files.createFile(rutaAlArchivoLogs);
            }

            LocalDate fechaHoy = LocalDate.now();
            LocalDate fechaModificacion = Files.getLastModifiedTime(rutaAlArchivoLogs).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (!fechaHoy.equals(fechaModificacion)) {
                crearNuevoFicheroLog(fechaModificacion.format(Constantes.FORMATO_FECHA_RENOMBRADO));
            }

            LocalDate fechaLog = LocalDate.now();
            LocalTime horaLog = LocalTime.now();

            String fechaLogString = fechaLog.format(Constantes.FORMATO_FECHA);
            String horaLogString = horaLog.format(Constantes.FORMATO_HORA);

            String lineaLog = String.format("[%s] [%s] : %s\n", fechaLogString, horaLogString, accion);
            Files.writeString(rutaAlArchivoLogs, lineaLog, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logError(Exception ex) {
        log(String.format("ERROR. Se ha producido un error en la aplicación: %s", ex.getMessage()));
    }

    public static void logAccion(String accion) {
        log(accion);
    }
}
