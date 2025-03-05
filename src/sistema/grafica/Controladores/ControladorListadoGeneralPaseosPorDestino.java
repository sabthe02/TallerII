package sistema.grafica.Controladores;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import sistema.grafica.VentanaListadoPaseosDispBoletos;
import sistema.grafica.VentanaListadoPaseosXDestino;
import sistema.logica.Excepciones.CantidadMayorCero;
import sistema.logica.Excepciones.DestinoNoPerteneceException;
import sistema.logica.ValueObject.VOPaseosListado;

public class ControladorListadoGeneralPaseosPorDestino extends ConexionRMI{

	private boolean conectado;
	private VentanaListadoPaseosXDestino ventana;

	public ControladorListadoGeneralPaseosPorDestino(VentanaListadoPaseosXDestino v) {
		super();
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

	public ArrayList<VOPaseosListado> ObtenerPaseos(String destino)
	{
	
		ArrayList<VOPaseosListado> resp = null;
		if(conectado)
		{
			try {
				resp =  super.iFac.ListadoPaseosDestino(destino);
			} catch (RemoteException e) {
				 ventana.mostrarError("Problemas de conexion al servidor");
			} 
		
		}else {
			ventana.mostrarError("No se pueden obtener los resultados ya que no se puede conectar al servidor.");
			
		}
		
		return resp;
	}
	
	
}
