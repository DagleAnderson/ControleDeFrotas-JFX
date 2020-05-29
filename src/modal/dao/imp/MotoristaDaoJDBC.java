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
			
			if(rowsAffected>0) {
				rs = st.getGeneratedKeys();
			}
			if(rs.next()) {
				int id = rs.getInt(1);
			
				obj.setId(id);
			}
			
			
		}catch (SQLException e) {
			System.out.println("vou não deu certo inserir");
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
			
		} catch (Exception e) {
			System.out.println("vou não deu certo atualizar");
			throw new DBException( e.getMessage());
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
