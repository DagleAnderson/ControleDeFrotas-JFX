package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.MotoristaDao;
import model.entities.Motorista;

public class MotoristaService {
		
	MotoristaDao motDao = DaoFactory.createMotoristaDao();

	public void saveOrUpdate(Motorista obj) {
		if(obj.getId() == null) {
			motDao.insert(obj);
		}else {
			motDao.update(obj);
		}
	}
	
	public Motorista findById(Motorista obj) {
		return null;
	}
	
	
	public void remove(Motorista obj) {
		
	}
	
public List<Motorista> findAll(Motorista obj){
		
		return null;
	}
	
}


