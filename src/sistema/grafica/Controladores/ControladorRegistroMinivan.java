package sistema.grafica.Controladores;

import java.rmi.RemoteException;

import sistema.grafica.VentanaRegistroMinivan;
import sistema.logica.Excepciones.CantAsientosMayorCeroException;
import sistema.logica.Excepciones.*;
import sistema.logica.ValueObject.VOMinivan;

public class ControladorRegistroMinivan extends ConexionRMI {

	private VentanaRegistroMinivan ventana;

	private boolean conectado;
	
	public ControladorRegistroMinivan (VentanaRegistroMinivan v)
	{
		super();
		
		conectado = Conectar();
		ventana = v;
	}
	
	public void RegistrarMinivan(VOMinivan voMinivan) throws MinivanYaExisteException, CantAsientosMayorCeroException, RemoteException
	{
		if(conectado)
		{
			super.iFac.RegistroMinivanes(voMinivan);
		}
		
		
	}
	
}
