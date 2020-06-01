package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.MotoristaDao;
import model.entities.Motorista;

public class MotoristaService {
		
	MotoristaDao motDao = DaoFactory.createMotoristaDao();

	public void saveOrUpdate(Motorista obj) {
		if(obj.getId() == null) {
			System.out.println("vou inserir...");
			motDao.insert(obj);
		}else {
			System.out.println("vou atualizar...");
			motDao.update(obj);
		}
	}
	
	public Motorista findById(Motorista obj) {
		return motDao.findById(obj.getId());
	}
	
	
	public void remove(Motorista obj) {
		motDao.delete(obj.getId());
	}
	
	public List<Motorista> findAll(){
		 return motDao.findAll();
		}
	
}


