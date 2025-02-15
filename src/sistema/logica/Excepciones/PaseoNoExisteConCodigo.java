package sistema.logica.Excepciones;
import java.io.Serializable;
import java.lang.Exception;

public class PaseoNoExisteConCodigo extends Exception implements Serializable {

	private String mensaje;

    public PaseoNoExisteConCodigo(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
}
