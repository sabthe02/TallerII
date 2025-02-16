package sistema.logica.Excepciones;

import java.io.Serializable;
import java.lang.Exception;

public class CelularMayorQue1000 extends Exception implements Serializable {

	private String mensaje;

    public CelularMayorQue1000(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }

}
