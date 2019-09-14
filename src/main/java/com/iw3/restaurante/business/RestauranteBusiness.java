package com.iw3.restaurante.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iw3.restaurante.model.Restaurante;
import com.iw3.restaurante.persistance.RestauranteRepository;

@Service
public class RestauranteBusiness implements IRestauranteBusiness {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestauranteRepository restauranteDAO;

	@Override
	public List<Restaurante> list() throws BusinessException {
		
		try {
			return restauranteDAO.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}

	@Override
	public Restaurante load(Integer idRestaurante) throws BusinessException, NotFoundException {
		Optional<Restaurante> op = null;
		try {
			op = restauranteDAO.findById(idRestaurante);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		if (!op.isPresent())
			throw new NotFoundException("No se encuentra el restaurante con id=" + idRestaurante);
		return op.get();
	}

	@Override
	public Restaurante save(Restaurante restaurante) throws BusinessException {
		try {
			return restauranteDAO.save(restaurante);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}

	@Override
	public void remove(Integer idRestaurante) throws BusinessException, NotFoundException {
		Optional<Restaurante> op = null;

		try {
			op = restauranteDAO.findById(idRestaurante);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}

		if (!op.isPresent())
			throw new NotFoundException("No se encuentra el restaurante con id=" + idRestaurante);
		try {
			restauranteDAO.deleteById(idRestaurante);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}

}
