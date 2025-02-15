package sistema.logica.Excepciones;

import java.io.Serializable;
import java.lang.Exception;

public class MinivanNoExiste extends Exception implements Serializable {

	 private String mensaje;

     public MinivanNoExiste(String mensaje) {
         this.mensaje = mensaje;
     }

     public String darMensaje() {
         return mensaje;
     }
     

}
