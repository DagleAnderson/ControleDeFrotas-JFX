package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ViagemDao;
import model.entities.Viagem;

public class ViagemService {
		
	ViagemDao viagemDao = DaoFactory.createViagemDao();
	
	public void insertOrUpdate(Viagem obj) {
		if(obj.getId() == null){
			viagemDao.insert(obj);
		}else {
			viagemDao.update(obj);
		}
	}
	
	public void delete(Integer id) {
	  viagemDao.delete(id);
	}
	
	public List<Viagem> findAll() {
		return viagemDao.findAll();
	}
	
	public Viagem findById(Integer id) {
		return viagemDao.findById(id);
	}
	
	
}

