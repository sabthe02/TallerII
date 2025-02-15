package sistema.logica.Boletos;

import sistema.logica.ValueObject.*;
import java.util.ArrayList;

public class Boletos {

	private 
	int tope;
	Boleto boletos [] ;
	
	public Boletos(Boletos boletos) {
		this.tope = boletos.getTope();
		this.boletos = boletos.getBoletos();
	}
	
	public Boletos(int largo) {
		this.tope = 0;
		this.boletos = new Boleto [largo];
	}
	
	public Boletos() {
		this.tope = 0;
		this.boletos = new Boleto [80];
	}
	
	public int getTope() {
		return this.tope;
	}

	public void setTope(int tope) {
		this.tope = tope;
	}

	public Boleto[] getBoletos() {
		return this.boletos;
	}

	public void setBoletos(Boletos boletos) {
		this.boletos = boletos.getBoletos();
		this.tope = boletos.getTope();
	}
	
	public void insBack (Boleto bol) {
		this.boletos[tope] = bol;
		this.tope++;
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
	
	public Boleto kesimo (int posicion) {
		return boletos[posicion];
	}
	
	public int largo () {
		return tope;
	}
	
	public double montoRecaudado () {
		double Monto = 0;
		for (int i =0; i <this.tope;i++) {
			double MontoBoleto = boletos[i].getPrecio();
				if (boletos[i].getEdad()<=18) {
					MontoBoleto = MontoBoleto*0.75;
				}
			if (boletos[i] instanceof BoletoEsp) {
				MontoBoleto = MontoBoleto*((BoletoEsp)boletos[i]).getDescuentoEsp();
			}
			Monto = Monto + MontoBoleto;
		}
		return Monto;
	}
	
	public ArrayList<VOListadoBoletos> listadoBoleto(boolean esEsp) {
		ArrayList<VOListadoBoletos> VOListadoBoletosL = new ArrayList<VOListadoBoletos>();
		
		for (int i = 0; i < this.tope; i++) {
			VOListadoBoletos VO;
			Boleto bol = boletos[i];
			if ((bol instanceof BoletoEsp) && (esEsp == true)) {
				VO = new VOListadoBoletos(bol.getNombrePasajero(), bol.getEdad(), bol.getNumeroCel(), true, ((BoletoEsp)bol).getDescuentoEsp(), bol.getNumeroBoleto());
				VOListadoBoletosL.add(VO);
			}
			else {
				if (esEsp == false &&  !(bol instanceof BoletoEsp)) {		
					VO = new VOListadoBoletos(bol.getNombrePasajero(), bol.getEdad(), bol.getNumeroCel(), false, 0.0, bol.getNumeroBoleto());
					VOListadoBoletosL.add(VO);
				}
			}
		}
		return VOListadoBoletosL;
	}
	

	
	public static void main (String args[]) {
		Boletos B = new Boletos(13);
		Boleto b1 = new Boleto(1, "Persona1", 17, "099000000", 15.0);
		B.insBack(b1);
		if (!(B.esVacia())) {
			System.out.println("NO es vacia");
		}
		else {
			System.out.println("Es vacia");
		}
		
		Boleto b2 = new BoletoEsp(2, "Persona2", 34, "099000001", 20.0, 0.8);
		B.insBack(b2);
		
		for (int i =0; i<(B.getTope()); i++) {
			System.out.println(B.kesimo(i).getNombrePasajero());
			System.out.println(B.kesimo(i).getEdad());
			System.out.println(B.kesimo(i).getNumeroBoleto());
			System.out.println(B.kesimo(i).getNumeroCel());
			System.out.println(B.kesimo(i).getPrecio());
			if (B.kesimo(i) instanceof BoletoEsp) {
				System.out.println(((BoletoEsp)B.kesimo(i)).getDescuentoEsp());
			}
			else {
				System.out.println("No es especial");
			}
			
		}
		double m = B.montoRecaudado();
		System.out.println("Monto recaudado: " + m);
		
		B.listadoBoleto(true).forEach((VOListadoBoletos)-> { 
			System.out.println(VOListadoBoletos.getNombre());
			System.out.println(VOListadoBoletos.getEdad());
			System.out.println(VOListadoBoletos.getCelular());
			System.out.println(VOListadoBoletos.isEsEspecial());
			System.out.println(VOListadoBoletos.getNumeroBoleto());
			System.out.println(VOListadoBoletos.getDescuento());
		}
		);
		

	}
}