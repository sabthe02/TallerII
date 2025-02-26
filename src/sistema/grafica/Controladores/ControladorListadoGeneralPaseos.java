package sistema.grafica.Controladores;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import sistema.grafica.VentanaListadoPaseosMinivan;
import sistema.logica.Excepciones.MinivanNoExiste;
import sistema.logica.ValueObject.VOPaseosListado;

public class ControladorListadoGeneralPaseos extends ConexionRMI{

		private boolean conectado;
		private VentanaListadoPaseosMinivan ventana;
		
		public ControladorListadoGeneralPaseos(VentanaListadoPaseosMinivan v)
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
		
		public ArrayList<VOPaseosListado> ObtenerPaseos(String matricula)
		{
		
			ArrayList<VOPaseosListado> resp = null;
			if(conectado)
			{
				try {
					resp = super.iFac.ListadoPaseosMinivan(matricula);
				} catch (RemoteException e) {
					 ventana.mostrarError("Problemas de conexion al servidor");
				} catch (MinivanNoExiste e) {
					ventana.mostrarError("La minivan con el codigo ingresado no existe");
				} 
			
			}else {
				ventana.mostrarError("No se pueden obtener los resultados ya que no se puede conectar al servidor.");
				
			}
			
			return resp;
		}


		
	}
