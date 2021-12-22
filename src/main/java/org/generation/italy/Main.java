package org.generation.italy;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
//import java.time.LocalDate;

public class Main {
	
	private final static String URL = "jdbc:mysql://localhost:3306/db-nations";
	private final static String USER = "root";
	private final static String PASSWORD = "fancypass_BC!!09";

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		


		
		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
			
			Country userCountryId = selectCountryById (con,scan);
			if(userCountryId != null) {
				System.out.println("Nazione: " +  userCountryId.getName());
			} else {
				System.out.println("Non in elenco");
			}
			
//			// login
//						User loggedUser = login(con, scan);
//						if(loggedUser != null) {
//							System.out.println("Welcome " + loggedUser.getName());
			
		} catch(SQLException e) {
			System.out.println("Errore:");
			System.out.println(e.getMessage());
		}		
		scan.close();
	}
	
//	private static User login(Connection con, Scanner scan) throws SQLException {
//		User loggedUser = null;
//		
//		System.out.print("Insert your username: ");
//		String username = scan.nextLine();
//		
//		String selectUser = "SELECT id, name, username FROM `user` WHERE username = ?;";
//		try(PreparedStatement psUser = con.prepareStatement(selectUser)){
//			psUser.setString(1, username);
//			
//			try(ResultSet rsUser = psUser.executeQuery()){
//				if(rsUser.next()) {
//					loggedUser = new User(rsUser.getInt(1), rsUser.getString(2), rsUser.getString(3));
//				} 
//			}
//			
//		}
//		return loggedUser;
	
	private static Country selectCountryById (Connection con, Scanner scan) throws SQLException {
		Country userCountryId = null;
		
		System.out.println("Inserisci l'id di una nazione: ");
		int countryId = Integer.parseInt(scan.nextLine());
		
		String selectCountry = "SELECT *\n"
				+ "FROM countries c\n"
				+ "WHERE c.country_id = ?;";
		
		try(PreparedStatement psCountry = con.prepareStatement(selectCountry)){
			psCountry.setInt(1, countryId);
			try(ResultSet rsCountry = psCountry.executeQuery()) {
				if (rsCountry.next()) {
				userCountryId = 
					new Country(
						rsCountry.getInt(1), 
						rsCountry.getString(2), 
						rsCountry.getInt(3), 
						rsCountry.getDate(4).toLocalDate(), 
						rsCountry.getString(5), 
						rsCountry.getString(6), 
						rsCountry.getInt(7)
					);
			}
		}
			
		}
		
		
		return userCountryId; 
	}
//	private static void addUserTodo(Connection con, Scanner scan, User loggedUser) throws SQLException {
//		String sql = "INSERT INTO todo "
//				+ "(created_at, user_id, todo, completed) "
//				+ "VALUES(?, ?, ?, ?);";
//		
//		System.out.println("What is you todo?");
//		String todo = scan.nextLine();
//		Timestamp now = new Timestamp(System.currentTimeMillis());
//		
//		try(PreparedStatement insert = con.prepareStatement(sql)){
//			insert.setTimestamp(1, now);
//			insert.setInt(2, loggedUser.getId());
//			insert.setString(3, todo);
//			insert.setBoolean(4, false);
//			
//			insert.executeUpdate();
//		}
//		
//	}

}
