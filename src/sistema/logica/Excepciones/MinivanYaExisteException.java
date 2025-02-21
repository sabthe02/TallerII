package sistema.logica.Excepciones;

import java.lang.Exception;

public class MinivanYaExisteException extends Exception {

	 private String mensaje;

     public MinivanYaExisteException(String mensaje) {
         this.mensaje = mensaje;
     }

     public String darMensaje() {
         return mensaje;
     }
     

}
