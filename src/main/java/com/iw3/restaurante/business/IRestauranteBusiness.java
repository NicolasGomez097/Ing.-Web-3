package com.iw3.restaurante.business;

import java.util.List;
import com.iw3.restaurante.model.Restaurante;

public interface IRestauranteBusiness {
	public List<Restaurante> list() throws BusinessException;

	public Restaurante load(int idRestaurante) throws BusinessException, NotFoundException;

	public Restaurante save(Restaurante restaurante) throws BusinessException;

	public void remove(int idRestaurante) throws BusinessException, NotFoundException;
}
