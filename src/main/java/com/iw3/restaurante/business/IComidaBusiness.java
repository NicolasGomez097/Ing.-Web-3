package com.iw3.restaurante.business;

import java.util.List;
import com.iw3.restaurante.model.Comida;

public interface IComidaBusiness {
	
	public List<Comida> list() throws BusinessException;

	public Comida load(int idComida) throws BusinessException, NotFoundException;

	public Comida save(Comida Comida) throws BusinessException;

	public void remove(int idComida) throws BusinessException, NotFoundException;
}
