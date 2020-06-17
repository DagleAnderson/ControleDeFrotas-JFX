package modal.dao.imp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	try {
		this.conn.setAutoCommit(false);
		
	}catch(SQLException e) {
		throw new DBException("commit:" + e.getMessage());
	}
	}

	@Override
	public void insert(Motorista obj) {	
		try {
			st = conn.prepareStatement("INSERT INTO "
			+ " motorista(nome_motor,sobrenome_motor,dataNasc_motor,cpf_motor,cnh_motor,tel_motor,email_motor)"
			+ " VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getSobreNome());
				if(obj.getDataNasc() != null) {
					st.setDate(3, new Date(obj.getDataNasc().getTime()));
				}else {
					st.setDate(3,null);
				}
			st.setString(4, obj.getCpf());
			st.setString(5,obj.getCnh());
			st.setString(6, obj.getTelefone());
			st.setString(7,obj.getEmail());

			;
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				rs = st.getGeneratedKeys();	
				if(rs.next()) {
					int id = rs.getInt(1);
				
					obj.setId(id);
				}
				
					DB.closeResultset(rs);
					DB.closeStatement(st);
					
					st = conn.prepareStatement("INSERT INTO endereco_motorista(cidade_end,uf_end,bairro_end,rua_end,numero_end,cep_end,comp_end,id_motorista_end)"
							+ " VALUES(?,?,?,?,?,?,?,?)" ,Statement.RETURN_GENERATED_KEYS);
					
					st.setString(1, obj.getEndereco().getCidade());
					st.setString(2, obj.getEndereco().getUf());
					st.setString(3, obj.getEndereco().getBairro());
					st.setString(4, obj.getEndereco().getRua());
					st.setString(5, obj.getEndereco().getNumero());
					st.setString(6, obj.getEndereco().getCep());
					st.setString(7, obj.getEndereco().getComplemento());
					
					st.setInt(8,obj.getId());
					
					int rowsAffectedEnd = st.executeUpdate();
					
					if(rowsAffectedEnd> 0 ) {
				 		rs = st.getGeneratedKeys();
				 		if(rs.next()) {
				 			int id = rs.getInt(1);
				 			
				 			obj.getEndereco().setId(id);
				 		} 
				 		
					conn.commit();	
				 	
					}else {
						conn.rollback();
				 		throw new DBException("Erro inesperado! Nenhum linha de endereço foi afetada");
				 	}
					
	
		}else {
			conn.rollback();
			throw new DBException("Erro inesperado! Nenhum linha de endereço foi afetada");
			
		}
		
		}catch (SQLException e) {		
			throw new DBException( e.getMessage());
		}finally {
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}
			
	
		
	}

	@Override
	public void update(Motorista obj) {
		
		try {
			
			st = conn.prepareStatement("UPDATE motorista SET "
			 + " nome_motor = ? , sobrenome_motor = ?, dataNasc_motor = ?, "+
				" cpf_motor = ?, cnh_motor = ?,tel_motor = ?,email_motor = ? "
			 + " WHERE id_motor = ?");
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getSobreNome());
			st.setDate(3, new Date(obj.getDataNasc().getTime()));
			st.setString(4, obj.getCpf());
			st.setString(5, obj.getCnh());
			st.setString(6, obj.getTelefone());
			st.setString(7, obj.getEmail());
			
			st.setInt(8, obj.getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DBException( e.getMessage());
		}finally {
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void delete(Integer id) {
		try {
			st = conn.prepareStatement("DELETE FROM motorista WHERE id_motor = ?");
			
			st.setInt(1, id);
			
			st.execute();
			
		}catch (SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Motorista findById(Integer id) {
		try {
			st = conn.prepareStatement("SELECT * FROM motorista WHERE id_motor = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()){
				Motorista obj = instanteateMotorista(rs);
				
				return obj;
			}
			
			return null;
			
		} catch (SQLException e) {
			 throw new DBException(e.getMessage()); 
		}finally {
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}

	}

	@Override
	public List<Motorista> findAll() {
		try {
			
			st = conn.prepareStatement("SELECT * FROM motorista as mot");
			
			rs = st.executeQuery();
			
			List<Motorista> motoristas = new ArrayList<>();
			
			while (rs.next()) {
				 
				Motorista motorista = instanteateMotorista(rs); 
				motoristas.add(motorista);
				
			}
			return motoristas;
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}
	}

	private Motorista instanteateMotorista(ResultSet rs) {
		try {
			Motorista mot = new Motorista();
			mot.setId(rs.getInt("id_motor"));
			mot.setNome(rs.getString("nome_motor"));
			mot.setSobreNome(rs.getString("sobrenome_motor"));
			if(rs.getString("dataNasc_motor") != null ){
				mot.setDataNasc(new  java.util.Date(rs.getTimestamp("dataNasc_motor").getTime()));
			}else{
				mot.setDataNasc(null);
			}
			mot.setCpf(rs.getString("cpf_motor"));
			mot.setCnh(rs.getString("cnh_motor"));
			mot.setTelefone(rs.getString("tel_motor"));
			mot.setEmail(rs.getString("email_motor"));
			//mot.setEndereco(endereco);
			
		System.out.println(mot);
			return mot;
			
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
	
}
