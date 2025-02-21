package sistema.logica.Excepciones;

import java.lang.Exception;

public class PaseoNoExiste extends Exception{

	private String mensaje;

    public PaseoNoExiste(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
}
