package com.iw3.restaurante.business;

import java.util.List;
import com.iw3.restaurante.model.Restaurante;

public interface IRestauranteBusiness {
	public List<Restaurante> list() throws BusinessException;

	public Restaurante load(Integer idRestaurante) throws BusinessException, NotFoundException;

	public Restaurante save(Restaurante restaurante) throws BusinessException;

	public void remove(Integer idRestaurante) throws BusinessException, NotFoundException;
	
	public Restaurante findFirstOrderByPuntuacion() throws BusinessException, NotFoundException;
	
	public List<Restaurante> findByHoraAperturaGreaterThanAndHoraCierreLessThan(String hora) throws BusinessException, NotFoundException;
	
	public List<String> getDireccionRestaurante(String nombre) throws BusinessException, NotFoundException;
}
