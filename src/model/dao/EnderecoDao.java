package model.dao;

import java.util.List;

import model.entities.Endereco;
import model.entities.Motorista;

public interface EnderecoDao {
	void insert(Motorista endMotorista);
	void update(Motorista endMotorista);
	void delete(Integer id);
	Endereco findById(Integer id);
	List<Motorista> findAll();
}
