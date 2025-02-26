package sistema.grafica.Controladores;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import sistema.grafica.VentanaMontoRecaudadoXPaseo;
import sistema.logica.Excepciones.PaseoNoExiste;
import sistema.logica.ValueObject.VOPaseo;

public class ControladorMontoRecaudadoXPaseo extends ConexionRMI {
	
	private VentanaMontoRecaudadoXPaseo ventana;

	private boolean conectado;
	
	public ControladorMontoRecaudadoXPaseo (VentanaMontoRecaudadoXPaseo v)
	{
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
	
	public double MontoRecaudadoPorPaseo(String codigo) throws PaseoNoExiste, RemoteException
	{
		double resu = 0;
		if(conectado)
		{
			try {
				resu = super.iFac.MontoRecaudadoPorPaseo(codigo);
			}catch(PaseoNoExiste e) {
				ventana.mostrarError("El Paseo no existe.");
			} catch(RemoteException e) {
				ventana.mostrarError("Remote Exception.");
			}
		}
		return resu;
		
		
	}

}
