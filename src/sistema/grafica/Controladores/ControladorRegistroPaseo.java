package sistema.grafica.Controladores;

import java.rmi.RemoteException;

import sistema.grafica.VentanaListadoGeneralMinivanes;
import sistema.logica.Excepciones.MinivanNoExiste;
import sistema.logica.Excepciones.PrecioMenorCero;
import sistema.logica.ValueObject.VOPaseo;

public class ControladorRegistroPaseo extends ConexionRMI{

	boolean conectado;
	
	public ControladorRegistroPaseo()
	{
		super();
		
		conectado = Conectar();
	}
	
	public void RegistrarPaseo(VOPaseo voPaseo) throws RemoteException, MinivanNoExiste, PrecioMenorCero
	{
		if(conectado)
		{
			super.iFac.RegistroPaseo(voPaseo);
		}
		
	}
	
	
	
}
