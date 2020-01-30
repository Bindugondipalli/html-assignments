package com.cts.jdbcd.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedSamp {

	public static void main(String[] args) {
		try (
				Scanner scan = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hr","root","welcome");
			) {
			
			
			PreparedStatement ps = con.prepareStatement("select first_name,salary from employees where salary>15000 order by first_name asc");
			ResultSet rs = ps.executeQuery();
			System.out.println("First_name \t"+"Salary");
			while(rs.next()) {
				System.out.printf("%-15s%-40s\n", rs.getString("first_name"), rs.getString("salary"));
			}
		} catch (SQLException e) {
			System.out.println("Could not retrieve");
			e.printStackTrace();
		}
	}

}