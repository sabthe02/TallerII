package sistema.logica.Excepciones;

import java.io.Serializable;
import java.lang.Exception;

public class MinivanYaExisteException extends Exception implements Serializable {

	 private String mensaje;

     public MinivanYaExisteException(String mensaje) {
         this.mensaje = mensaje;
     }

     public String darMensaje() {
         return mensaje;
     }
     

}
