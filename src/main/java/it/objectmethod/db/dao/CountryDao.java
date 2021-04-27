package it.objectmethod.db.dao;

import java.sql.SQLException;
import java.util.List;

import it.objectmethod.db.models.Country;

public interface CountryDao {

	List<Country> getCountryByContinentName(String name, String continent) throws SQLException;
	
	public List<Country> getCountryByContinentList(String name) throws SQLException;

}
