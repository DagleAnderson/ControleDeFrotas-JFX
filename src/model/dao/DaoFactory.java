package model.dao;


import java.sql.Connection;

import db.DB;
import modal.dao.imp.ModeloDaoJDBC;
import modal.dao.imp.VeiculoDaoJDBC;

public class DaoFactory { // F�brica de instancia��o DAO

	private static final Connection conn = DB.getConnection();

	public static VeiculoDao createVeiculoDao() {
		return new VeiculoDaoJDBC(conn);
	} 
	
	
	public static ModeloDao createModeloDao() {
		return new ModeloDaoJDBC(conn);
	}
}
