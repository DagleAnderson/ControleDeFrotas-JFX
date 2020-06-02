package modal.dao.imp;

import java.sql.Connection;
import java.util.List;

import model.dao.EnderecoDao;
import model.entities.Endereco;
import model.entities.Motorista;

public class EnderecoDaoJDBC implements EnderecoDao{
	
	Connection conn;
	
	public EnderecoDaoJDBC(Connection connection) {
		this.conn = connection;
	}

	@Override
	public void insert(Endereco endereco) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Endereco endereco) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Endereco findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Motorista> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
