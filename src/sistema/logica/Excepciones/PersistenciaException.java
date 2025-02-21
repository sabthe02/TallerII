
package sistema.logica.Excepciones;

import java.lang.Exception;

public class PersistenciaException extends Exception {

	private String mensaje;

    public PersistenciaException(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
    
    
}
