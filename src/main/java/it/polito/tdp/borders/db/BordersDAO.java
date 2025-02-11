package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c = new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				result.add(c);
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database - loadAllCountries()");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {
		String sql = "SELECT state1no, state2no, year "
				+ "FROM contiguity "
				+ "WHERE conttype = 1";
		List<Border> result = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Border b = new Border(rs.getInt("state1no"), rs.getInt("state2no"), rs.getInt("year"));
				for (Country c : this.loadAllCountries()) {
					if (c.getcCode() == rs.getInt("state1no")) {
						Country c1 = c;
						b.setC1(c1);
					}
					else if (c.getcCode() == rs.getInt("state2no")) {
						Country c2 = c;
						b.setC2(c2);
					}
				}
				if (b.getYear() <= anno) {
					result.add(b);
				}
			}
			conn.close();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database - getCountryPairs()");
			throw new RuntimeException("Error Connection Database");
		}
		//return result;
	}
}
