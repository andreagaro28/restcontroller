package it.objectmethod.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.db.dao.CountryDao;
import it.objectmethod.db.models.Country;

@Component
public class CountryDaoImpl implements CountryDao{


	@Autowired
	DataSource dataSource;
	
	@Override
	public List<Country> getCountryByContinentName(String name, String continent) throws SQLException {
		Connection conn = dataSource.getConnection();
		Country country = null;
		List<Country> countryList= new ArrayList<Country>();

		String querySql = "SELECT Name, Continent, Population, SurfaceArea, Code FROM country \r\n"
				+ "WHERE UPPER(('' = ? OR Name = ?) \r\n"
				+ "AND  ('' = ? OR Continent = ?))\r\n"
				+ "";
		PreparedStatement stmt = conn.prepareStatement(querySql);
		stmt.setString(1, name);
		stmt.setString(2, name);
		stmt.setString(3, continent);
		stmt.setString(4, continent);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			country = new Country();
			country.setCode(rs.getString("Code"));
			country.setName(rs.getString("Name"));
			country.setPopulation(rs.getInt("Population"));
			country.setContinent(rs.getString("Continent"));
			country.setSurfaceArea(rs.getFloat("SurfaceArea"));
			countryList.add(country);
		}

		rs.close();
		stmt.close();
		conn.close();
		return countryList;
	}

	@Override
	public List<Country> getCountryByContinentList(String name) throws SQLException {
		Connection conn = dataSource.getConnection();
		List<Country> countryList = new ArrayList<>();
		Country country = null;
		
		String querySql = "SELECT * FROM country "
				+ "WHERE UPPER(Continent) = ?";
		
		PreparedStatement stmt = conn.prepareStatement(querySql);
		stmt.setString(1, name);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			country = new Country();
			country.setName(rs.getString("Name"));
			country.setCode(rs.getString("Code"));
			country.setPopulation(rs.getInt("Population"));
			country.setContinent(rs.getString("Continent"));
			country.setSurfaceArea(rs.getFloat("SurfaceArea"));
			countryList.add(country);
		}
		rs.close();
		stmt.close();
		conn.close();

		return countryList;
	}

}
