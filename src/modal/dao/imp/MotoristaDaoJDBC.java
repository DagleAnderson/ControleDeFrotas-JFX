package modal.dao.imp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.MotoristaDao;
import model.entities.Motorista;


public class MotoristaDaoJDBC implements MotoristaDao {

	Connection conn;
	
	 private PreparedStatement  st = null;
	 private ResultSet rs = null;
	
	
	public MotoristaDaoJDBC(Connection connection) {
		this.conn = connection;
	}

	@Override
	public void insert(Motorista obj) {
	
		
		try {
			st = conn.prepareStatement("INSERT INTO "
			+ " motorista(nome_motor,sobrenome_moto,dataNasc_motor,cpf_motor,rg_motor,tel_motor,cnh_motor) "
			+ " VALUES(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getSobreNome());
			st.setDate(3, new Date(obj.getDataNascimento().getTime()));
			st.setString(4, obj.getCpf());
			st.setString(5, obj.getRg());
			st.setString(6, obj.getTelefone());
			st.setString(7,obj.getCnh());
			;
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected>0) {
				rs = st.getGeneratedKeys();
			}
			if(rs.next()) {
				int id = rs.getInt(1);
			
				obj.setId(id);
			}
			
			
		}catch (SQLException e) {
			throw new DBException("Erro SQL de banco de dados:"+ e.getMessage());
		}finally {
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}
			
	
		
	}

	@Override
	public void update(Motorista obj) {
		
		try {
			
			st = conn.prepareStatement("UPDATE motorista SET "
			 + " nome_motor = ? , sobrenome_moto = ?, dataNasc_motor = ?, cpf_motor = ?, rg_motor = ?,tel_motor = ?, cnh_motor = ?"
			 + " WHERE id_motor = ? ");
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getSobreNome());
			st.setDate(3, new Date(obj.getDataNascimento().getTime()));
			st.setString(4, obj.getCpf());
			st.setString(5, obj.getRg());
			st.setString(6, obj.getTelefone());
			st.setString(7, obj.getCnh());
			
			st.setInt(8, obj.getId());
			
		} catch (Exception e) {
			throw new DBException("Erro SQL de banco de dados:" + e.getMessage());
		}finally {
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}
		
		
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
