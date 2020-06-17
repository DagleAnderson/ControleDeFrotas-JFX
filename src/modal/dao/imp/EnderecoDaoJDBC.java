package modal.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.EnderecoDao;
import model.entities.Endereco;
import model.entities.Motorista;

public class EnderecoDaoJDBC implements EnderecoDao{
	
	Connection conn;
	PreparedStatement st =null;
	ResultSet rs = null;
	
	public EnderecoDaoJDBC(Connection connection) {
		this.conn = connection;
	}

	@Override
	public void insert(Motorista obj) {
		try {
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
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected> 0 ) {
		 		rs = st.getGeneratedKeys();
		 		if(rs.next()) {
		 			int id = rs.getInt(1);
		 			
		 			obj.setId(id);
		 		}
		 	}else {
		 		throw new DBException("Erro inesperado! Nenhum linha foi afetada");
		 	}
			
		}catch (SQLException e) {
			 throw new DBException(e.getMessage());
		}finally {
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}
		
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
