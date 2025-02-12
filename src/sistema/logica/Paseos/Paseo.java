package sistema.logica.Paseos;

import java.util.ArrayList;

import sistema.logica.Boletos.*;
//import sistema.logica.Minivanes.*;
//import sistema.logica.VO.*;
//import java.util.ArrayList;

public class Paseo {
	
private String codigo;
		String horaPartida; // Tratar de ver cómo se pondría en Hora, trate con Simpledate, pero a la hora de asignarla no sé cómo hacer :-(
		String horaRegreso; // Tratar de ver cómo se pondría en Hora, trate con Simpledate, pero a la hora de asignarla no sé cómo hacer :-(
		int cantVendidos;
		int cantMaxBoletos;
		double precioBase;
		String destino;
		Boletos boletos;
		
public Paseo(String codigo, String horaPartida, String horaRegreso, int cantVendidos,
				int cantMaxBoletos, double precioBase, String destino) {
			this.codigo = codigo;
			this.horaPartida = horaPartida;
			this.horaRegreso = horaRegreso;
			this.cantVendidos = cantVendidos;
			this.cantMaxBoletos = cantMaxBoletos;
			this.precioBase = precioBase;
			this.destino = destino;
			this.boletos = new Boletos (this.getCantMaxBoletos());
}
public String getCodigo () {
	return this.codigo;
}

public String getHoraPartida() {
	return horaPartida;
}
public void setHoraPartida(String horaPartida) {
	this.horaPartida = horaPartida;
}
public String getHoraRegreso() {
	return horaRegreso;
}
public void setHoraRegreso(String horaRegreso) {
	this.horaRegreso = horaRegreso;
}
public int getCantVendidos() {
	return cantVendidos;
}
public void setCantVendidos(int cantVendidos) {
	this.cantVendidos = cantVendidos;
}
public int getCantMaxBoletos() {
	return cantMaxBoletos;
}
public void setCantMaxBoletos(int cantMaxBoletos) {
	this.cantMaxBoletos = cantMaxBoletos;
}
public double getPrecioBase() {
	return this.precioBase;
}
public void setPrecioBase(double precioBase) {
	this.precioBase = precioBase;
}
public String getDestino() {
	return this.destino;
}
public void setDestino(String destino) {
	this.destino = destino;
}

public Boletos getBoletosP() {
	return this.boletos;
}
public void setBoletos(Boletos boletos) {
	this.boletos = boletos;
}

//public void compraBoleto (VOCompraBoleto VO)  { // Cómo hacer en caso de los errores?
// Acá le tengo que agregar algo si recién los quiero lanzar en la fachada?
//
//	if (this.getCantMaxBoletos() - this.getCantVendidos() > 0) {
//		if (VO.getDescuento() >0) {
//			if (VO.getEdad <= 18) {
//				Boleto b1 = new BoletoEsp (this.getCantVendidos()+1, VO.getNombre(), VO.getEdad(), VO.getCelular(),  VO.getPrecio(), 0.75, VO.getDescuento());
//			}
//			else {
//				Boleto b1 = new BoletoEsp (this.getCantVendidos()+1, VO.getNombre(), VO.getEdad(), VO.getCelular(),  VO.getPrecio(), 1, VO.getDescuento());
//			}		
//		}
//		else  {
//			if (VO.getEdad <= 18) {
//			Boleto b1 = new Boleto(this.getCantVendidos()+1, VO.getNombre(), VO.getEdad(), VO.getCelular(),  VO.getPrecio(), 0.75);
//			}
//			else {
//				Boleto b1 = new Boleto(this.getCantVendidos()+1, VO.getNombre(), VO.getEdad(), VO.getCelular(),  VO.getPrecio(), 1);
//			}
//		}
//	this.getBoletosP().insBack(b1); // por qué no reconoce a b1 acá, porque hay if-else? Cómo hacer?
//	this.setCantVendidos(this.cantVendidos+1);
//	
//	}
//}


public VOListadoBoletos[] listadoBoleto(String codigo, boolean esEsp) {
	
ArrayList<VOListadoBoletos> VOListadoBoletosL = new ArrayList<VOListadoBoletos>();


}


public static void main (String args[]) {
	
	Paseo p1 = new Paseo ("PDP1", "09:00", "13:00", 5, 7, 15.0, "Punta del Este");
	
	System.out.println(p1.getCodigo());
	System.out.println(p1.getHoraPartida());
	System.out.println(p1.getHoraRegreso());
	System.out.println(p1.getCantVendidos());
	System.out.println(p1.getCantMaxBoletos());
	System.out.println(p1.getPrecioBase());
	System.out.println(p1.getDestino());
}

}
