package unsm.archivo.entitys;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Resolucion
{
	@Id
	String nrodoc;
	
	@Column
	String titulo;
	String estado;
	LocalDate fecha;
	Integer duracion;
	LocalDate vencimiento;
	String link;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name= "idtipocriterio")
	Tipocriterio idtipocriterio;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name= "idusuario")
	Usuario idusuario;
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getNrodoc() {
		return nrodoc;
	}
	public void setNrodoc(String doc) {
		this.nrodoc = doc;
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
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {							
		this.fecha = fecha;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public LocalDate getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(LocalDate vencimiento) {
		this.vencimiento = vencimiento;
	}
	public Tipocriterio getIdtipocriterio() {
		return idtipocriterio;
	}
	public void setIdtipocriterio(Tipocriterio idtipocriterio) {
		this.idtipocriterio = idtipocriterio;
	}
	public Usuario getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(Usuario idusuario) {
		this.idusuario = idusuario;
	}
}
