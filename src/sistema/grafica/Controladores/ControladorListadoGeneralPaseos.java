package sistema.grafica.Controladores;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import sistema.grafica.VentanaListadoPaseosMinivan;
import sistema.grafica.VentanaListadoPaseosXDestino;
import sistema.logica.Excepciones.DestinoNoPerteneceException;
import sistema.logica.Excepciones.MinivanNoExiste;
import sistema.logica.ValueObject.VOPaseosListado;

public class ControladorListadoGeneralPaseos extends ConexionRMI{

		private boolean conectado;
		private VentanaListadoPaseosMinivan ventana;
		
		public void ControladorListadoGeneralMinivanes(VentanaListadoPaseosMinivan v)
		{
			ventana = v;
			try {
				conectado = Conectar();
			} catch (MalformedURLException e) {
				
				
			} catch (RemoteException e) {
			
			} catch (NotBoundException e) {
			}
			
			
		}
		
		public ArrayList<VOPaseosListado> obtenerListado() 
		{
			ArrayList<VOPaseosListado> arre = null;
			if (conectado) {	
				
				try {
					arre = super.iFac.ListadoPaseosMinivan("");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MinivanNoExiste e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			
			return arre;
		}
		
	}
