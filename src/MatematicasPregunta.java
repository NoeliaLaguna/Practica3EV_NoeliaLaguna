public class MatematicasPregunta implements Pregunta {

    @Override
    public void preguntar() {

        //Consultar con el profe.

        int numeroOperandos = (int) (Math.random() * 5);
        double numero;
        int operador;
        StringBuilder operacion = new StringBuilder();
        while (numeroOperandos <= 1) {
            numeroOperandos = (int) (Math.random() * 5);
        }

        for (int cont = 0; cont <= numeroOperandos; cont++) {
            numero = Math.random() * 10;
            numero = (double) Math.round(numero * 100) / 100;
            if (cont < numeroOperandos) {
                operador = (int) (Math.random() * 4);
                operacion.append(numero).append(Constantes.OPERADORES[operador]);
            } else {
                operacion.append(numero);//Consultar con el profe.
            }
        }

        System.out.println(operacion);

    }

    @Override
    public boolean evaluarRespuesta(String respuesta) {

        //Me he descargado la librería exp4j pero no se usarla.

        return false;
    }
}
