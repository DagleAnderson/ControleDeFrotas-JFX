package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ModeloDao;
import model.entities.Modelo;

public class ModeloService {
	
	private ModeloDao modDao = DaoFactory.createModeloDao();
	
	public List<Modelo> findAll(){
		return modDao.findAll();
	}
}
