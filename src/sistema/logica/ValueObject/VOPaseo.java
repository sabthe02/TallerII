package sistema.logica.ValueObject;

import java.sql.Time;

public class VOPaseo {

	private String Codigo;
	private Time HoraPartida;
	private Time HoraRegreso;
	private Double PrecioBase;
	private String Destino;
	
	public VOPaseo() {
		// TODO Auto-generated constructor stub
		this.Codigo = "";
		this.HoraPartida = new Time(1);
		this.HoraRegreso = new Time(1);
		this.PrecioBase = 0.0;
		this.Destino = "";
	}

	public VOPaseo(String codigo, Time horaPartida, Time horaRegreso, Double precioBase, String destino) {
		super();
		Codigo = codigo;
		HoraPartida = horaPartida;
		HoraRegreso = horaRegreso;
		PrecioBase = precioBase;
		Destino = destino;
	}

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
		Codigo = codigo;
	}

	public Time getHoraPartida() {
		return HoraPartida;
	}

	public void setHoraPartida(Time horaPartida) {
		HoraPartida = horaPartida;
	}

	public Time getHoraRegreso() {
		return HoraRegreso;
	}

	public void setHoraRegreso(Time horaRegreso) {
		HoraRegreso = horaRegreso;
	}

	public Double getPrecioBase() {
		return PrecioBase;
	}

	public void setPrecioBase(Double precioBase) {
		PrecioBase = precioBase;
	}

	public String getDestino() {
		return Destino;
	}

	public void setDestino(String destino) {
		Destino = destino;
	}
	
	
	
	
}
