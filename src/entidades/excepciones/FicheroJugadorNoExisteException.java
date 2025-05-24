package entidades.excepciones;

import java.io.IOException;

public class FicheroJugadorNoExisteException extends IOException {
    @Override
    public String getMessage() {
        return "El fichero al que intenta acceder no existe. " + super.getMessage();
    }
}
