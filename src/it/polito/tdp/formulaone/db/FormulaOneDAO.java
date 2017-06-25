package it.polito.tdp.formulaone.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.formulaone.model.Circuit;
import it.polito.tdp.formulaone.model.Constructor;
import it.polito.tdp.formulaone.model.Season;


public class FormulaOneDAO {

	public List<Integer> getAllYearsOfRace() {
		
		String sql = "SELECT year FROM races ORDER BY year" ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			List<Integer> list = new ArrayList<>() ;
			while(rs.next()) {
				list.add(rs.getInt("year"));
			}
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Query Error");
		}
	}
	
	public List<Season> getAllSeasons() {
		
		String sql = "SELECT year, url FROM seasons ORDER BY year" ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			List<Season> list = new ArrayList<>() ;
			while(rs.next()) {
				list.add(new Season(Year.of(rs.getInt("year")), rs.getString("url"))) ;
			}
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Circuit> getAllCircuits() {

		String sql = "SELECT * FROM circuits ORDER BY circuitid";

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			List<Circuit> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Circuit(rs.getInt("circuitId"), rs.getString("circuitref"),rs.getString("name"),rs.getString("location"),rs.getString("country"),rs.getDouble("lat"),rs.getDouble("lng"),rs.getInt("alt"),rs.getString("url")));
			}

			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Query Error");
		}
	}
	
	public List<Constructor> getConstructors(int circuitId) {

		String sql = "select distinct cr.constructorId,c.name from races r, constructorresults cr, constructors c where r.circuitId=? and r.raceId=cr.raceId and c.constructorId=cr.constructorId ";

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, circuitId);

			ResultSet rs = st.executeQuery();

			List<Constructor> constructors = new ArrayList<>();
			while (rs.next()) {
				constructors.add(new Constructor(rs.getInt("constructorId"), rs.getString("name")));
			}

			conn.close();
			return constructors;
		} catch (SQLException e) {
			e.printStackTrace();
			throw
			new RuntimeException("SQL Query Error");
		}
	}
		
		public List<Integer> getRaceId(int circuitId) {

			String sql = "select r.raceId from races r where r.circuitId=? ";

			try {
				Connection conn = DBConnect.getConnection();

				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, circuitId);

				ResultSet rs = st.executeQuery();

				List<Integer> id = new ArrayList<>();
				while (rs.next()) {
					id.add(rs.getInt("raceid"));
				}

				conn.close();
				return id;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("SQL Query Error");
			}
		}
		
			public double getPuntiCostruttoreGara(int raceId, int constId) {

				String sql = "select cr.points from constructorresults cr where cr.raceId=? and cr.constructorId=?";

				try {
					Connection conn = DBConnect.getConnection();

					PreparedStatement st = conn.prepareStatement(sql);
					st.setInt(1, raceId);
					st.setInt(2, constId);
					

					ResultSet rs = st.executeQuery();

					double p=0;
					while (rs.next()) {
						p=rs.getDouble("points");
					}

					conn.close();
					return p;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException("SQL Query Error");
				}
			
			
	}


	public static void main(String[] args) {
		FormulaOneDAO dao = new FormulaOneDAO() ;
		
		//List<Integer> years = dao.getAllYearsOfRace() ;
		//System.out.println(years);
		
		//List<Season> seasons = dao.getAllSeasons() ;
		//System.out.println(seasons);

		
		//List<Circuit> circuits = dao.getAllCircuits();
		//System.out.println(circuits);

		List<Constructor> constructors = dao.getConstructors(1);
		System.out.println(constructors);
		
	}
	
}
