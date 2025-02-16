package sistema.logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Iterator;


import sistema.logica.Excepciones.*;
import sistema.logica.Minivanes.*;
import sistema.logica.Paseos.Paseos;
import sistema.logica.Paseos.Paseo;
import sistema.logica.ValueObject.*;


public class Fachada implements Serializable {
	
	private static final long serialVersionUID = 1L; 


	private Minivanes colMinivan;
	private Paseos colPaseos;

	public Fachada() {
		colMinivan = new Minivanes();
		colPaseos = new Paseos();

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
		}
	}

	public ArrayList<VOMinivanListado> ListadoGeneralMinivanes() {
		return colMinivan.ListadoMinivanes();
	}

	public void RegistroPaseo(VOPaseo VO) throws MinivanNoExiste, PrecioMenorCero {
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
					colMinivan.insert(VO.getCodigo(), m);

				}

			}
			if (!agregar) {
				String mensajeError = String.format("La minivan con codigo %s no existe", VO.getCodigo());
				throw new MinivanNoExiste(mensajeError);
			}

		} else {

			String mensajeError = "Por favor ingresar un precio mayor a cero";
			throw new PrecioMenorCero(mensajeError);
		}

	}

	public ArrayList<VOPaseosListado> ListadoPaseosMinivan(String matricula) throws MinivanNoExiste {

		if (colMinivan.member(matricula)) {

			return colMinivan.ListadoPaseosEnMinivan(matricula);
		} else {
			String mensajeError = String.format("La minivan con codigo %s no existe", matricula);
			throw new MinivanNoExiste(mensajeError);
		}
	}

	public ArrayList<VOPaseosListado> ListadoPaseosDestino(String destino) throws DestinoNoPerteneceException {

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
		}
		if (existe) {
			return colPaseos.listadoPaseosDestino(destino);
		} else {
			String mensajeError = String.format("El destino %s no partenece a la lista de posibles destinos", destino);
			throw new DestinoNoPerteneceException(mensajeError);
		}
	}

	public ArrayList<VOPaseosListado> ListadoPaseosDispBoletos(int cantBoletos) throws CantidadMayorCero {

		ArrayList<VOPaseosListado> resp = new ArrayList<>();

		if (cantBoletos > 0) {
			resp = colPaseos.listadoPaseosDisponible(cantBoletos);

		} else {
			throw new CantidadMayorCero("La cantidad de Boletos debe ser mayor que cero");

		}

		return resp;
	}

	public void ComprarBoleto(VOCompraBoleto voBoleto)
			throws BoletosNoDisponibles, PaseoNoExiste, CelularMayorQue1000, MenorDe0 {
		if (voBoleto.getEdad() > 0) {

			if (Integer.parseInt(voBoleto.getCelular()) > 0) // verificar si el celular no deberia ser un int
			{/// Pregunta, acá no puede verificar si es >0 (lo que dice la letra)?
				if (colPaseos.member(voBoleto.getCodigoPaseo()))

				{
					if (colPaseos.find(voBoleto.getCodigoPaseo()).getCantidadBoletosDisponibles() > 0) {
						colPaseos.compraBoleto(voBoleto);

					} else {

						String mensajeError = "No hay boletos disponibles.";
						throw new BoletosNoDisponibles(mensajeError);
					}
				} else {
					String mensajeError = String.format("El paseo con código: %s no existe.",
							voBoleto.getCodigoPaseo());
					throw new PaseoNoExiste(mensajeError);
				}

			} else {

				String mensajeError = "Ingresar un numero de celular mayor que 0";
				throw new CelularMayorQue1000(mensajeError);
			}

		} else {
			String mensajeError = String.format("Edad: %d es menor que 0, ingresar edad valida", voBoleto.getEdad());
			throw new MenorDe0(mensajeError);

		}

	}

	public ArrayList<VOListadoBoletos> ListadoBoleto(String codigo, boolean esEsp) throws PaseoNoExiste {

		if (colPaseos.member(codigo)) {
			if (colPaseos.CantidadBoletosVendidos(codigo) > 0) {
				return colPaseos.listadoBoletoTipo(codigo, esEsp);
			}
		} else {
			String mensajeError = String.format("El paseo con código: %s no existe.", codigo);
			throw new PaseoNoExiste(mensajeError);
		}
		return null;
	}

	public Double MontoRecaudadoPorPaseo(String codigoPaseo) throws PaseoNoExiste {
		Double resp = -1.0;

		if (colPaseos.member(codigoPaseo)) {
			resp = colPaseos.find(codigoPaseo).montoRecaudadoPaseo();

		} else {
			throw new PaseoNoExiste("El Codigo de paseo indicado no existe.");

		}

		return resp;

	}

	public static void main(String args[]) {

		Fachada f = new Fachada();

		System.out.println("INICIO // (Req 1) Prueba funcion Registro Minivan: ");
		VOMinivan VOm = new VOMinivan("A1", "Volvo", "Modelo1", 8);

		try {
			f.RegistroMinivanes(VOm);
		} catch (MinivanYaExisteException | CantAsientosMayorCeroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		VOMinivan VOm1 = new VOMinivan("A2", "Mercedes", "Modelo3", 3);

		try {
			f.RegistroMinivanes(VOm1);
		} catch (MinivanYaExisteException | CantAsientosMayorCeroException e) {
			// TODO Auto-generated catch block
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

		// funciona

		System.out.println("");
		System.out.println("INICIO //  (Req 3)  Prueba registro Paseos ");
		VOPaseo v = new VOPaseo("PDE01",
				LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(),
						LocalDateTime.now().getDayOfMonth(), 13, 0),
				LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(),
						LocalDateTime.now().getDayOfMonth(), 20, 0),
				15.0, "Punta del Este");

		try {
			f.RegistroPaseo(v);
		} catch (MinivanNoExiste e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrecioMenorCero e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrecioMenorCero e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("FIN // Prueba registro Paseos ");

		System.out.println("");
		System.out.println("INICIO //  (Req 4) Prueba Listado Paseos Minivanes");

		try {

			f.ListadoPaseosMinivan("A1").forEach((VOPaseosListado) -> {
				System.out.println("Codigo: " + VOPaseosListado.getCodigo());
				System.out.println("Destino: " + VOPaseosListado.getDestino());
				System.out.println("Hora Salida: " + VOPaseosListado.getHoraPartida());
				System.out.println("Hora Regreso: " + VOPaseosListado.getHoraRegreso());
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

		// funciona

		System.out.println("");
		System.out.println("INICIO //  (Req 5)  Prueba Listado Paseos por Destino");
		System.out.println();
		System.out.println();

		try {
			f.ListadoPaseosDestino("Punta del Este").forEach((VOPaseosListado) -> {
				System.out.println("Codigo: " + VOPaseosListado.getCodigo());
				System.out.println("Destino: " + VOPaseosListado.getDestino());
				System.out.println("Hora Salida: " + VOPaseosListado.getHoraPartida());
				System.out.println("Hora Regreso: " + VOPaseosListado.getHoraRegreso());
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
				System.out.println("Hora Salida: " + VOPaseosListado.getHoraPartida());
				System.out.println("Hora Regreso: " + VOPaseosListado.getHoraRegreso());
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

		// funciona

		System.out.println("");
		System.out.println("INICIO //  (Req 7) Prueba Compra Boleto");

		VOCompraBoleto vo = new VOCompraBoleto("Santiago", 30, "099099010", true, 20.5, "PDE01");
		try {
			f.ComprarBoleto(vo);
		} catch (BoletosNoDisponibles e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PaseoNoExiste e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CelularMayorQue1000 e) {
			// TODO Auto-generated catch block

		} catch (MenorDe0 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		VOCompraBoleto vo1 = new VOCompraBoleto("Maria", 10, "099099010", false, 20.5, "PDE02");
		try {
			f.ComprarBoleto(vo1);
		} catch (BoletosNoDisponibles e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PaseoNoExiste e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CelularMayorQue1000 e) {
			// TODO Auto-generated catch block

		} catch (MenorDe0 e) {
			// TODO Auto-generated catch block
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
				System.out.println("Numero de Boleto: " + VOListadoBoletos.getNumeroBoleto()); // Ver por qué tira 0 por
																								// defecto
			});
		} catch (PaseoNoExiste e) {
			// TODO Auto-generated catch block
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
		}

		System.out.println("FIN //  (Req 9) Monto Recaudado en un Paseo");

	}

}
