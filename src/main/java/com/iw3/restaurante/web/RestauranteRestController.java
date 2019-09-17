package com.iw3.restaurante.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iw3.restaurante.business.BusinessException;
import com.iw3.restaurante.business.IRestauranteBusiness;
import com.iw3.restaurante.business.NotFoundException;
import com.iw3.restaurante.model.Restaurante;
import com.iw3.restaurante.utils.Constantes;

@RestController
@RequestMapping(Constantes.URL_BASE_RESTAURANTES)
public class RestauranteRestController {
	
	@Autowired
	private IRestauranteBusiness restaurantesBO;
	
	@GetMapping("")
	public ResponseEntity<List<Restaurante>> list() {
		try {
			return new ResponseEntity<List<Restaurante>>(restaurantesBO.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Restaurante>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Restaurante> load(@PathVariable("id") int idRestaurante) {
		try {
			return new ResponseEntity<Restaurante>(restaurantesBO.load(idRestaurante), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Restaurante>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Restaurante>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> insert(@RequestBody Restaurante restaurante) {
		try {
			if(restaurante == null)
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
			restaurantesBO.save(restaurante);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_RESTAURANTES + "/" + restaurante.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody Restaurante restaurante) {
		try {
			restaurantesBO.save(restaurante);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int idRestaurante) {
		try {
			restaurantesBO.remove(idRestaurante);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping(value ="/best_rating")
	public ResponseEntity<Restaurante> findFirstOrderByPuntuacion() {
		try {
			return new ResponseEntity<Restaurante>(restaurantesBO.findFirstOrderByPuntuacion(),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Restaurante>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Restaurante>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value ="/is_open")
	public ResponseEntity<List<Restaurante>> findByHoraAperturaGreaterThanAndHoraCierreLessThan(@RequestParam("hora") String hora) {
		try {
			return new ResponseEntity<List<Restaurante>>(restaurantesBO.findByHoraAperturaGreaterThanAndHoraCierreLessThan(hora),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	

}
