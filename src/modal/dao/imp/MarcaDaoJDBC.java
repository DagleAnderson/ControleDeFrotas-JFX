package modal.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.MarcaDao;
import model.entities.Marca;

public class MarcaDaoJDBC implements MarcaDao {
	
	Connection conn;
	
	public MarcaDaoJDBC(Connection conexao){
		this.conn = conexao;
	}

	@Override
	public void insert(Marca obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("INSERT INTO"
					+" marca(nome_marca) VALUES(?)",Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1,obj.getDescricao());	
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
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
		}finally {
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Marca obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("UPDATE marca SET"
										+" nome_marca=?  WHERE id_marca = ?");
			st.setString(1,obj.getDescricao());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();
			
		}catch (SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultset(rs);
		}
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Marca findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM marca  WHERE id_marca = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Marca obj = instantiateMarca(rs);
				
				return obj;
			}
			return null;
		}catch(SQLException e){
			throw new DBException(e.getMessage());
		}finally {
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}
	}


	@Override
	public List<Marca> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM marca");
			rs = st.executeQuery();
			
			List<Marca> marcaList = new ArrayList<>();
			
			while(rs.next()){
				 Marca marca = instantiateMarca(rs);
				 
				 marcaList.add(marca);
			}
			
			return marcaList;
			
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}
	
	}

	private Marca instantiateMarca(ResultSet rs) {
		try {
			Marca marca = new Marca();
			marca.setId(rs.getInt("id_marca"));
			marca.setDescricao(rs.getString("nome_marca"));
			
			return marca;
			
		}catch(SQLException e){
			throw new DBException(e.getMessage());
		}
	}

}
