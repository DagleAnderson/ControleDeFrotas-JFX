package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.MarcaDao;
import model.entities.Marca;

public class MarcaService {
	
	private MarcaDao marcaDao = DaoFactory.createMarcaDao();
	
	public void saveOrupdate(Marca obj) {
		if(obj.getId() == null){
			this.marcaDao.insert(obj);
		}else {
			this.marcaDao.update(obj);
		}
		
	}
	
	public Marca findById(Integer id){
		return this.marcaDao.findById(id);
	}
	
	public void remove(Marca obj) {
		this.marcaDao.delete(obj.getId());
	}
	
	public List<Marca> findAll(){
		return marcaDao.findAll();
	}
}
