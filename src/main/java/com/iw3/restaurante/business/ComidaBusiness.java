package com.iw3.restaurante.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iw3.restaurante.model.Comida;
import com.iw3.restaurante.persistance.ComidaRepository;

@Service
public class ComidaBusiness implements IComidaBusiness {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ComidaRepository comidaDAO;

	@Override
	public List<Comida> list() throws BusinessException {
		try {
			return comidaDAO.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}

	@Override
	public Comida load(int idComida) throws BusinessException, NotFoundException {
		Optional<Comida> op = null;
		try {
			op = comidaDAO.findById(idComida);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent())
			throw new NotFoundException("No se encuentra la comida con id= " + idComida);
		return op.get();
	}

	@Override
	public Comida save(Comida comida) throws BusinessException {
		try {
			return comidaDAO.save(comida);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void remove(int idComida) throws BusinessException, NotFoundException {
		
		
		Optional<Comida> op = null;
		try {
			op = comidaDAO.findById(idComida);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

		if (!op.isPresent())
			throw new NotFoundException("No se encuentra la Comida con id= " + idComida);
		try {
			comidaDAO.deleteById(idComida);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
