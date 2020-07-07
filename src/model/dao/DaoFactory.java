package model.dao;


import java.sql.Connection;

import db.DB;
import modal.dao.imp.MarcaDaoJDBC;
import modal.dao.imp.ModeloDaoJDBC;
import modal.dao.imp.MotoristaDaoJDBC;
import modal.dao.imp.VeiculoDaoJDBC;
import modal.dao.imp.ViagemDaoJDBC;

public class DaoFactory { // F�brica de inje��o de dependencia DAO

	private static final Connection conn = DB.getConnection();

	public static VeiculoDao createVeiculoDao() {
		return new VeiculoDaoJDBC(conn);
	} 
	
	public static ModeloDao createModeloDao() {
		return new ModeloDaoJDBC(conn);
	}
	
	public static MarcaDao createMarcaDao() {
		return new MarcaDaoJDBC(conn);
	}
	
	public static MotoristaDao createMotoristaDao() {
		return new MotoristaDaoJDBC(conn);
	}
	
	public static ViagemDao createViagemDao(){
		return new ViagemDaoJDBC();
	}
	
	
}
