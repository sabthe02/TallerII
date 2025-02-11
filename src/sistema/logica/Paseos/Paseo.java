package sistema.logica.Paseos;

import sistema.logica.Boletos.*;

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
	return precioBase;
}
public void setPrecioBase(double precioBase) {
	this.precioBase = precioBase;
}
public String getDestino() {
	return destino;
}
public void setDestino(String destino) {
	this.destino = destino;
}

public static void main (String args[]) {
	
	Paseo p1 = new Paseo ("PDP1", "09:00", "13:00", 5, 7, 15.0, "Punta del Este");
	
	p1.boletos = new Boletos (p1.getCantVendidos());
	System.out.println(p1.getCodigo());
	System.out.println(p1.getHoraPartida());
	System.out.println(p1.getHoraRegreso());
	System.out.println(p1.getCantVendidos());
	System.out.println(p1.getCantMaxBoletos());
	System.out.println(p1.getPrecioBase());
	System.out.println(p1.getDestino());
}

}
