package model.dao;

import java.util.List;

import model.entities.Modelo;
import model.entities.Veiculo;

public interface VeiculoDao {
	
	void insert(Veiculo obj);
	void update(Veiculo obj);
	void deleteById(Integer id);
	Veiculo findById(Integer id);
	List<Veiculo>findByModelo(Modelo modelo);
	List<Veiculo>findAll();
}
