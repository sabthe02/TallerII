package sistema.logica.Excepciones;

import java.io.Serializable;
import java.lang.Exception;

public class BoletosNoDisponibles extends Exception implements Serializable{

	private String mensaje;

    public BoletosNoDisponibles(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
    
    
}
