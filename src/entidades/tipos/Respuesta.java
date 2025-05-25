package entidades.tipos;

public class Respuesta {
    private final boolean acierto;
    private final String respuestaCorrecta;

    public boolean isAcierto() {
        return acierto;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public Respuesta(boolean acierto, String respuestaCorrecta) {
        this.acierto = acierto;
        this.respuestaCorrecta = respuestaCorrecta;
    }
}
