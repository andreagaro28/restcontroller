package it.objectmethod.db.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

import it.objectmethod.db.models.City;
import it.objectmethod.db.dao.CityDao;

@Component
public class CityDaoImpl implements CityDao{

	@Autowired
	private DataSource dataSource;


	@Override
	public City getCityByName(String name) throws SQLException {
		Connection conn = dataSource.getConnection();
		City city = null;

		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM city WHERE UPPER(Name) = ?");
		stmt.setString(1, name);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			city = new City();
			city.setId(rs.getLong("ID"));
			city.setCountryCode(rs.getString("CountryCode"));
			city.setName(rs.getString("Name"));
			city.setPopulation(rs.getInt("Population"));
			city.setDistrict(rs.getString("District"));
		}
		rs.close();
		stmt.close();
		conn.close();

		return city;
	}
	
	@Override
	public List<City> getCityByCode(String code) throws SQLException {
		Connection conn = dataSource.getConnection();
		List<City> cityList = new ArrayList<>();
		City city = null;

		String querySql = "SELECT * FROM city WHERE UPPER(CountryCode) = ?";
		
		PreparedStatement stmt = conn.prepareStatement(querySql);
		stmt.setString(1, code);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			city = new City();
			city.setId(rs.getLong("ID"));
			city.setName(rs.getString("Name"));
			city.setPopulation(rs.getInt("Population"));
			city.setCountryCode(rs.getString("CountryCode"));
			city.setDistrict(rs.getString("District"));
			cityList.add(city);
		}
		rs.close();
		stmt.close();
		conn.close();

		return cityList;
	}
	
}
