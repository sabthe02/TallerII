package sistema.grafica.Controladores;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import sistema.grafica.VentanaListadoPaseosXDestino;
import sistema.logica.Excepciones.DestinoNoPerteneceException;
import sistema.logica.ValueObject.VOPaseosListado;

public class ControladorListadoGeneralPaseos extends ConexionRMI{

		private boolean conectado;
		private VentanaListadoPaseosXDestino ventana;
		
		public ControladorListadoGeneralMinivanes(VentanaListadoPaseosMinivan v)
		{
			ventana = v;
			try {
				conectado = Conectar();
			} catch (MalformedURLException e) {
				ventana.mostrarError("Problema de formar la URL");
				
			} catch (RemoteException e) {
				ventana.mostrarError("Problemas de conexion al servidor");
			
			} catch (NotBoundException e) {
				ventana.mostrarError("Problema con la direccion del servidor");
			}
			
			
		}
		
		public ArrayList<VOPaseosListado> obtenerListado() 
		{
			ArrayList<VOPaseosListado> arre = null;
			if (conectado) {	
				
				arre = super.iFac.ListadoPaseosMinivan();
				
			}

			
			return arre;
		}
		
	}
