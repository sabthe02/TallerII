package sistema.cliente;



import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;

import sistema.grafica.MainProgram;
import sistema.logica.Fachada;
import sistema.logica.Excepciones.*;
import sistema.logica.ValueObject.*;

public class Cliente {

	public static void main(String args[]) {

		/*Fachada f = null;

		try {
			f = new Fachada();
			f.RecuperarDatos();
		} catch (PersistenciaException e) {
			System.out.println("Error al recuperar datos: " + e.darMensaje());
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}*/
		
		
		MainProgram main = new MainProgram();
		main.setVisible(true); 
		
//
//		System.out.println("INICIO // (Req 1) Prueba funcion Registro Minivan: ");
//		VOMinivan VOm = new VOMinivan("A1", "Volvo", "Modelo1", 8);
//
//		try {
//			f.RegistroMinivanes(VOm);
//		} catch (MinivanYaExisteException e) {
//			e.printStackTrace();
//		} catch (CantAsientosMayorCeroException e) {
//			e.printStackTrace();
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}
//		catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
//		VOMinivan VOm1 = new VOMinivan("A2", "Mercedes", "Modelo2", 5);
//
//		try {
//			f.RegistroMinivanes(VOm1);
//		} catch (MinivanYaExisteException e) {
//			e.printStackTrace();
//		} catch (CantAsientosMayorCeroException e) {
//			e.printStackTrace();
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}
//		catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("FIN // Prueba funcion Registro Minivan: ");
//
//		System.out.println("");
//		System.out.println("INICIO //  (Req 2) Prueba funcion Listado General Minivanes. ");
//		
//		try {f.ListadoGeneralMinivanes().forEach((VOMinivanListado) -> {
//			System.out.println("Minivan: ");
//			System.out.println("Matricula: " + VOMinivanListado.getMatricula());
//			System.out.println("Marca: " + VOMinivanListado.getMarca());
//			System.out.println("Modelo: " + VOMinivanListado.getModelo());
//			System.out.println("Cantidad Asientos: " + VOMinivanListado.getCantidadAsientos());
//			System.out.println("");
//
//		});
//		}catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("FIN // Prueba funcion Listado General Minivanes. ");
//
//		System.out.println("");
//		System.out.println("INICIO //  (Req 3)  Prueba registro Paseos ");
//		VOPaseo v = new VOPaseo("PDE01",
//				LocalTime.now(),
//				LocalTime.now(),
//				20.0, "Piriapolis");
//
//		try {
//			f.RegistroPaseo(v);
//		} catch (MinivanNoExiste e) {
//			e.printStackTrace();
//		} catch (PrecioMenorCero e) {
//			e.printStackTrace();
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
//		VOPaseo v1 = new VOPaseo("PDE02",
//				LocalTime.now(),
//				LocalTime.now(),
//				15.0, "Punta del Este");
//
//		try {
//			f.RegistroPaseo(v1);
//		} catch (MinivanNoExiste e) {
//			e.printStackTrace();
//		} catch (PrecioMenorCero e) {
//			e.printStackTrace();
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("FIN // Prueba registro Paseos ");
//
//		System.out.println("");
//		System.out.println("INICIO //  (Req 4) Prueba Listado Paseos Minivanes");
//
//		try {
//
//			f.ListadoPaseosMinivan("A1").forEach((VOPaseosListado) -> {
//				System.out.println("Codigo: " + VOPaseosListado.getCodigo());
//				System.out.println("Destino: " + VOPaseosListado.getDestino());
//				DateTimeFormatter hora = DateTimeFormatter.ofPattern("H:mm");
//				System.out.println("Hora Salida: " + VOPaseosListado.getHoraPartida().format(hora));
//				DateTimeFormatter hora2 = DateTimeFormatter.ofPattern("H:mm");
//				System.out.println("Hora Regreso: " + VOPaseosListado.getHoraRegreso().format(hora2));
//				System.out.println("Precio Base: " + VOPaseosListado.getPrecioBase());
//				System.out.println("Boletos Vendibles: " + VOPaseosListado.getCantidadMaximaBoletosVendibles());
//				System.out.println("Boletos Disponibles: " + VOPaseosListado.getCantidadBoletosDisponibles());
//				System.out.println();
//			});
//		} catch (MinivanNoExiste e) {
//			e.printStackTrace();
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}
//		catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("FIN // Prueba Listado Paseos Minivanes");
//
//		System.out.println("");
//		System.out.println("INICIO //  (Req 5)  Prueba Listado Paseos por Destino");
//		System.out.println();
//		System.out.println();
//
//		try {
//			f.ListadoPaseosDestino("Punta del Este").forEach((VOPaseosListado) -> {
//				System.out.println("Codigo: " + VOPaseosListado.getCodigo());
//				System.out.println("Destino: " + VOPaseosListado.getDestino());
//				DateTimeFormatter hora = DateTimeFormatter.ofPattern("H:mm");
//				System.out.println("Hora Salida: " + VOPaseosListado.getHoraPartida().format(hora));
//				DateTimeFormatter hora2 = DateTimeFormatter.ofPattern("H:mm");
//				System.out.println("Hora Regreso: " + VOPaseosListado.getHoraRegreso().format(hora2));
//				System.out.println("Precio Base: " + VOPaseosListado.getPrecioBase());
//				System.out.println("Boletos Vendibles: " + VOPaseosListado.getCantidadMaximaBoletosVendibles());
//				System.out.println("Boletos Disponibles: " + VOPaseosListado.getCantidadBoletosDisponibles());
//				System.out.println();
//			});
//		} catch (DestinoNoPerteneceException e) {
//			e.printStackTrace();
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}
//		catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println();
//
//		System.out.println("FIN // Prueba Listado Paseos por Destino");
//
//		System.out.println("");
//		System.out.println("INICIO //  (Req 6)  Prueba Listado Paseos por cantidad boletos");
//
//		try {
//			f.ListadoPaseosDispBoletos(3).forEach((VOPaseosListado) -> {
//				System.out.println("Codigo: " + VOPaseosListado.getCodigo());
//				System.out.println("Destino: " + VOPaseosListado.getDestino());
//				DateTimeFormatter hora = DateTimeFormatter.ofPattern("H:mm");
//				System.out.println("Hora Salida: " + VOPaseosListado.getHoraPartida().format(hora));
//				DateTimeFormatter hora2 = DateTimeFormatter.ofPattern("H:mm");
//				System.out.println("Hora Regreso: " + VOPaseosListado.getHoraRegreso().format(hora2));
//				System.out.println("Precio Base: " + VOPaseosListado.getPrecioBase());
//				System.out.println("Boletos Vendibles: " + VOPaseosListado.getCantidadMaximaBoletosVendibles());
//				System.out.println("Boletos Disponibles: " + VOPaseosListado.getCantidadBoletosDisponibles());
//				System.out.println();
//			});
//		} catch (CantidadMayorCero e) {
//			e.printStackTrace();
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("FIN // Prueba Listado Paseos por Destino");
//
//		System.out.println("");
//		System.out.println("INICIO //  (Req 7) Prueba Compra Boleto");
//
//		VOCompraBoleto vo = new VOCompraBoleto("Santiago", 30, "099099010", true, 20.5, "PDE01");
//		try {
//			f.ComprarBoleto(vo);
//		} catch (BoletosNoDisponibles e) {
//			e.printStackTrace();
//		} catch (PaseoNoExiste e) {
//			e.printStackTrace();
//		} catch (CelularMayorQue1000 e) {
//			e.printStackTrace();
//		} catch (MenorDe0 e) {
//			e.printStackTrace();
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
//		VOCompraBoleto vo1 = new VOCompraBoleto("Maria", 10, "099099010", false, 20.5, "PDE02");
//		try {
//			f.ComprarBoleto(vo1);
//		} catch (BoletosNoDisponibles e) {
//			e.printStackTrace();
//		} catch (PaseoNoExiste e) {
//			e.printStackTrace();
//		} catch (CelularMayorQue1000 e) {
//			e.printStackTrace();
//		} catch (MenorDe0 e) {
//			e.printStackTrace();
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("FIN // Prueba Compra Boleto");
//
//		System.out.println("");
//		System.out.println("INICIO //  (Req 8) Prueba Listado Boleto");
//		try {
//			f.ListadoBoleto("PDE01", true).forEach((VOListadoBoletos) -> {
//				System.out.println("Nombre: " + VOListadoBoletos.getNombre());
//				System.out.println("Edad: " + VOListadoBoletos.getEdad());
//				System.out.println("Celular: " + VOListadoBoletos.getCelular());
//				System.out.println("Descuento: " + VOListadoBoletos.getDescuento());
//				System.out.println("Numero de Boleto: " + VOListadoBoletos.getNumeroBoleto());
//			});
//		} catch (PaseoNoExiste e) {
//			e.printStackTrace();
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("FIN // Prueba Listado Boleto");
//
//		System.out.println("");
//		System.out.println("INICIO //  (Req 9) Monto Recaudado en un Paseo\n");
//
//		try {
//			System.out.println(
//					"El monto recaudado para el paseo: PDE02 es: " + f.MontoRecaudadoPorPaseo("PDE01").toString());
//		} catch (PaseoNoExiste e) {
//			e.printStackTrace();
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("FIN //  (Req 9) Monto Recaudado en un Paseo");
//
//		try {
//			f.RespaldarDatos();
//		} catch (PersistenciaException e) {
//			System.out.println("Error al recuperar datos: " + e.darMensaje());
//
//		}catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
	}
}
