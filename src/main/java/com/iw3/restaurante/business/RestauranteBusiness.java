package com.iw3.restaurante.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iw3.restaurante.model.Restaurante;
import com.iw3.restaurante.persistance.RestauranteRepository;

public class RestauranteBusiness implements IRestauranteBusiness {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestauranteRepository restauranteDAO;

	@Override
	public List<Restaurante> list() throws BusinessException {
		return restauranteDAO.findAll();
	}

	@Override
	public Restaurante load(int idRestaurante) throws BusinessException, NotFoundException {
		return null;
	}

	@Override
	public Restaurante save(Restaurante restaurante) throws BusinessException {
		return null;
	}

	@Override
	public void remove(int idRestaurante) throws BusinessException, NotFoundException {
	}

}
