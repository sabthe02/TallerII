package sistema.grafica.Controladores;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import sistema.grafica.VentanaRegistroPaseo;
import sistema.grafica.VentanaVentaBoleto;
import sistema.logica.Excepciones.BoletosNoDisponibles;
import sistema.logica.Excepciones.CelularMayorQue1000;
import sistema.logica.Excepciones.MenorDe0;
import sistema.logica.Excepciones.MinivanNoExiste;
import sistema.logica.Excepciones.PaseoNoExiste;
import sistema.logica.Excepciones.PrecioMenorCero;
import sistema.logica.ValueObject.VOCompraBoleto;
import sistema.logica.ValueObject.VOPaseo;

public class ControladorVentaBoleto extends ConexionRMI {
	
	private boolean conectado;
	private VentanaVentaBoleto ventana;
	
	
	public ControladorVentaBoleto(VentanaVentaBoleto v) throws MalformedURLException, RemoteException, NotBoundException
	{
		super();
		
		conectado = Conectar();
		
		ventana = v;
	}
	
	public void ComprarBoleto(VOCompraBoleto voBoleto) throws BoletosNoDisponibles, PaseoNoExiste, CelularMayorQue1000, MenorDe0, RemoteException 
	{
		if(conectado)
		{
			super.iFac.ComprarBoleto(voBoleto);
			
		}
		
	}
	
	

}
