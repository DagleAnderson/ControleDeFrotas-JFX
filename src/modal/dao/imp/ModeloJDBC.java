package modal.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBException;
import model.dao.ModeloDao;
import model.entities.Modelo;

public class ModeloJDBC implements ModeloDao{
	
	Connection conn;
	
	
	public ModeloJDBC( Connection connection) {
		this.conn = connection;
	}

	@Override
	public void insert(Modelo obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Modelo obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Modelo findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Modelo> findAll() {
			
			PreparedStatement st = null;
			ResultSet rs = null;
		try {	
				st  = conn.prepareStatement("SELECT * FROM modelo");
				rs = st.executeQuery();
				
				List<Modelo> modeloList = new ArrayList<>();
				while(rs.next()) {
					Modelo mod = instantiateModelo(rs);
					
					modeloList.add(mod);
				}
				
				return modeloList;
				
		}catch(SQLException e) {
			 throw new DBException(e.getMessage()) ;
		}
	}

	private Modelo instantiateModelo(ResultSet rs) {
		try {
			Modelo modelo = new Modelo();
			modelo.setId(rs.getInt("id"));
			modelo.setDescricao(rs.getString("descricao"));
			
			return modelo;
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
	}

}
