package sistema.logica.Boletos;

import sistema.logica.VO.*;
import java.util.ArrayList;

public class Boletos {

	private 
	int tope;
	Boleto boletos [];
	
	public Boletos(int tope, Boleto[] boletos) {
		this.tope = tope;
		this.boletos = boletos;
	}
	
	public Boletos(int tope) {
		this.tope = tope;
		this.boletos = new Boleto [tope-1];
	}
	
	public Boletos () {
		this.tope = 0;
	}

	public int getTope() {
		return tope;
	}

	public void setTope(int tope) {
		this.tope = tope;
	}

	public Boleto[] getBoletos() {
		return boletos;
	}

	public void setBoletos(Boleto[] boletos) {
		this.boletos = boletos;
	}
	
	public void insBack (Boleto bol) {
		boletos[tope] = bol;
		tope++;
	}
	
	public boolean esVacia () {
		boolean es = false;
		if (this.tope == 0) {
			es = true;
		}
		return es;
	}
	
	public Boleto primero () {
		return boletos[0];
	}
	
	public Boleto ultimo () {
		return boletos[tope-1];
	}
	public int largo () {
		return tope;
	}
	
	public double montoRecaudado () {
		double Monto = 0;
		for (int i =0; i <this.tope;i++) {
			double MontoBoleto = boletos[i].getPrecio()*boletos[i].getDescuento();
			if (boletos[i] instanceof Boleto) {
				MontoBoleto = MontoBoleto*((BoletoEsp)boletos[i]).getDescuentoEsp();
			}
			Monto = Monto + MontoBoleto;
		}
		return Monto;
	}
	
//	public VOListadoBoletosL listadoBoleto(String codigo, boolean esEsp) {
//		ArrayList<VOListadoBoletos> VOListadoBoletosL = new ArrayList<VOListadoBoletos>();
//		
//		for (int i = 0; i < this.tope; i++) {
//			Boleto bol = boletos[i];
//			if ((bol instanceof Boleto) && (esEsp == true)) {
//				VOListadoBoletos VO = new VOListadoBoletos(bol.getNombrePasajero(), bol.getEdad(), bol.getNumeroCel(), bol.getNumeroBoleto(), esEsp, ((BoletoEsp)bol).getDescuento());
//			}
//			else {
//				VOListadoBoletos VO = new VOListadoBoletos(bol.getNombrePasajero(), bol.getEdad(), bol.getNumeroCel(), bol.getNumeroBoleto(), esEsp);
//			}
//			VOListadoBoletosL.add(VO);
//			}
//		}
//	}
//	
	public static void main (String args[]) {
		Boletos B = new Boletos();
		Boleto b1 = new Boleto(1, "Persona1", 17, "099000000", 15.0, 0.75);
		B.insBack(b1);
		if (!(B.esVacia())) {
			System.out.println("Es vacia");
		}
		else {
			System.out.println("NO es vacia");
		}
		
		Boleto b2 = new Boleto(2, "Persona2", 34, "099000001", 20.0, 1);
		B.insBack(b2);
		
		
	}

}
