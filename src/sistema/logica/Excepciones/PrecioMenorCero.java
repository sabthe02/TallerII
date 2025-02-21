package sistema.logica.Excepciones;

import java.lang.Exception;


public class PrecioMenorCero extends Exception  {
	private String mensaje;

    public PrecioMenorCero(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
}

