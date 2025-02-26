package sistema.grafica.Controladores;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

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
	
	
	public ControladorVentaBoleto(VentanaVentaBoleto v)
	{
		super();
		ventana = v;
		
		try {
			conectado = Conectar();
		} 
		catch (MalformedURLException e) {
			ventana.mostrarError("Problema de formar la URL");
			
		} catch (RemoteException e) {
			ventana.mostrarError("Problemas de conexion al servidor");
			
		} catch (NotBoundException e) {
			ventana.mostrarError("Problema con la direccion del servidor");
		}
		
	}
	
	public void ComprarBoleto(VOCompraBoleto voBoleto)
	{
		if(conectado)
		{	try {
			super.iFac.ComprarBoleto(voBoleto);
		
		} catch (RemoteException e) {
			ventana.mostrarError("Problemas de conexion al servidor");
			} catch (BoletosNoDisponibles e) {
				ventana.mostrarError("No existen suficientes cupos en el paseo");
			} catch (PaseoNoExiste e) {
				ventana.mostrarError("No existe un paseo con el codigo ingresado");
			} catch(CelularMayorQue1000 e){
				ventana.mostrarError("El celular debe ser mayor que cero");
			} catch(MenorDe0 e) {
				ventana.mostrarError("La edad debe ser mayor que cero");
			}
		}
		}
		
		

}
