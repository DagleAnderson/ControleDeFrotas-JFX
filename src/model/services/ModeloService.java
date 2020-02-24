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
	
	public void SaveOrUpdate(Modelo obj) {
		if(obj.getId() == null){
			modDao.insert(obj);
		}else {
			modDao.update(obj);
		}
	}
	
}
