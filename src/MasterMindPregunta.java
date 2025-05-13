public class MasterMindPregunta implements Pregunta {

    @Override
    public void preguntar() {

        int numeroSecreto = (int) (Math.random() * 1000);
        String numeroEnString = String.valueOf(numeroSecreto);
        char[] arrayDeNumeros = numeroEnString.toCharArray();
        while (arrayDeNumeros.length < 3) {
            numeroSecreto = (int) (Math.random() * 1000);
            numeroEnString = String.valueOf(numeroSecreto);
            arrayDeNumeros = numeroEnString.toCharArray();
        }
        while (arrayDeNumeros[0] == arrayDeNumeros[1] || arrayDeNumeros[0] == arrayDeNumeros[2] || arrayDeNumeros[1] == arrayDeNumeros[2]) {
            numeroSecreto = (int) (Math.random() * 1000);
            numeroEnString = String.valueOf(numeroSecreto);
            arrayDeNumeros = numeroEnString.toCharArray();
        }

    }

    @Override
    public boolean evaluarRespuesta(String respuesta) {
        return false;
    }
}
