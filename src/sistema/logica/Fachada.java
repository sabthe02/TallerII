package sistema.logica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Properties;

import sistema.logica.Excepciones.*;
import sistema.logica.Minivanes.*;
import sistema.logica.Paseos.Paseos;
import sistema.logica.Paseos.Paseo;
import sistema.logica.ValueObject.*;
import sistema.persistencia.*;


public class Fachada {
	
	private Minivanes colMinivan;
	private Paseos colPaseos;
	
	public static Monitor monitor;
	

	public Fachada() {
		colMinivan = new Minivanes();
		colPaseos = new Paseos();
		monitor = new Monitor();
	}

	public void RegistroMinivanes(VOMinivan VO) throws MinivanYaExisteException, CantAsientosMayorCeroException, RemoteException {
		Fachada.monitor.comienzoEscritura();
		if (VO.getCantidadAsientos() > 0) {
			if (!colMinivan.member(VO.getMatricula())) {
				
				Minivan m = new Minivan(VO.getMatricula(), VO.getMarca(), VO.getModelo(), VO.getCantidadAsientos());
				colMinivan.insert(m.getMatricula(), m);
				monitor.terminoEscritura();
				
			} else {
				monitor.terminoEscritura();
				String mensajeError = String.format("Ya existe una minivan con la matrícula: %s", VO.getMatricula());				
				throw new MinivanYaExisteException(mensajeError);
			}
		} else {
			monitor.terminoEscritura();
			String mensajeError = "La cantidad de asientos tiene que ser mayor que cero";			
			throw new CantAsientosMayorCeroException(mensajeError);	        
		}

		
	}

	public ArrayList<VOMinivanListado> ListadoGeneralMinivanes() throws RemoteException {
		monitor.comienzoLectura();		
		ArrayList<VOMinivanListado> arre = colMinivan.ListadoMinivanes();
		monitor.terminoLectura();
		return arre;
			
	}
		
	

	public void RegistroPaseo(VOPaseo VO) throws MinivanNoExiste, PrecioMenorCero, RemoteException {
		monitor.comienzoEscritura();
		boolean agregar;
		boolean vanDisponible;
		if (VO.getPrecioBase() > 0) {
			agregar = false;
			Iterator<Minivan> iterm = colMinivan.arbol.values().iterator();

			while (iterm.hasNext() && !agregar) {
				vanDisponible = true;
				Minivan m = iterm.next();
				Iterator<Paseo> iterp = m.getPaseos().arbol.values().iterator();

				while (iterp.hasNext() && vanDisponible) {
					Paseo p = iterp.next();
					if (p.getHoraPartida().isAfter(VO.getHoraPartida())) {
						if (p.getHoraPartida().isBefore(VO.getHoraRegreso())) {
							vanDisponible = false;
						}
					} else {
						if (p.getHoraPartida().isBefore(VO.getHoraPartida())) {
							if (p.getHoraRegreso().isAfter(VO.getHoraPartida())) {
								vanDisponible = false;

							}
						} else {
							vanDisponible = false;
						}
					}
				}
				if (vanDisponible) {
					agregar = true;
				}
				if (agregar) {
					Paseo paseo = new Paseo(VO.getCodigo(), VO.getHoraPartida(), VO.getHoraRegreso(), 0,
							m.getCantAsientos(), VO.getPrecioBase(), VO.getDestino());

					m.getPaseos().registroPaseo(paseo);
					colPaseos.insert(VO.getCodigo(), paseo);
					monitor.terminoEscritura();

				}

			}
			if (!agregar) {
				monitor.terminoEscritura();
				String mensajeError = "No hay minivanes disponibles para ese paseo";
				throw new MinivanNoExiste(mensajeError);
			}

		} else {
			monitor.terminoEscritura();
			String mensajeError = "Por favor ingresar un precio mayor a cero";
			throw new PrecioMenorCero(mensajeError);
		}
            
	}

	public ArrayList<VOPaseosListado> ListadoPaseosMinivan(String matricula) throws MinivanNoExiste, RemoteException {
           monitor.comienzoLectura();
	           if(colMinivan.member(matricula)) {
	        	   ArrayList<VOPaseosListado> arre = colMinivan.ListadoPaseosEnMinivan(matricula);
	        	   monitor.terminoLectura();
        	   return arre;
           }
           else {
        	   monitor.terminoLectura();
        	   throw new MinivanNoExiste(String.format("La minivan con codigo: %s no existe", matricula));
           }
         	     
	}

	public ArrayList<VOPaseosListado> ListadoPaseosDestino(String destino) throws DestinoNoPerteneceException, RemoteException {
        monitor.comienzoLectura();
		boolean existe;
		switch (destino) {

		case "Punta del Este":
		case "Piriapolis":
		case "Canelones":
		case "Maldonado":
		case "Rocha":
			existe = true;
			break;

		default:
			existe = false;
		break;
		}
		if (existe) {
			
			ArrayList<VOPaseosListado> arre = colPaseos.listadoPaseosDestino(destino);
			monitor.terminoLectura();
			return arre;
			
		} else {
			monitor.terminoLectura();
			String mensajeError = String.format("El destino %s no partenece a la lista de posibles destinos", destino);
			throw new DestinoNoPerteneceException(mensajeError);
		}
		
	}

	public ArrayList<VOPaseosListado> ListadoPaseosDispBoletos(int cantBoletos) throws CantidadMayorCero, RemoteException {

		monitor.comienzoLectura();
			if(cantBoletos <= 0) {
				monitor.terminoLectura();
				throw new CantidadMayorCero("La cantidad de boletos debe ser mayor que cero");
			}
			monitor.terminoLectura();
			return colPaseos.listadoPaseosDisponible(cantBoletos);

	}

	public void ComprarBoleto(VOCompraBoleto voBoleto)
	        throws BoletosNoDisponibles, PaseoNoExiste, CelularMayorQue1000, MenorDe0, RemoteException {
		if (voBoleto.getEdad() > 0) {

			if (Integer.parseInt(voBoleto.getCelular()) > 0) {
				if (colPaseos.member(voBoleto.getCodigoPaseo())) {
					if (colPaseos.find(voBoleto.getCodigoPaseo()).getCantidadBoletosDisponibles() > 0) {
						colPaseos.compraBoleto(voBoleto);
						monitor.terminoEscritura();

					} else {
						monitor.terminoEscritura();
						String mensajeError = "No hay boletos disponibles.";
						throw new BoletosNoDisponibles(mensajeError);
					}
				} else {
					monitor.terminoEscritura();
					String mensajeError = String.format("El paseo con código: %s no existe.",
							voBoleto.getCodigoPaseo());
					throw new PaseoNoExiste(mensajeError);
				}

			} else {
				monitor.terminoEscritura();
				String mensajeError = "Ingresar un numero de celular mayor que 0";
				throw new CelularMayorQue1000(mensajeError);
			}

		} else {
			monitor.terminoEscritura();
			String mensajeError = String.format("Edad: %d es menor que 0, ingresar edad valida", voBoleto.getEdad());
			throw new MenorDe0(mensajeError);

		}

	}


	public ArrayList<VOListadoBoletos> ListadoBoleto(String codigo, boolean esEsp) throws PaseoNoExiste, RemoteException {
        monitor.comienzoLectura();

		if (colPaseos.member(codigo)) {
			if (colPaseos.CantidadBoletosVendidos(codigo) > 0) {
				ArrayList<VOListadoBoletos> arre = colPaseos.listadoBoletoTipo(codigo, esEsp);
				monitor.terminoLectura();
				return arre;
			}
		} else {
			monitor.terminoLectura();
			String mensajeError = String.format("El paseo con código: %s no existe.", codigo);
			throw new PaseoNoExiste(mensajeError);
		}
		return null;
        
        
	}

	public Double MontoRecaudadoPorPaseo(String codigoPaseo) throws PaseoNoExiste, RemoteException {
		monitor.comienzoLectura();
		Double resp = -1.0;

		if (colPaseos.member(codigoPaseo)) {
			resp = colPaseos.find(codigoPaseo).montoRecaudadoPaseo();
			monitor.terminoLectura();

		} else {
			monitor.terminoLectura();
			throw new PaseoNoExiste("El Codigo de paseo indicado no existe.");
			
		}

		return resp;
		
	}

	public void RespaldarDatos() throws PersistenciaException, RemoteException {
		monitor.comienzoLectura();
		String ruta = "";
		try {

			Properties p = new Properties();
			String nomArchProperties = "./config/config.properties";
			p.load(new FileInputStream(nomArchProperties));
			ruta = p.getProperty("rutaDatosRespaldo");

			VOMinivanesYPaseosRespaldo vo = new VOMinivanesYPaseosRespaldo(this.colMinivan, this.colPaseos);

			new Respaldo().respaldar(ruta, vo);
			monitor.terminoLectura();
			
		} catch (IOException e) {
			e.printStackTrace();
			monitor.terminoLectura();

		} catch (PersistenciaException e) {
			monitor.terminoLectura();
			System.out.println("Error de persistencia: " + e.getMessage());
			
		}

	}

	public void RecuperarDatos() throws PersistenciaException, RemoteException {
		monitor.comienzoEscritura();
		String ruta = "";
		try {

			Properties p = new Properties();
			String nomArchProperties = "./config/config.properties";
			p.load(new FileInputStream(nomArchProperties));
			ruta = p.getProperty("rutaDatosRespaldo");
			VOMinivanesYPaseosRespaldo vo = new Respaldo().recuperar(ruta);
			if (vo!= null) {
				this.colMinivan = vo.getColMinivan();
				this.colPaseos = vo.getColPaseos();
				monitor.terminoEscritura();
			}

		} catch (IOException e) {
			e.printStackTrace();
			monitor.terminoEscritura();
			
		} catch (PersistenciaException e) {
			System.out.println("Error de persistencia: " + e.getMessage());
			monitor.terminoEscritura();
		}
	}

	

}
