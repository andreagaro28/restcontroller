package it.objectmethod.db.dao;

import java.sql.SQLException;
import java.util.List;

public interface ContinentsDao {
	
	List<String> getContinents() throws SQLException;

}
