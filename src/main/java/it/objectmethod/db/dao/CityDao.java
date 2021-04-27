package it.objectmethod.db.dao;

import java.sql.SQLException;
import java.util.List;

import it.objectmethod.db.models.City;

public interface CityDao {
	City getCityByName(String name) throws SQLException;
		
	List<City> getCityByCode(String code) throws SQLException;

}
