package modal.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


import db.DB;
import model.dao.ViagemDao;
import model.entities.Viagem;

public class ViagemDaoJDBC implements ViagemDao {
	
	Connection conn = DB.getConnection();
	
	@Override
	public void insert(Viagem obj) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void update(Viagem obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Viagem findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Viagem> findByModelo(Viagem viagem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Viagem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
