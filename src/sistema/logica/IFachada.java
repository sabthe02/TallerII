package sistema.logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import sistema.logica.Excepciones.BoletosNoDisponibles;
import sistema.logica.Excepciones.CantAsientosMayorCeroException;
import sistema.logica.Excepciones.CantidadMayorCero;
import sistema.logica.Excepciones.CelularMayorQue1000;
import sistema.logica.Excepciones.DestinoNoPerteneceException;
import sistema.logica.Excepciones.MenorDe0;
import sistema.logica.Excepciones.MinivanNoExiste;
import sistema.logica.Excepciones.MinivanYaExisteException;
import sistema.logica.Excepciones.PaseoNoExiste;
import sistema.logica.Excepciones.PersistenciaException;
import sistema.logica.Excepciones.PrecioMenorCero;
import sistema.logica.ValueObject.VOCompraBoleto;
import sistema.logica.ValueObject.VOListadoBoletos;
import sistema.logica.ValueObject.VOMinivan;
import sistema.logica.ValueObject.VOMinivanListado;
import sistema.logica.ValueObject.VOPaseo;
import sistema.logica.ValueObject.VOPaseosListado;

public interface IFachada extends Remote{
	
	public void RegistroMinivanes(VOMinivan VO) throws MinivanYaExisteException, CantAsientosMayorCeroException, RemoteException;
	public ArrayList<VOMinivanListado> ListadoGeneralMinivanes() throws RemoteException ;
	public void RegistroPaseo(VOPaseo VO) throws MinivanNoExiste, PrecioMenorCero, RemoteException;
	public ArrayList<VOPaseosListado> ListadoPaseosMinivan(String matricula) throws MinivanNoExiste, RemoteException ;
	public ArrayList<VOPaseosListado> ListadoPaseosDestino(String destino) throws RemoteException;
	public ArrayList<VOPaseosListado> ListadoPaseosDispBoletos(int cantBoletos) throws CantidadMayorCero, RemoteException;
	public void ComprarBoleto(VOCompraBoleto voBoleto) throws BoletosNoDisponibles, PaseoNoExiste, CelularMayorQue1000, MenorDe0, RemoteException ;
	public ArrayList<VOListadoBoletos> ListadoBoleto(String codigo, boolean esEsp) throws PaseoNoExiste, RemoteException;
	public Double MontoRecaudadoPorPaseo(String codigoPaseo) throws PaseoNoExiste, RemoteException;
	public void RespaldarDatos() throws PersistenciaException, RemoteException;
	public void RecuperarDatos() throws PersistenciaException, RemoteException;
	
}
