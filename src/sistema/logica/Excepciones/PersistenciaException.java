
package sistema.logica.Excepciones;

import java.io.Serializable;
import java.lang.Exception;

public class PersistenciaException extends Exception implements Serializable{

	private String mensaje;

    public PersistenciaException(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
    
    
}
