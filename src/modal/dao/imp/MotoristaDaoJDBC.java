package modal.dao.imp;

import java.sql.Connection;
import java.util.List;

import model.dao.MotoristaDao;
import model.entities.Motorista;


public class MotoristaDaoJDBC implements MotoristaDao {

	Connection conn;
	
	public MotoristaDaoJDBC(Connection connection) {
		this.conn = connection;
	}

	@Override
	public void insert(Motorista motorista) {
		
		
	}

	@Override
	public void update(Motorista motorista) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Motorista findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Motorista> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
