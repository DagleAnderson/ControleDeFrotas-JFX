package model.dao;


import java.sql.Connection;

import db.DB;
import modal.dao.imp.VeiculoDaoJDBC;

public class DaoFactory {

	private static final Connection conn = DB.getConnection();

	public static VeiculoDao createVeiculoDao() {
		return new VeiculoDaoJDBC(conn);
	} 
}
