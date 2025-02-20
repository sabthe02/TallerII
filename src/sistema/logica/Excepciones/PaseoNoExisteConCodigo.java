package sistema.logica.Excepciones;

import java.lang.Exception;

public class PaseoNoExisteConCodigo extends Exceptione {

	private String mensaje;

    public PaseoNoExisteConCodigo(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
}
