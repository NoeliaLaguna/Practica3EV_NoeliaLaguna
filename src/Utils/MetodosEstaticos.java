package Utils;

import java.util.Scanner;

public class MetodosEstaticos {
    public static boolean comprobarDigito(String opcionescrita) {
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

    public static String stringConComprobacionDigit() {
        Scanner teclado = new Scanner(System.in);
        String opcionescrita;
        do {
            opcionescrita = teclado.nextLine();
            if (!comprobarDigito(opcionescrita)) {
                System.out.println("El valor introducido no es un número. Por favor, introduce un número.");
            }
        } while (!comprobarDigito(opcionescrita));
        return opcionescrita;
    }
}
