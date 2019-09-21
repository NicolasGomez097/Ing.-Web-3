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
import org.springframework.web.bind.annotation.RestController;

import com.iw3.restaurante.business.BusinessException;
import com.iw3.restaurante.business.IComidaBusiness;
import com.iw3.restaurante.business.NotFoundException;
import com.iw3.restaurante.model.Comida;
import com.iw3.restaurante.utils.Constantes;

@RestController
@RequestMapping(Constantes.URL_BASE_COMIDAS)
public class ComidaRestController {
	
	@Autowired
	private IComidaBusiness comidasBO;
	
	@GetMapping("")
	public ResponseEntity<List<Comida>> list() {
		try {
			return new ResponseEntity<List<Comida>>(comidasBO.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Comida>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Comida> load(@PathVariable("id") int idcomida) {
		try {
			return new ResponseEntity<Comida>(comidasBO.load(idcomida), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Comida>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Comida>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> insert(@RequestBody Comida comida) {
		try {
			comidasBO.save(comida);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_COMIDAS + "/" + comida.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody Comida comida) {
		try {
			comidasBO.save(comida);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int idComida) {
		try {
			comidasBO.remove(idComida);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/menor_mayor_precio_restaurante")
	public ResponseEntity<String> orderByPriceAndRestaurante(@PathVariable("orden") String orden, @PathVariable("restaurante") String restaurante) {
		try {
			comidasBO.orderByPriceAndRestaurante(orden, restaurante);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

}
