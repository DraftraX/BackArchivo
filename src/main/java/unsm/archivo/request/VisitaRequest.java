package unsm.archivo.request;

public class VisitaRequest 
{
	String fecha;
	String nombre;
	String ocupacion;
	String motivo;
	String numerocorreo;
	String username;
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getNumerocorreo() {
		return numerocorreo;
	}
	public void setNumerocorreo(String numerocorreo) {
		this.numerocorreo = numerocorreo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}