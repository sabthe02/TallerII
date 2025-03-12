package sistema.logica.Paseos;

import java.util.ArrayList;


import sistema.logica.Boletos.*;
import sistema.logica.ValueObject.VOCompraBoleto;
//import sistema.logica.Minivanes.*;
//import sistema.logica.VO.*;
import sistema.logica.ValueObject.VOListadoBoletos;

import java.time.LocalTime;
import java.io.Serializable;

public class Paseo implements Serializable {
	
private static final long serialVersionUID = 1L; 

	
private String codigo;
		LocalTime horaPartida; 
		LocalTime horaRegreso;
		int cantVendidos;
		int cantMaxBoletos;
		double precioBase;
		String destino;
		Boletos boletos;
		
public Paseo(String codigo, LocalTime horaPartida, LocalTime horaRegreso, int cantVendidos,
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

public Paseo() {
	this.codigo = "A0";
	this.horaPartida = LocalTime.now();
	this.horaRegreso = LocalTime.now();
	this.cantVendidos = 0;
	this.cantMaxBoletos = 0;
	this.precioBase = 0.0;
	this.destino = "";
	this.boletos = new Boletos (this.getCantMaxBoletos());
}

public String getCodigo () {
	return this.codigo;
}

public LocalTime getHoraPartida() {
	return horaPartida;
}

public void setHoraPartida(LocalTime horaPartida) {
	this.horaPartida = horaPartida;
}

public LocalTime getHoraRegreso() {
	return horaRegreso;
}

public void setHoraRegreso(LocalTime horaRegreso) {
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

public int getCantidadBoletosDisponibles()
{
	return this.cantMaxBoletos - this.cantVendidos;
}

public void compraBoleto (VOCompraBoleto bol)  {
	
	Boleto k;
	if(bol.isEsEspecial())
	{
		k = new BoletoEsp(this.cantVendidos, bol.getNombre(), bol.getEdad(), bol.getCelular(), this.getPrecioBase(), bol.getDescuento());
	}else {
		k = new Boleto(this.cantVendidos, bol.getNombre(), bol.getEdad(), bol.getCelular(), this.getPrecioBase());
	}
	
	this.boletos.insBack(k);
	this.cantVendidos++;
	
}


public ArrayList<VOListadoBoletos> listadoBoletosPaseo(boolean esEsp) {
	
return (this.getBoletosP().listadoBoleto(esEsp));


}

public double montoRecaudadoPaseo () {
	return boletos.montoRecaudado();
}


}
