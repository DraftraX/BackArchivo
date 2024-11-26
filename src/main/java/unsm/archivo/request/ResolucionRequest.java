package unsm.archivo.request;

public class ResolucionRequest 
{
	String nrodoc;
	String titulo;
	String estado;
	String fecha;
	Integer duracion;
	String tipoResolucion;
	Integer idtipocriterio;
	String link;
	String usuario;
	
	public String getNrodoc() {
		return nrodoc;
	}
	public void setNrodoc(String nrodoc) {
		this.nrodoc = nrodoc;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public String getTipoResolucion() {
		return tipoResolucion;
	}
	public void setTipoResolucion(String tipoResolucion) {
		this.tipoResolucion = tipoResolucion;
	}
	public Integer getIdtipocriterio() {
		return idtipocriterio;
	}
	public void setIdtipocriterio(Integer idtipocriterio) {
		this.idtipocriterio = idtipocriterio;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}