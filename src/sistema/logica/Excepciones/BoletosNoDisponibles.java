package sistema.logica.Excepciones;

import java.lang.Exception;

public class BoletosNoDisponibles extends Exception {

	private String mensaje;

    public BoletosNoDisponibles(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
    
    
}
