package sistema.logica.Excepciones;

public class CodigoPaseoYaExiste extends Exception {

	private static final long serialVersionUID = 1L;
	private String mensaje;

    public CodigoPaseoYaExiste(String mensaje) {
        this.mensaje = mensaje;
    }

    public String darMensaje() {
        return mensaje;
    }
    
    
}