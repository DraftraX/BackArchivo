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
}
