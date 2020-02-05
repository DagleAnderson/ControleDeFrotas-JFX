package model.services;

import java.util.ArrayList;
import java.util.List;

import modal.dao.imp.VeiculoDaoJDBC;
import model.dao.DaoFactory;
import model.dao.VeiculoDao;
import model.entities.Marca;
import model.entities.Modelo;
import model.entities.Veiculo;

public class VeiculoService {
	
	private VeiculoDao veicDao = DaoFactory.createVeiculoDao();
	
	public List<Veiculo> findAll(){

		return veicDao.findAll();
	}
	
}
