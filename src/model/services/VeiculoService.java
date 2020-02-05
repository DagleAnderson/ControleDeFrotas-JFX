package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Marca;
import model.entities.Modelo;
import model.entities.Veiculo;

public class VeiculoService {
	
	public List<Veiculo> findAll(){
		
		List<Veiculo> list = new ArrayList<>();
		list.add(new Veiculo(1," Caminhao", "2000", 123.3, "asd123", "as123", "1234536", new Modelo(1, "Duplex", new Marca(1, "Fiat"))));
		list.add(new Veiculo(1," Caminhao 2", "2000", 123.3, "asd123", "as123", "1234536", new Modelo(1, "Duplex", new Marca(1, "Fiat"))));
	
	return list;
	}
	
}
