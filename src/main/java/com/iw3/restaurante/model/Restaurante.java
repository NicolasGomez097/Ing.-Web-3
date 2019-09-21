package com.iw3.restaurante.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iw3.restaurante.utils.Tiempo;
import com.iw3.restaurante.utils.TiempoConverter;

@Entity
@Table(name = "restaurante")
public class Restaurante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 20)
	private String nombre;
	
	@Column(length = 30)
	private String direccion;
	
	@Column(length = 8)
	@Convert(converter = TiempoConverter.class)
	private Tiempo horaApertura;
	
	@Column(length = 8)
	@Convert(converter = TiempoConverter.class)
	private Tiempo horaCierre;
	@Column
	private double puntuacion;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
	private List<Comida> comidas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Tiempo getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(Tiempo horaApertura) {
		this.horaApertura = horaApertura;
	}

	public Tiempo getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(Tiempo horaCierre) {
		this.horaCierre = horaCierre;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public List<Comida> getComidas() {
		return comidas;
	}

	public void setComidas(List<Comida> comidas) {
		this.comidas = comidas;
	}
	
	
}
