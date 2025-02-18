package sistema.logica.ValueObject;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VOPaseo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String Codigo;
	private LocalDateTime HoraPartida;
	private LocalDateTime HoraRegreso;
	private Double PrecioBase;
	private String Destino;
	
	public VOPaseo() {
		// TODO Auto-generated constructor stub
		this.Codigo = "";
		this.HoraPartida = LocalDateTime.of(LocalDateTime.now().
							getYear(),LocalDateTime.now().
							getMonth(),LocalDateTime.now().
							getDayOfMonth(),
							1,0);
		this.HoraRegreso = LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(),LocalDateTime.now().getDayOfMonth(),1,0);
		this.PrecioBase = 0.0;
		this.Destino = "";
	}

	public VOPaseo(String codigo, LocalDateTime horaPartida, LocalDateTime horaRegreso, Double precioBase, String destino) {
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

	public LocalDateTime getHoraPartida() {
		return HoraPartida;
	}

	public void setHoraPartida(LocalDateTime horaPartida) {
		HoraPartida = horaPartida;
	}

	public LocalDateTime getHoraRegreso() {
		return HoraRegreso;
	}

	public void setHoraRegreso(LocalDateTime horaRegreso) {
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
