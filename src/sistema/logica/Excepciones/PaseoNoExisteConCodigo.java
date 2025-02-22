package sistema.logica.Excepciones;

import java.lang.Exception;


public class PaseoNoExisteConCodigo extends Exception {
  
	private String mensaje;

    public PaseoNoExisteConCodigo(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
}
