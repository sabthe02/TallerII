package sistema.logica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Properties;

import javax.management.monitor.Monitor;

import sistema.logica.Excepciones.*;
import sistema.logica.Minivanes.*;
import sistema.logica.Paseos.Paseos;
import sistema.logica.Paseos.Paseo;
import sistema.logica.ValueObject.*;
import sistema.persistencia.*;


public class Fachada implements Serializable {
	
	private static final long serialVersionUID = 1L; 

	private Minivanes colMinivan;
	private Paseos colPaseos;
	
	public static Monitor monitor;
	

	public Fachada() {
		colMinivan = new Minivanes();
		colPaseos = new Paseos();
		monitor = new Monitor();
	}

	public void RegistroMinivanes(VOMinivan VO) throws MinivanYaExisteException, CantAsientosMayorCeroException {
		if (VO.getCantidadAsientos() > 0) {
			if (!colMinivan.member(VO.getMatricula())) {
				Minivan m = new Minivan(VO.getMatricula(), VO.getMarca(), VO.getModelo(), VO.getCantidadAsientos());
				colMinivan.insert(m.getMatricula(), m);
				
			} else {
				String mensajeError = String.format("Ya existe una minivan con la matrícula: %s", VO.getMatricula());
				throw new MinivanYaExisteException(mensajeError);
			}
		} else {
			String mensajeError = "La cantidad de asientos tiene que ser mayor que cero";
			throw new CantAsientosMayorCeroException(mensajeError);
		Fachada.monitor.comienzoEscritura();
		try {
			if (VO.getCantidadAsientos() <= 0) {
				throw new CantAsientosMayorCeroException("La cantidad de asientos tiene que ser mayor a cero");
			}
			if(colMinivan.member(VO.getMatricula())){
				throw new MinivanYaExisteException(String.format("Ya existe una minivan con la matrícula %s", VO.getMatricula()));
			}
			 Minivan m = new Minivan(VO.getMatricula(), VO.getMarca(), VO.getModelo(), VO.getCantidadAsientos());
			 colMinivan.insert(m.getMatricula(), m);
			
		}finally {
	        Fachada.monitor.terminoEscritura();
		}

		
	}

	public ArrayList<VOMinivanListado> ListadoGeneralMinivanes() {
		Fachada.monitor.comienzoLectura();
		try {
			return colMinivan.ListadoMinivanes();
			
		} finally {
			
			Fachada.monitor.terminoLectura();
		}
			
	}
		
	

	public void RegistroPaseo(VOPaseo VO) throws MinivanNoExiste, PrecioMenorCero {
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

	public ArrayList<VOPaseosListado> ListadoPaseosMinivan(String matricula) throws MinivanNoExiste {
           Fachada.monitor.comienzoLectura();
           try {
        	   if(!colMinivan.member(matricula)) {
        	    throw new MinivanNoExiste(String.format("La minivan con codigo: %s no existe", matricula));
        	   }
        	   return colMinivan.ListadoPaseosEnMinivan(matricula);
        	   
           }finally {
        	   Fachada.monitor.terminoLectura(); 
           }

		     
	}

	public ArrayList<VOPaseosListado> ListadoPaseosDestino(String destino) throws DestinoNoPerteneceException {
        Fachada.monitor.comienzoLectura();
   try {
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
			return colPaseos.listadoPaseosDestino(destino);
		} else {
			String mensajeError = String.format("El destino %s no partenece a la lista de posibles destinos", destino);
			throw new DestinoNoPerteneceException(mensajeError);
		}
		}finally{
			Fachada.monitor.terminoLectura();
		}

		
	}

	public ArrayList<VOPaseosListado> ListadoPaseosDispBoletos(int cantBoletos) throws CantidadMayorCero {

		Fachada.monitor.comienzoLectura();
		try {
			if(cantBoletos <= 0) {
				throw new CantidadMayorCero("La cantidad de boletos debe ser mayor que cero");
			}
			return colPaseos.listadoPaseosDisponible(cantBoletos);
			
		}finally{
			Fachada.monitor.terminoLectura();

		}

	}

	public void ComprarBoleto(VOCompraBoleto voBoleto)
	        throws BoletosNoDisponibles, PaseoNoExiste, CelularMayorQue1000, MenorDe0 {
	    Fachada.monitor.comienzoEscritura();
	    try {
	        if (voBoleto.getEdad() <= 0) {
	            throw new MenorDe0(String.format("Edad: %d es menor que 0", voBoleto.getEdad()));
	        }

	        if (Integer.parseInt(voBoleto.getCelular()) <= 0) {
	            throw new CelularMayorQue1000("Ingresar un número de celular mayor que 0");
	        }

	        if (!colPaseos.member(voBoleto.getCodigoPaseo())) {
	            throw new PaseoNoExiste(String.format("El paseo con código: %s no existe", voBoleto.getCodigoPaseo()));
	        }

	        if (colPaseos.find(voBoleto.getCodigoPaseo()).getCantidadBoletosDisponibles() <= 0) {
	            throw new BoletosNoDisponibles("No hay boletos");
	        }

	        colPaseos.compraBoleto(voBoleto);
	    } finally {
	        Fachada.monitor.terminoEscritura();
	    }
	}


	public ArrayList<VOListadoBoletos> ListadoBoleto(String codigo, boolean esEsp) throws PaseoNoExiste {
        Fachada.monitor.comienzoLectura();
        try {
        	if(!colPaseos.member(codigo)) {
        		throw new PaseoNoExiste(String.format("El paseo con codigo: %s no existe", codigo));
        	}
        	if(colPaseos.CantidadBoletosVendidos(codigo) > 0) {
        		return colPaseos.listadoBoletoTipo(codigo, esEsp);
        	}
        	return new ArrayList<>();
        }finally {
        	Fachada.monitor.terminoLectura();
        }
		
	}

	public Double MontoRecaudadoPorPaseo(String codigoPaseo) throws PaseoNoExiste {
		Fachada.monitor.comienzoLectura();
		try {
			if(!colPaseos.member(codigoPaseo)) {
				throw new PaseoNoExiste("El codigo de paseo no existe");
			}
			return colPaseos.find(codigoPaseo).montoRecaudadoPaseo();
		}finally {
			Fachada.monitor.terminoLectura();
		}
		

	}

	public void RespaldarDatos() throws PersistenciaException {

		String ruta = "";
		try {

			Properties p = new Properties();
			String nomArchProperties = "./config/config.properties";
			p.load(new FileInputStream(nomArchProperties));
			ruta = p.getProperty("rutaDatosRespaldo");

			VOMinivanesYPaseosRespaldo vo = new VOMinivanesYPaseosRespaldo(this.colMinivan, this.colPaseos);

			new Respaldo().respaldar(ruta, vo);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PersistenciaException e) {
			System.out.println("Error de persistencia: " + e.getMessage());
		}

	}

	public void RecuperarDatos() throws PersistenciaException {

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
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (PersistenciaException e) {
			System.out.println("Error de persistencia: " + e.getMessage());
		}
	}

	public static void main(String args[]) {

		Fachada f = new Fachada();

		try {
			f.RecuperarDatos();
		} catch (PersistenciaException e) {
			System.out.println("Error al recuperar datos: " + e.darMensaje());
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

		System.out.println("FIN // Prueba funcion Registro Minivan: ");

		System.out.println("");
		System.out.println("INICIO //  (Req 2) Prueba funcion Listado General Minivanes. ");
		f.ListadoGeneralMinivanes().forEach((VOMinivanListado) -> {
			System.out.println("Minivan: ");
			System.out.println("Matricula: " + VOMinivanListado.getMatricula());
			System.out.println("Marca: " + VOMinivanListado.getMarca());
			System.out.println("Modelo: " + VOMinivanListado.getModelo());
			System.out.println("Cantidad Asientos: " + VOMinivanListado.getCantidadAsientos());
			System.out.println("");

		});

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
		}

		System.out.println("FIN //  (Req 9) Monto Recaudado en un Paseo");

		try {
			f.RespaldarDatos();
		} catch (PersistenciaException e) {
			System.out.println("Error al recuperar datos: " + e.darMensaje());

		}

	}

}
