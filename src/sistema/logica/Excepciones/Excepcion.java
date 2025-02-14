
package sistema.logica.Excepciones;

public class Excepcion {
    public static class ListaPaseosVaciaException extends Exception {
        private String mensaje;

        public ListaPaseosVaciaException(String mensaje) {
            this.mensaje = mensaje;
        }

        public String darMensaje() {
            return mensaje;
        }
    }
    public static class MinivanNoEncontradaException extends Exception {
        private String mensaje;

        public MinivanNoEncontradaException(String mensaje) {
            this.mensaje = mensaje;
        }

        public String darMensaje() {
            return mensaje;
        }
    }
    
    public static class DestinoNoPerteneceException extends Exception {
        private String mensaje;

        public DestinoNoPerteneceException(String mensaje) {
            this.mensaje = mensaje;
        }

        public String darMensaje() {
            return mensaje;
        }
    }
    
    
    
}
