package utils;

import java.util.Scanner;

public class MetodosEstaticos {
    public static boolean esUnNumero(String opcionescrita) {
        boolean esUnNumero = false;
        opcionescrita.toCharArray();
        for (int cont = 0; cont < opcionescrita.length(); cont++) {
            char caracter = opcionescrita.charAt(cont);
            if (Character.isDigit(caracter)) {
                esUnNumero = true;
            }

        }
        return esUnNumero;
    }

    /**
     * @param opcionescrita
     * @return boolean tieneDecimales
     */
    public static boolean tieneDecimales(String opcionescrita) {
        boolean tieneDecimales = false;
        if (opcionescrita.contains(".") || opcionescrita.contains(",")) {
            tieneDecimales = true;
        }
        return !tieneDecimales;
    }

    public static String stringConComprobacionDigitNoDecimales() {
        Scanner teclado = new Scanner(System.in);
        String opcionescrita;
        do {
            opcionescrita = teclado.nextLine();
            if (!esUnNumero(opcionescrita)) {
                System.out.println("El valor introducido no es un número. Por favor, introduce un número.");
            }

            if (!tieneDecimales(opcionescrita)) {
                System.out.println("El valor introducido tiene decimales. Por favor, introduce un número entero.");
            }

        } while (!esUnNumero(opcionescrita) || !tieneDecimales(opcionescrita));
        return opcionescrita;
    }

    public static double redondearADosDecimales(double numero) {
        numero = Math.round(numero * 100.0) / 100.0;
        return numero;
    }
}