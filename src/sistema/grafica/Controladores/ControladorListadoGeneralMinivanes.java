package sistema.grafica.Controladores;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import sistema.grafica.VentanaListadoGeneralMinivanes;
import sistema.logica.ValueObject.VOMinivanListado;

public class ControladorListadoGeneralMinivanes extends ConexionRMI{

	private boolean conectado;
	private VentanaListadoGeneralMinivanes ventana;
	
	public ControladorListadoGeneralMinivanes(VentanaListadoGeneralMinivanes v)
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
	
	public ArrayList<VOMinivanListado> obtenerListado() 
	{
		ArrayList<VOMinivanListado> arre = null;
		if (conectado) {	
			try {
				arre = super.iFac.ListadoGeneralMinivanes();
			} catch (RemoteException e) {
				ventana.mostrarError("Problemas de conexion al servidor");
			}
		}
			
		
		return arre;
	}
	
}
