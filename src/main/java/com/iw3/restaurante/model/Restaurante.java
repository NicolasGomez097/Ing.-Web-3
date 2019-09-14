package com.iw3.restaurante.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	private double puntuacion;

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
}
