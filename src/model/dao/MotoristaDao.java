package model.dao;

import java.util.List;

import model.entities.Motorista;

public interface MotoristaDao {
	
	void insert(Motorista motorista);
	void update(Motorista motorista);
	void delete(Integer id);
	Motorista findById(Integer id);
	List<Motorista> findAll();
}
