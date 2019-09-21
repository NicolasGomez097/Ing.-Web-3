package com.iw3.restaurante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iw3.restaurante.persistance.RestauranteRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	private RestauranteRepository restaurantesBO;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);		
	}
	
	@Override
	public void run(String... args) throws Exception {
		/*Restaurante r = new Restaurante();
		r.setNombre("Johnny B Good");
		r.setDireccion("Av. Hipólito Yrigoyen 320");
		r.setHoraApertura(new Tiempo("08:00"));
		r.setHoraCierre(new Tiempo("13:00"));
		r.setPuntuacion(4.2);
		
		restaurantesBO.save(r);	*/
		//Tiempo a = new Tiempo("08:00:00");
		//Tiempo b = new Tiempo("02:00:00");
	}

}
