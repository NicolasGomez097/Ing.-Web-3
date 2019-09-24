package com.iw3.restaurante.business;

import java.util.ArrayList;
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
	private Comida comidaAux = null;
	private List<Comida> listComidaAux;
	
	@Autowired
	private ComidaRepository comidaDAO;

	@Override
	public List<Comida> list() throws BusinessException {
		try {
			listComidaAux= comidaDAO.findAll();
			
			return listComidaAux;
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
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("No se encuentra la comida con id= " + idComida);
		}
		return op.get();
	}

	@Override
	public Comida save(Comida comida) throws BusinessException {				
		boolean isNew = comida.getId() == null;
		
		try {
			comidaAux = comidaDAO.save(comida);
			
			if(!isNew) 
				log.info("UPDATE-COMIDA, objeto: "+ comidaAux.toString());
			
			else
				log.info("INSERT-COMIDA, objeto:" + comidaAux.toString());
				
			
			return comidaAux;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}

	@Override
	public void remove(int idComida) throws BusinessException, NotFoundException {		
		
		Optional<Comida> op = null;
		try {
			op = comidaDAO.findById(idComida);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}

		if (!op.isPresent()) {
			throw new NotFoundException("No se encuentra la Comida con id= " + idComida);
		}
		try {
			log.info("REMOVE-COMIDA, id: "+idComida);
			comidaDAO.deleteById(idComida);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}
	
	@Override
	public List<Comida> orderByPriceAndRestaurante(String orden, String restaurante)
			throws BusinessException, NotFoundException {
		Optional<List<Comida>> op = null;
		Optional<Comida> opComida = null;
		List<Comida> list = new ArrayList<>();
		
		try {
			switch(orden) {
			
			case "menor":
				
				if(restaurante.equals("ALL")) { 
					op= comidaDAO.findAllByOrderByPrecioAsc();
					list = op.get();
				}
				else {
					opComida= comidaDAO.findFirstByRestauranteNombreOrderByPrecioAsc(restaurante);
					list.add(opComida.get());
				}
				break;
				
				
			case "mayor":
				
				if(restaurante.equals("ALL")) {
					op= comidaDAO.findAllByOrderByPrecioDesc();
					list = op.get();
				}
				else {
					opComida= comidaDAO.findFirstByRestauranteNombreOrderByPrecioDesc(restaurante);
					list.add(opComida.get());
				}
				break;

			default:
				log.error("BAD-REQUEST, orden:"+orden);
				throw new BusinessException("Bad request");
			}
			
			
			if (list.isEmpty()) {
				throw new NotFoundException("No se encuentra la comida con orden = "+orden+" y restaurante = "+restaurante );			
			}
		}
		catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}	
		return list;
		
	}

	@Override
	public List<Comida> findComidasByRestaurante(String nombre) throws BusinessException, NotFoundException{
		Optional<List<Comida>> op = null;
		try {
			op = comidaDAO.findAllByRestauranteNombreOrderByNombreDesc(nombre);
			if(!op.isPresent()) {
				log.warn("No se encontro lista de comidas para el restaurante = "+nombre);
				throw new NotFoundException("No se encontro lista de comidas para el restaurante = "+nombre);
			}
			else
			return op.get(); 
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}

}
