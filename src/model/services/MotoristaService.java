package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.EnderecoDao;
import model.dao.MotoristaDao;
import model.entities.Endereco;
import model.entities.Motorista;

public class MotoristaService {
		
	MotoristaDao motDao = DaoFactory.createMotoristaDao();
	 EnderecoDao endDao  = DaoFactory.createEnderecoDao();
	
	 public void saveOrUpdate(Motorista obj,Endereco objEnd) {
		if(obj.getId() == null) {
			motDao.insert(obj);
		}else {
			motDao.update(obj);
		}
	}
	
	public Motorista findById(Motorista obj,Endereco objEnd) {
		return motDao.findById(obj.getId());
	}
	
	
	public void remove(Motorista obj, Endereco objEnd) {
		motDao.delete(obj.getId());
	}
	
	public List<Motorista> findAll(){
		 return motDao.findAll();
		}
	
}


