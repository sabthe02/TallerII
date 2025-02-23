package sistema.grafica.Controladores;

import java.rmi.RemoteException;
import java.util.ArrayList;

import sistema.grafica.VentanaListadoGeneralMinivanes;
import sistema.logica.ValueObject.VOMinivanListado;

public class ControladorListadoGeneralMinivanes extends ConexionRMI{

	private boolean conectado;
	private VentanaListadoGeneralMinivanes ventana;
	
	public ControladorListadoGeneralMinivanes(VentanaListadoGeneralMinivanes v)
	{
		
		conectado = Conectar();
		
		ventana = v;
	}
	
	public ArrayList<VOMinivanListado> obtenerListado() throws RemoteException
	{
		
		return super.iFac.ListadoGeneralMinivanes();
		
	}
	
}
