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
		
		try {
			conectado = Conectar();
      
		} catch (MalformedURLException e) {
      
			ventana.mostrarError("Problema de formar la URL");
		} catch (RemoteException e) {
			ventana.mostrarError("Problemas de conexion al servidor");

		} catch (NotBoundException e) {
			ventana.mostrarError("Problema con la direccion del servidor");
		}
		
		ventana = v;
	}
	
	public void ComprarBoleto(VOCompraBoleto voBoleto) throws BoletosNoDisponibles, PaseoNoExiste, CelularMayorQue1000, MenorDe0, RemoteException 
	{
		if(conectado)
		{
			try {
				super.iFac.ComprarBoleto(voBoleto);
			
			} catch (RemoteException e) {
				ventana.mostrarError("Problemas de conexion al servidor.");
			} catch (PaseoNoExiste e) {
				ventana.mostrarError("El Paseo NO Existe.");
			} catch (CelularMayorQue1000 e) {
				ventana.mostrarError("El Celular debe ser Mayor que 1000.");
			} catch(MenorDe0 e) {
				ventana.mostrarError("Menor de 0.");
			} catch(BoletosNoDisponibles e) {
				ventana.mostrarError("No hay boletos disponibles.");
			}
			
			
			
		}
		
	}
	
	

}
