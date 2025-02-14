
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
    public static class MinivanYaExisteException extends Exception {
        private String mensaje;

        public MinivanYaExisteException(String mensaje) {
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
    
    public static class CantAsientosMayorCeroException extends Exception {
        private String mensaje;

        public CantAsientosMayorCeroException(String mensaje) {
            this.mensaje = mensaje;
        }

        public String darMensaje() {
            return mensaje;
        }
    }
    
    public static class MenorDe18 extends Exception {
        private String mensaje;

        public MenorDe18(String mensaje) {
            this.mensaje = mensaje;
        }

        public String darMensaje() {
            return mensaje;
        }
    }
    
    public static class PaseoNoExiste extends Exception {
        private String mensaje;

        public PaseoNoExiste(String mensaje) {
            this.mensaje = mensaje;
        }

        public String darMensaje() {
            return mensaje;
        }
    }
    
    public static class BoletosNoDisponibles extends Exception {
        private String mensaje;

        public BoletosNoDisponibles(String mensaje) {
            this.mensaje = mensaje;
        }

        public String darMensaje() {
            return mensaje;
        }
    }
    
    public static class CelularMayorQue1000 extends Exception {
        private String mensaje;

        public CelularMayorQue1000(String mensaje) {
            this.mensaje = mensaje;
        }

        public String darMensaje() {
            return mensaje;
        }
    }
    
    
    public static class PaseoNoExisteConCodigo extends Exception {
        private String mensaje;

        public PaseoNoExisteConCodigo(String mensaje) {
            this.mensaje = mensaje;
        }

        public String darMensaje() {
            return mensaje;
        }
    }
    
    
    
}
