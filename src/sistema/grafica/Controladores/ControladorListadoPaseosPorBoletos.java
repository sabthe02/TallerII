package sistema.grafica.Controladores;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import sistema.grafica.VentanaListadoPaseosDispBoletos;
import sistema.grafica.VentanaRegistroPaseo;
import sistema.logica.Excepciones.CantidadMayorCero;
import sistema.logica.Excepciones.MinivanNoExiste;
import sistema.logica.Excepciones.PrecioMenorCero;
import sistema.logica.ValueObject.VOPaseo;
import sistema.logica.ValueObject.VOPaseosListado;

public class ControladorListadoPaseosPorBoletos extends ConexionRMI {

	private boolean conectado;
	private VentanaListadoPaseosDispBoletos ventana;

	public ControladorListadoPaseosPorBoletos(VentanaListadoPaseosDispBoletos v) {
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

	public ArrayList<VOPaseosListado> ObtenerPaseos(int cantidadBoletos)
	{
	
		ArrayList<VOPaseosListado> resp = null;
		if(conectado)
		{
			try {
				resp =  super.iFac.ListadoPaseosDispBoletos(cantidadBoletos);
			} catch (CantidadMayorCero e) {
				ventana.mostrarError("La cantidad de Boletos ingresados debe ser mayor a 0.");
			}
			 catch (RemoteException e) {
				 ventana.mostrarError("Problemas de conexion al servidor");
			} 
		
		}else {
			ventana.mostrarError("No se pueden obtener los resultados ya que no se puede conectar al servidor.");
			
		}
		
		return resp;
	}
	
	
}

