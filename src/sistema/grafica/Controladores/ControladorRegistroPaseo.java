package sistema.grafica.Controladores;

import java.rmi.RemoteException;

import sistema.grafica.VentanaListadoGeneralMinivanes;
import sistema.grafica.VentanaRegistroPaseo;
import sistema.logica.Excepciones.MinivanNoExiste;
import sistema.logica.Excepciones.PrecioMenorCero;
import sistema.logica.ValueObject.VOPaseo;

public class ControladorRegistroPaseo extends ConexionRMI{

	private boolean conectado;
	private VentanaRegistroPaseo ventana;
	
	
	public ControladorRegistroPaseo(VentanaRegistroPaseo v)
	{
		super();
		
		conectado = Conectar();
		
		ventana = v;
	}
	
	public void RegistrarPaseo(VOPaseo voPaseo) throws RemoteException, MinivanNoExiste, PrecioMenorCero
	{
		if(conectado)
		{
			super.iFac.RegistroPaseo(voPaseo);
		}
		
	}
	
	
	
}
