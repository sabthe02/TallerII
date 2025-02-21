package sistema.logica.Excepciones;

import java.lang.Exception;


public class MenorDe0 extends Exception {
	private String mensaje;

    public MenorDe0(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
}

