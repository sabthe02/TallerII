package sistema.logica.Excepciones;

import java.lang.Exception;

public class MinivanNoExiste extends Exception {

	 private String mensaje;

     public MinivanNoExiste(String mensaje) {
         this.mensaje = mensaje;
     }

     public String darMensaje() {
         return mensaje;
     }
     

}
