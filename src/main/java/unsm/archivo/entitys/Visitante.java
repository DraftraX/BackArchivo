package unsm.archivo.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Visitante 
{
	@Id
	@GeneratedValue
	Integer id;
	
	@Column
	String fecha;
	String nombre;
	String ocupacion;
	String motivo;
	String numerocorreo;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name= "idusuario")
	Usuario idusuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Usuario getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Usuario idusuario) {
		this.idusuario = idusuario;
	}
}
