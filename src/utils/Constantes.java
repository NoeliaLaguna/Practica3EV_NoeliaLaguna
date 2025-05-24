package utils;

import java.time.format.DateTimeFormatter;

public class Constantes {
    public static final String[] OPERADORES = { "+", "-", "*" };
    public static final String FICHERO_JUGADORES = "JugadoresRegistrados.txt";
    public static final String FICHERO_HISTORIAL = "Historial.txt";
    public static final String FICHERO_CONFIGURACION = "config/configuracion.properties.txt";
    public static final String FICHERO_LOGS = "Salida.log";

    public static final int[] TIPO_DE_PREGUNTA = { 1, 2, 3, 4 };
    public static final int RONDAS_PARTIDA_RAPIDA = 3;
    public static final int RONDAS_PARTIDA_CORTA = 5;
    public static final int RONDAS_PARTIDA_NORMAL = 10;
    public static final int RONDAS_PARTIDA_LARGA = 20;
    public static final int RADIO_TIERRA = 6371;

    public static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("[dd/MM/yyyy] [HH:mm:ss]");
    public static final DateTimeFormatter FORMATO_FECHA_RENOMBRADO = DateTimeFormatter.ofPattern("yyyyMMdd");

}
