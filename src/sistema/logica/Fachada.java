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
		Fachada.monitor = new Monitor();
	}

	public void RegistroMinivanes(VOMinivan VO) throws MinivanYaExisteException, CantAsientosMayorCeroException, RemoteException {
		monitor.comienzoEscritura();
		if (VO.getCantidadAsientos() > 0) {
			if (!colMinivan.member(VO.getMatricula())) {
				
				Minivan m = new Minivan(VO.getMatricula(), VO.getMarca(), VO.getModelo(), VO.getCantidadAsientos());
				colMinivan.insert(m.getMatricula(), m);
				Fachada.monitor.terminoEscritura();
				
			} else {
				Fachada.monitor.terminoEscritura();
				String mensajeError = String.format("Ya existe una minivan con la matrícula: %s", VO.getMatricula());				
				throw new MinivanYaExisteException(mensajeError);
			}
		} else {
			Fachada.monitor.terminoEscritura();
			String mensajeError = "La cantidad de asientos tiene que ser mayor que cero";			
			throw new CantAsientosMayorCeroException(mensajeError);	        
		}

		
	}

	public ArrayList<VOMinivanListado> ListadoGeneralMinivanes() throws RemoteException {
		Fachada.monitor.comienzoLectura();
		try {
			return colMinivan.ListadoMinivanes();
			
		} finally {
			
			Fachada.monitor.terminoLectura();
		}
			
	}
		
	

	public void RegistroPaseo(VOPaseo VO) throws MinivanNoExiste, PrecioMenorCero, RemoteException {
		Fachada.monitor.comienzoEscritura();
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
					Fachada.monitor.terminoEscritura();

				}

			}
			if (!agregar) {
				Fachada.monitor.terminoEscritura();
				String mensajeError = "No hay minivanes disponibles para ese paseo";
				throw new MinivanNoExiste(mensajeError);
			}

		} else {
			Fachada.monitor.terminoEscritura();
			String mensajeError = "Por favor ingresar un precio mayor a cero";
			throw new PrecioMenorCero(mensajeError);
		}
            
	}

	public ArrayList<VOPaseosListado> ListadoPaseosMinivan(String matricula) throws MinivanNoExiste, RemoteException {
           Fachada.monitor.comienzoLectura();
           // Saqué el try...catch...finally, igual entiendo que hay que encontrar una manera para que se ejecute 
           // el cierre del montitor DESPUES del return, pero no sé si va un try catch finally acá porque qué estamos 
           // tratando de atrapar? Este procedimiento lanza la excepción
	           if(colMinivan.member(matricula)) {
	        	   Fachada.monitor.terminoLectura(); // ver la manera de que se ejecute después del return
        	   return colMinivan.ListadoPaseosEnMinivan(matricula);
           }
           else {
        	   Fachada.monitor.terminoLectura();
        	   throw new MinivanNoExiste(String.format("La minivan con codigo: %s no existe", matricula));
           }
         	     
	}

	public ArrayList<VOPaseosListado> ListadoPaseosDestino(String destino) throws DestinoNoPerteneceException, RemoteException {
        Fachada.monitor.comienzoLectura();
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
			Fachada.monitor.terminoLectura(); // misma cosa, saqué el try catch finally, pero entiendo el punto, hay
			// que ver una manera de que se ejecute DESPUES del return
			return colPaseos.listadoPaseosDestino(destino);
		} else {
			Fachada.monitor.terminoLectura();
			String mensajeError = String.format("El destino %s no partenece a la lista de posibles destinos", destino);
			throw new DestinoNoPerteneceException(mensajeError);
		}
		
	}

	public ArrayList<VOPaseosListado> ListadoPaseosDispBoletos(int cantBoletos) throws CantidadMayorCero, RemoteException {

		Fachada.monitor.comienzoLectura();
			if(cantBoletos <= 0) {
				monitor.terminoLectura();
				throw new CantidadMayorCero("La cantidad de boletos debe ser mayor que cero");
			}
			Fachada.monitor.terminoLectura(); // misma cosa, saqué el try catch finally, pero entiendo que hay
			// que ver una manera de que se ejecute DESPUES del return
			return colPaseos.listadoPaseosDisponible(cantBoletos);

	}

	public void ComprarBoleto(VOCompraBoleto voBoleto)
	        throws BoletosNoDisponibles, PaseoNoExiste, CelularMayorQue1000, MenorDe0, RemoteException {
		if (voBoleto.getEdad() > 0) {

			if (Integer.parseInt(voBoleto.getCelular()) > 0) {
				if (colPaseos.member(voBoleto.getCodigoPaseo())) {
					if (colPaseos.find(voBoleto.getCodigoPaseo()).getCantidadBoletosDisponibles() > 0) {
						colPaseos.compraBoleto(voBoleto);
						Fachada.monitor.terminoEscritura();

					} else {
						Fachada.monitor.terminoEscritura();
						String mensajeError = "No hay boletos disponibles.";
						throw new BoletosNoDisponibles(mensajeError);
					}
				} else {
					Fachada.monitor.terminoEscritura();
					String mensajeError = String.format("El paseo con código: %s no existe.",
							voBoleto.getCodigoPaseo());
					throw new PaseoNoExiste(mensajeError);
				}

			} else {
				Fachada.monitor.terminoEscritura();
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
        Fachada.monitor.comienzoLectura();
////        try { Dejo comentado, entiendo la idea que el monitor se tiene que liberar después del return
//        	if(colPaseos.member(codigo)) {
//        		return colPaseos.listadoBoletoTipo(codigo, esEsp);
//        	}
//        	else {
//        		throw new PaseoNoExiste(String.format("El paseo con codigo: %s no existe", codigo));
//        	}
//        	return new ArrayList<>();
//        }finally {
//        	Fachada.monitor.terminoLectura();
//        }
//		
		if (colPaseos.member(codigo)) {
			if (colPaseos.CantidadBoletosVendidos(codigo) > 0) {
				Fachada.monitor.terminoLectura(); // ver cómo se podría hacer después del return
				return colPaseos.listadoBoletoTipo(codigo, esEsp);
			}
		} else {
			Fachada.monitor.terminoLectura();
			String mensajeError = String.format("El paseo con código: %s no existe.", codigo);
			throw new PaseoNoExiste(mensajeError);
		}
		return null;
        
        
	}

	public Double MontoRecaudadoPorPaseo(String codigoPaseo) throws PaseoNoExiste, RemoteException {
		Fachada.monitor.comienzoLectura();
//		try { dejo comentado
//			if(!colPaseos.member(codigoPaseo)) {
//				throw new PaseoNoExiste("El codigo de paseo no existe");
//			}
//			return colPaseos.find(codigoPaseo).montoRecaudadoPaseo();
//		}finally {
//			Fachada.monitor.terminoLectura();
//		}
//		
//
		Double resp = -1.0;

		if (colPaseos.member(codigoPaseo)) {
			resp = colPaseos.find(codigoPaseo).montoRecaudadoPaseo();
			Fachada.monitor.terminoLectura();

		} else {
			Fachada.monitor.terminoLectura();
			throw new PaseoNoExiste("El Codigo de paseo indicado no existe.");
			
		}

		return resp;
		
	}

	public void RespaldarDatos() throws PersistenciaException, RemoteException {
		Fachada.monitor.comienzoLectura();
		String ruta = "";
		try {

			Properties p = new Properties();
			String nomArchProperties = "./config/config.properties";
			p.load(new FileInputStream(nomArchProperties));
			ruta = p.getProperty("rutaDatosRespaldo");

			VOMinivanesYPaseosRespaldo vo = new VOMinivanesYPaseosRespaldo(this.colMinivan, this.colPaseos);

			new Respaldo().respaldar(ruta, vo);
			Fachada.monitor.terminoLectura();
			
		} catch (IOException e) {
			e.printStackTrace();

		} catch (PersistenciaException e) {
			System.out.println("Error de persistencia: " + e.getMessage());
		}
		finally { // no tenía esto, le puse, pero me queda la duda de usar el try catch finally en el mismo procedimiento
			// que lanza la excepción
			Fachada.monitor.terminoLectura();
		}

	}

	public void RecuperarDatos() throws PersistenciaException, RemoteException {
		Fachada.monitor.comienzoEscritura();
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
				Fachada.monitor.terminoEscritura();
			}

		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (PersistenciaException e) {
			System.out.println("Error de persistencia: " + e.getMessage());
		}
		finally {// no tenía esto, le puse, pero me queda la duda de usar el try catch finally en el mismo procedimiento
			// que lanza la excepción
			Fachada.monitor.terminoEscritura();
		}
	}

	public static void main(String args[]) {

		Fachada f = new Fachada();

		try {
			f.RecuperarDatos();
		} catch (PersistenciaException e) {
			System.out.println("Error al recuperar datos: " + e.darMensaje());
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}

		System.out.println("INICIO // (Req 1) Prueba funcion Registro Minivan: ");
		VOMinivan VOm = new VOMinivan("A1", "Volvo", "Modelo1", 8);

		try {
			f.RegistroMinivanes(VOm);
		} catch (MinivanYaExisteException e) {
			e.printStackTrace();
		} catch (CantAsientosMayorCeroException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}

		VOMinivan VOm1 = new VOMinivan("A2", "Mercedes", "Modelo2", 5);

		try {
			f.RegistroMinivanes(VOm1);
		} catch (MinivanYaExisteException e) {
			e.printStackTrace();
		} catch (CantAsientosMayorCeroException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}

		System.out.println("FIN // Prueba funcion Registro Minivan: ");

		System.out.println("");
		System.out.println("INICIO //  (Req 2) Prueba funcion Listado General Minivanes. ");
		
		try {f.ListadoGeneralMinivanes().forEach((VOMinivanListado) -> {
			System.out.println("Minivan: ");
			System.out.println("Matricula: " + VOMinivanListado.getMatricula());
			System.out.println("Marca: " + VOMinivanListado.getMarca());
			System.out.println("Modelo: " + VOMinivanListado.getModelo());
			System.out.println("Cantidad Asientos: " + VOMinivanListado.getCantidadAsientos());
			System.out.println("");

		});
		}catch (RemoteException e) {
			e.printStackTrace();
		}

		System.out.println("FIN // Prueba funcion Listado General Minivanes. ");

		System.out.println("");
		System.out.println("INICIO //  (Req 3)  Prueba registro Paseos ");
		VOPaseo v = new VOPaseo("PDE01",
				LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(),
						LocalDateTime.now().getDayOfMonth(), 13, 0),
				LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(),
						LocalDateTime.now().getDayOfMonth(), 20, 0),
				20.0, "Piriapolis");

		try {
			f.RegistroPaseo(v);
		} catch (MinivanNoExiste e) {
			e.printStackTrace();
		} catch (PrecioMenorCero e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}catch (RemoteException e) {
			e.printStackTrace();
		}

		VOPaseo v1 = new VOPaseo("PDE02",
				LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(),
						LocalDateTime.now().getDayOfMonth(), 13, 0),
				LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(),
						LocalDateTime.now().getDayOfMonth(), 20, 0),
				15.0, "Punta del Este");

		try {
			f.RegistroPaseo(v1);
		} catch (MinivanNoExiste e) {
			e.printStackTrace();
		} catch (PrecioMenorCero e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}catch (RemoteException e) {
			e.printStackTrace();
		}

		System.out.println("FIN // Prueba registro Paseos ");

		System.out.println("");
		System.out.println("INICIO //  (Req 4) Prueba Listado Paseos Minivanes");

		try {

			f.ListadoPaseosMinivan("A1").forEach((VOPaseosListado) -> {
				System.out.println("Codigo: " + VOPaseosListado.getCodigo());
				System.out.println("Destino: " + VOPaseosListado.getDestino());
				DateTimeFormatter hora = DateTimeFormatter.ofPattern("H:mm");
				System.out.println("Hora Salida: " + VOPaseosListado.getHoraPartida().format(hora));
				DateTimeFormatter hora2 = DateTimeFormatter.ofPattern("H:mm");
				System.out.println("Hora Regreso: " + VOPaseosListado.getHoraRegreso().format(hora2));
				System.out.println("Precio Base: " + VOPaseosListado.getPrecioBase());
				System.out.println("Boletos Vendibles: " + VOPaseosListado.getCantidadMaximaBoletosVendibles());
				System.out.println("Boletos Disponibles: " + VOPaseosListado.getCantidadBoletosDisponibles());
				System.out.println();
			});
		} catch (MinivanNoExiste e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}

		System.out.println("FIN // Prueba Listado Paseos Minivanes");

		System.out.println("");
		System.out.println("INICIO //  (Req 5)  Prueba Listado Paseos por Destino");
		System.out.println();
		System.out.println();

		try {
			f.ListadoPaseosDestino("Punta del Este").forEach((VOPaseosListado) -> {
				System.out.println("Codigo: " + VOPaseosListado.getCodigo());
				System.out.println("Destino: " + VOPaseosListado.getDestino());
				DateTimeFormatter hora = DateTimeFormatter.ofPattern("H:mm");
				System.out.println("Hora Salida: " + VOPaseosListado.getHoraPartida().format(hora));
				DateTimeFormatter hora2 = DateTimeFormatter.ofPattern("H:mm");
				System.out.println("Hora Regreso: " + VOPaseosListado.getHoraRegreso().format(hora2));
				System.out.println("Precio Base: " + VOPaseosListado.getPrecioBase());
				System.out.println("Boletos Vendibles: " + VOPaseosListado.getCantidadMaximaBoletosVendibles());
				System.out.println("Boletos Disponibles: " + VOPaseosListado.getCantidadBoletosDisponibles());
				System.out.println();
			});
		} catch (DestinoNoPerteneceException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}

		System.out.println();

		System.out.println("FIN // Prueba Listado Paseos por Destino");

		System.out.println("");
		System.out.println("INICIO //  (Req 6)  Prueba Listado Paseos por cantidad boletos");

		try {
			f.ListadoPaseosDispBoletos(3).forEach((VOPaseosListado) -> {
				System.out.println("Codigo: " + VOPaseosListado.getCodigo());
				System.out.println("Destino: " + VOPaseosListado.getDestino());
				DateTimeFormatter hora = DateTimeFormatter.ofPattern("H:mm");
				System.out.println("Hora Salida: " + VOPaseosListado.getHoraPartida().format(hora));
				DateTimeFormatter hora2 = DateTimeFormatter.ofPattern("H:mm");
				System.out.println("Hora Regreso: " + VOPaseosListado.getHoraRegreso().format(hora2));
				System.out.println("Precio Base: " + VOPaseosListado.getPrecioBase());
				System.out.println("Boletos Vendibles: " + VOPaseosListado.getCantidadMaximaBoletosVendibles());
				System.out.println("Boletos Disponibles: " + VOPaseosListado.getCantidadBoletosDisponibles());
				System.out.println();
			});
		} catch (CantidadMayorCero e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}catch (RemoteException e) {
			e.printStackTrace();
		}

		System.out.println("FIN // Prueba Listado Paseos por Destino");

		System.out.println("");
		System.out.println("INICIO //  (Req 7) Prueba Compra Boleto");

		VOCompraBoleto vo = new VOCompraBoleto("Santiago", 30, "099099010", true, 20.5, "PDE01");
		try {
			f.ComprarBoleto(vo);
		} catch (BoletosNoDisponibles e) {
			e.printStackTrace();
		} catch (PaseoNoExiste e) {
			e.printStackTrace();
		} catch (CelularMayorQue1000 e) {
			e.printStackTrace();
		} catch (MenorDe0 e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}catch (RemoteException e) {
			e.printStackTrace();
		}

		VOCompraBoleto vo1 = new VOCompraBoleto("Maria", 10, "099099010", false, 20.5, "PDE02");
		try {
			f.ComprarBoleto(vo1);
		} catch (BoletosNoDisponibles e) {
			e.printStackTrace();
		} catch (PaseoNoExiste e) {
			e.printStackTrace();
		} catch (CelularMayorQue1000 e) {
			e.printStackTrace();
		} catch (MenorDe0 e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}catch (RemoteException e) {
			e.printStackTrace();
		}

		System.out.println("FIN // Prueba Compra Boleto");

		System.out.println("");
		System.out.println("INICIO //  (Req 8) Prueba Listado Boleto");
		try {
			f.ListadoBoleto("PDE01", true).forEach((VOListadoBoletos) -> {
				System.out.println("Nombre: " + VOListadoBoletos.getNombre());
				System.out.println("Edad: " + VOListadoBoletos.getEdad());
				System.out.println("Celular: " + VOListadoBoletos.getCelular());
				System.out.println("Descuento: " + VOListadoBoletos.getDescuento());
				System.out.println("Numero de Boleto: " + VOListadoBoletos.getNumeroBoleto());
			});
		} catch (PaseoNoExiste e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}catch (RemoteException e) {
			e.printStackTrace();
		}

		System.out.println("FIN // Prueba Listado Boleto");

		System.out.println("");
		System.out.println("INICIO //  (Req 9) Monto Recaudado en un Paseo\n");

		try {
			System.out.println(
					"El monto recaudado para el paseo: PDE02 es: " + f.MontoRecaudadoPorPaseo("PDE01").toString());
		} catch (PaseoNoExiste e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}catch (RemoteException e) {
			e.printStackTrace();
		}

		System.out.println("FIN //  (Req 9) Monto Recaudado en un Paseo");

		try {
			f.RespaldarDatos();
		} catch (PersistenciaException e) {
			System.out.println("Error al recuperar datos: " + e.darMensaje());

		}catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
