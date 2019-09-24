package com.iw3.restaurante.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iw3.restaurante.model.Restaurante;
import com.iw3.restaurante.persistance.RestauranteRepository;
import com.iw3.restaurante.utils.Tiempo;
import com.iw3.restaurante.utils.TiempoConverter;

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
		Restaurante res;
		Optional<Restaurante> aux = null;
		boolean isNew = restaurante.getId() == null;
		
		try {
			res = restauranteDAO.save(restaurante);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		
		if(!isNew) 			
			log.info("UPDATE-RESTAURANTE, objeto nuevo:"+restaurante.toString());
		
		else 
			log.info("INSERT-RESTAURANTE, objeto nuevo:"+restaurante.toString());
			
		return res;
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
		log.info("REMOVE-RESTAURANTE, id:"+idRestaurante);
	}

	@Override
	public List<Restaurante> findBestRating() throws BusinessException, NotFoundException{
		Optional<List<Restaurante>> res = null;
		Optional<Restaurante> op;
		try {
			op = restauranteDAO.findFirstByOrderByPuntuacionDesc();
			
			res = restauranteDAO.findByPuntuacion(op.get().getPuntuacion());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		if (!res.isPresent())
			throw new NotFoundException("No se encuentra ningún restaurante");
		return res.get();
	}

	@Override
	public List<Restaurante> findByHoraAperturaGreaterThanAndHoraCierreLessThan(String hora) throws BusinessException, NotFoundException {
		List<Restaurante> op = null;
		
		TiempoConverter tc = new TiempoConverter();
		Tiempo tiempo = tc.convertToEntityAttribute(hora);
		if(!tiempo.isValid()) {
			throw new BusinessException("El tiempo no es valido");
		}
		
		
		try {
			op = restauranteDAO.findAll();
			
			for(int i = 0; i < op.size(); i++) {
				Restaurante res =  op.get(i);
				Tiempo apertura = res.getHoraApertura();
				Tiempo cierre = res.getHoraCierre();
				if(
						(apertura.compareTo(cierre) < 0 &&
								(apertura.compareTo(tiempo) > 0 || cierre.compareTo(tiempo) <= 0)
						)
						|| (apertura.compareTo(cierre) > 0
								&& cierre.compareTo(tiempo) <= 0 
								&& apertura.compareTo(tiempo) > 0)
				){
					op.remove(i);
					i--;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}		
		
		return op;
	}
	
	@Override
	public List<String> getDireccionRestaurante(String nombre)  throws BusinessException, NotFoundException{
		Optional<List<Restaurante>> aux;
		List<String> salida = new ArrayList<String>();
		nombre = "%" + nombre + "%";
		try {
			aux = restauranteDAO.findByNombreLike(nombre);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		
		if (!aux.isPresent())
			throw new NotFoundException("No se encuentra ningún restaurante con el nombre " + nombre);
		
		for(int i=0; i < aux.get().size();i++) {
			salida.add(aux.get().get(i).getNombre() + ": " + aux.get().get(i).getDireccion());
		}
		
		return salida;
	}

	@Override
	public List<Restaurante> findByComidasNombreLike(String nombre) throws BusinessException, NotFoundException {
		Optional<List<Restaurante>> op = null;
		try {
			op = restauranteDAO.findByComidasNombreLike('%'+nombre+'%');
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		
		if (!op.isPresent())
			throw new NotFoundException("No se encuentra ningún restaurante");
		
		
		return op.get();
	}

}
