package sistema.logica.ValueObject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class VOPaseo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String Codigo;
	private LocalTime HoraPartida;
	private LocalTime HoraRegreso;
	private Double PrecioBase;
	private String Destino;
	
	public VOPaseo() {
		// TODO Auto-generated constructor stub
		this.Codigo = "";
		this.HoraPartida = LocalTime.now();
		this.HoraRegreso = LocalTime.now();
		this.PrecioBase = 0.0;
		this.Destino = "";
	}

	public VOPaseo(String codigo, LocalTime horaPartida, LocalTime horaRegreso, Double precioBase, String destino) {
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

	public LocalTime getHoraPartida() {
		return HoraPartida;
	}

	public void setHoraPartida(LocalTime horaPartida) {
		HoraPartida = horaPartida;
	}

	public LocalTime getHoraRegreso() {
		return HoraRegreso;
	}

	public void setHoraRegreso(LocalTime horaRegreso) {
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
