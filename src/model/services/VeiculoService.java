package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.VeiculoDao;
import model.entities.Veiculo;

public class VeiculoService {
	
	private VeiculoDao veicDao = DaoFactory.createVeiculoDao();
	
	public List<Veiculo> findAll(){

		return veicDao.findAll();
	}
	
	public void saveOrUpdate(Veiculo obj){
		if(obj.getId() == null) {
			veicDao.insert(obj);
		}else {
			veicDao.update(obj);
		}
		
		
	}
	
}
