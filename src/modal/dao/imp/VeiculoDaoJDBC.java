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


import db.DB;
import db.DBException;
import model.entities.Marca;
import model.entities.Modelo;
import model.entities.Veiculo;
import model.dao.VeiculoDao;

public class VeiculoDaoJDBC implements VeiculoDao{

	private Connection conn;
	
	public VeiculoDaoJDBC(Connection conection){
		this.conn = conection;
	}
	
	@Override
	public void insert(Veiculo obj) {
		 PreparedStatement st = null;
		 ResultSet rs = null;
		 
			 try {
				 st = conn.prepareStatement(
						 " INSERT INTO veiculo "  
				 		+"(descricao_veic,ano_veic,km_rodado_veic,placa_veic,chassi_veic,renavam_veic,modelo_id) "  
				 		+" VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				 
				 		st.setString(1, obj.getDescricao());
				 		st.setString(2,obj.getAno());
				 		st.setDouble(3, obj.getKmRodado());
				 		st.setString(4, obj.getPlaca());
				 		st.setString(5, obj.getChassi());
				 		st.setString(6, obj.getRenavam());
				 		st.setInt(7, 1);
				 		
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
				 
				 
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}finally {
				DB.closeResultset(rs);
				DB.closeStatement(st);
			}
		
	}

	@Override
	public void update(Veiculo obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					 " UPDATE veiculo SET "
				     +"descricao_veic = ?,ano_veic=?,km_rodado_veic=?,placa_veic=?,chassi_veic=?,renavam_veic=?,modelo_id=? "
					 +"WHERE id_veic =?"
					 );
			
			st.setString(1, obj.getDescricao());
			st.setString(2,obj.getAno());
	 		st.setDouble(3, obj.getKmRodado());
	 		st.setString(4, obj.getPlaca());
	 		st.setString(5, obj.getChassi());
	 		st.setString(6, obj.getRenavam());
	 		st.setInt(7, obj.getModelo().getId());
	 		
	 		st.setInt(8,obj.getId());
		    st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}
	
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Veiculo findById(Integer id) { //recuperar dados de veiculo por Id
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {	
			st = conn.prepareStatement(
					"SELECT veiculo.*,modelo.nome_mod as modelo,marca.nome_marca as marca FROM veiculo "
					 + "INNER JOIN modelo ON veiculo.modelo_id = modelo.id_mod "
					 + "INNER JOIN marca ON modelo.marca_id = marca.id_marca " 
					 + "WHERE veiculo.id_veic =?");
					
					st.setInt(1,id);
					rs = st.executeQuery();
					
					if(rs.next()){
						Marca marca = instantiateMarca(rs);
						Modelo modelo =instantiateModelo(rs,marca); 
						Veiculo obj = instantiateVeiculo(rs,modelo,marca);	
						
						return obj;
					}
					return null;
	
		} catch (SQLException e) {
			throw new  DBException(e.getMessage());
		}
		finally {
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}
	
	}
	
	//Instanciar Veiculo de ResultSet
	private Veiculo instantiateVeiculo(ResultSet rs, Modelo modelo, Marca marca) {
		try {	
			Veiculo ResultVeiculo = new Veiculo(
				rs.getInt("id_veic"),
				rs.getString("descricao_veic"),
				rs.getString("ano_veic"),
				rs.getDouble("km_rodado_veic"),
				rs.getString("placa_veic"),
				rs.getString("chassi_veic"),
				rs.getString("renavam_veic"),
				modelo);
			
		return  ResultVeiculo;
		
		}catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
	//Instanciar Modelo de ResultSet 
	private Modelo instantiateModelo(ResultSet rs,Marca Resultmarca) {
		try {
			Modelo ResultMod = new Modelo();
			ResultMod.setId(rs.getInt("modelo_id"));
			ResultMod.setDescricao(rs.getString("modelo"));
			ResultMod.setMarca(Resultmarca);
			
			return ResultMod;
			
		}catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	private Marca instantiateMarca(ResultSet rs) { //Instanciar Marca de ResultSet 
		try {
			Marca ResultMarca = new Marca();
			ResultMarca.setDescricao(rs.getString("marca"));
			
			return ResultMarca;
			
		}catch (SQLException e) {
			 throw new DBException(e.getMessage());
		}
	}
	
	@Override
	public List<Veiculo> findByModelo(Modelo modelo) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT veiculo.*,modelo.nome_mod as modelo,marca.nome_marca as marca FROM veiculo "
				    + "INNER JOIN modelo ON veiculo.modelo_id = modelo.id_mod " 
					+ "INNER JOIN marca ON modelo.marca_id = marca.id_marca "
				    + "WHERE modelo.nome_mod=?");
		
			st.setString(1, modelo.getDescricao());
			rs = st.executeQuery();
			
			List<Veiculo> listVeic = new ArrayList<>();
			
			//Verificação de existência de duas instancias do mesmo obj
			Map<String,Marca> mapMarca = new HashMap<>();
			Map<Integer,Modelo> mapMod = new HashMap<>();
				while(rs.next()){
					Marca marca = mapMarca.get(rs.getString("marca"));
					if(marca == null) {
						marca = instantiateMarca(rs);
						mapMarca.put(rs.getString("marca"), marca);
					}
					
					Modelo mod = mapMod.get(rs.getInt("modelo_id"));
					if(mod == null) {
					    mod = instantiateModelo(rs,marca);
						mapMod.put(rs.getInt("modelo_id"), mod);
					}
					
					Veiculo obj = instantiateVeiculo(rs, mod, marca);
					listVeic.add(obj);
				 }
			
				return listVeic;	
		
		}catch (SQLException e) {
			throw new DBException(e.getMessage());
		}finally{
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}
		
	}
	
	@Override
	public List<Veiculo> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
        	 st = conn.prepareStatement(
        	    		"SELECT veiculo.*,modelo.nome_mod as modelo,marca.nome_marca as marca FROM veiculo "
        					    + "INNER JOIN modelo ON veiculo.modelo_id = modelo.id_mod " 
        						+ "INNER JOIN marca ON modelo.marca_id = marca.id_marca");
      
        	 rs = st.executeQuery();
        	 
        	 List<Veiculo> veicList = new ArrayList<>();
        	 
        	 Map<String,Marca> mapMarca = new HashMap<>();
        	 Map<Integer,Modelo> mapMod = new HashMap<>();
        	 while(rs.next()){
        		 Marca marca = mapMarca.get(rs.getString("marca"));
					if(marca == null) {
						marca = instantiateMarca(rs);
						mapMarca.put(rs.getString("marca"), marca);
					}
					
					Modelo mod = mapMod.get(rs.getInt("modelo_id"));
					if(mod == null) {
					    mod = instantiateModelo(rs,marca);
						mapMod.put(rs.getInt("modelo_id"), mod);
					}
					
					Veiculo obj = instantiateVeiculo(rs, mod, marca);
					veicList.add(obj);
				 }
			
				return veicList;	
   	 
        }catch (SQLException e) {
			throw new DBException(e.getMessage());
		}finally{
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}
	}


}
