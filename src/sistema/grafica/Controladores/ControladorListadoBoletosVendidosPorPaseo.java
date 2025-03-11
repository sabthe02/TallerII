package sistema.grafica.Controladores;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import sistema.grafica.VentanaListadoBoletosVendidosXPaseo;
import sistema.logica.Excepciones.PaseoNoExiste;
import sistema.logica.ValueObject.VOListadoBoletos;
import sistema.logica.ValueObject.VOMinivanListado;

public class ControladorListadoBoletosVendidosPorPaseo extends ConexionRMI{
	
	private VentanaListadoBoletosVendidosXPaseo ventana;
	private boolean conectado;
	
	public ControladorListadoBoletosVendidosPorPaseo(VentanaListadoBoletosVendidosXPaseo v) {
		ventana = v;
		
		
		try {
			conectado = Conectar();
		} catch (MalformedURLException e) {
			ventana.mostrarError("Problema de formar la URL.");
			
		} catch (RemoteException e) {
			ventana.mostrarError("Problemas de conexion al servidor.");

			
		} catch (NotBoundException e) {
			ventana.mostrarError("Problema con la direccion del servidor.");
		}
	}
	
	public ArrayList<VOListadoBoletos> obtenerListado(String codigoPaseo, boolean esEspecial) 
	{
		ArrayList<VOListadoBoletos> arre = null;
		if (conectado) 
		{	
			try {
				arre = super.iFac.ListadoBoleto(codigoPaseo, esEspecial);
			} catch (RemoteException e) {
				ventana.mostrarError("Problemas de conexion al servidor.");
			} catch (PaseoNoExiste e) {
				ventana.mostrarError("El paseo indicado no existe.");
			}
		}else {
			ventana.mostrarError("No se pueden obtener los resultados ya que no se puede conectar al servidor.");
			
		}
			
		
		return arre;
	}

}
