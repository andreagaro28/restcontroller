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

import it.objectmethod.db.dao.ContinentsDao;
@Component
public class ContinentsDaoImpl implements ContinentsDao{

	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<String> getContinents() throws SQLException {
		List<String> continentsList = new ArrayList<String>();
		Connection conn = dataSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT Continent FROM country");
		ResultSet rs = stmt.executeQuery();
		String continents = null;
		while(rs.next()) {
			continents = rs.getString("Continent");
			continentsList.add(continents);
		}
		rs.close();
		stmt.close();
		conn.close();
		return continentsList;
	}

}
