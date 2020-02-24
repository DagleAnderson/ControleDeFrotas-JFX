package modal.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import model.dao.MarcaDao;
import model.entities.Marca;

public class MarcaDaoJDBC implements MarcaDao {
	
	Connection conn;
	
	public MarcaDaoJDBC(Connection conexao){
		this.conn = conexao;
	}

	@Override
	public void insert(Marca obj) {
		
	}

	@Override
	public void update(Marca obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Marca findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Marca> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
