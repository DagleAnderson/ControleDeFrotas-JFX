package modal.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DBException;
import model.dao.ModeloDao;
import model.entities.Marca;
import model.entities.Modelo;

public class ModeloDaoJDBC implements ModeloDao{
	
	Connection conn;
	
	
	public ModeloDaoJDBC( Connection connection) {
		this.conn = connection;
	}

	@Override
	public void insert(Modelo obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
		st = conn.prepareStatement("INSERT INTO modelo "+
		"(nome_mod,marca_id) "+
				"VALUES(?,?)",Statement.RETURN_GENERATED_KEYS);
		
		st.setString(1, obj.getDescricao());
		st.setInt(2, 1);
		
		 int rowsAffected = st.executeUpdate();
		 	
		 	if(rowsAffected > 0 ) {
		 		rs = st.getGeneratedKeys();
		 	if(rs.next()) {
		 			int id = rs.getInt(1);
		 			
		 			obj.setId(id);
		 		}
		 	}else {
		 		throw new DBException("Erro inesperado! Nenhum linha foi afetada");
		 	}
	
		}catch(SQLException e) {
			throw new DBException(e.getMessage()); 
		}
		
		
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
				st  = conn.prepareStatement("SELECT modelo.* ,marca.nome_marca as marca FROM modelo" +
						" INNER JOIN marca ON modelo.marca_id = marca.id_marca");
					
				rs = st.executeQuery();
				
				List<Modelo> modeloList = new ArrayList<>();
				
				Map<String,Marca> mapMarca = new HashMap<>();
				while(rs.next()) {
					Marca marca =  mapMarca.get(rs.getString("marca"));
					if(marca == null) {
						marca = instantiateMarca(rs);
						mapMarca.put(rs.getString("marca"), marca);
						
					}
					Modelo mod = instantiateModelo(rs,marca);
					
					modeloList.add(mod);
				}
				
				return modeloList;
				
		}catch(SQLException e) {
			 throw new DBException(e.getMessage()) ;
		}
	}

	private Marca instantiateMarca(ResultSet rs) {
		try {
			Marca marca = new Marca();
			marca.setDescricao(rs.getString("marca"));
			return marca;
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		
	}

	private Modelo instantiateModelo(ResultSet rs,Marca ResultMarca) {
		try {
			Modelo modelo = new Modelo();
			modelo.setId(rs.getInt("id_mod"));
			modelo.setDescricao(rs.getString("nome_mod"));
			modelo.setMarca(ResultMarca);
			
			return modelo;
			
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
	}

}
