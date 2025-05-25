package utils;

import java.time.format.DateTimeFormatter;

public class Constantes {
    public static final String[] OPERADORES = { "+", "-", "*" };
    public static final String FICHERO_JUGADORES = "JugadoresRegistrados.txt";
    public static final String FICHERO_HISTORIAL = "Historial.txt";
    public static final String FICHERO_CONFIGURACION = "config/configuracion.properties";
    public static final String FICHERO_LOGS = "Salida.log";

    public static final int RONDAS_PARTIDA_RAPIDA = 3;
    public static final int RONDAS_PARTIDA_CORTA = 5;
    public static final int RONDAS_PARTIDA_NORMAL = 10;
    public static final int RONDAS_PARTIDA_LARGA = 20;
    public static final int RADIO_TIERRA = 6371;

    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter FORMATO_FECHA_RENOMBRADO = DateTimeFormatter.ofPattern("yyyyMMdd");

}
