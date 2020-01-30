package com.cts.escp.dao;

import com.cts.escp.model.Employee;
import com.cts.escp.util.ConnectionProvider;
import com.cts.escp.dao.IQueryMapper;
import com.cts.escp.exception.EmployeeException;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;

public class EmployeeDAOJDBCImpl implements IEmployeeDAO {

	ConnectionProvider conProvider;
	//Logger log;

	public EmployeeDAOJDBCImpl() throws EmployeeException {
		// log = Logger.getLogger("dao");

		try {
			conProvider = ConnectionProvider.getInstance();
		} catch (ClassNotFoundException | IOException exp) {
			//log.error(exp);
			throw new EmployeeException("Database is not reachable");
		}
	}

	@Override
	public String add(Employee employee) throws EmployeeException {
		String ename = null;
		if (employee != null) {
			try (Connection con = conProvider.getConnection();
					PreparedStatement pInsert = con
							.prepareStatement(IQueryMapper.ADD_Employee_QRY)) {

				pInsert.setString(1, employee.getEname());
				pInsert.setString(2, employee.getCname());
				pInsert.setDouble(4, employee.getSalary());
				pInsert.setDate(3, Date.valueOf(employee.getJoinDate()));

				int rowCount = pInsert.executeUpdate();

				if (rowCount == 1) {
					ename = employee.getEname();
				}
			} catch (SQLException exp) {
				//log.error(exp);
				throw new EmployeeException("Employee is not inserted");
			}
		}
		return ename;
	}

	@Override
	public boolean delete(String ename) throws EmployeeException {
		boolean isDone = false;

		try (Connection con = conProvider.getConnection();
				PreparedStatement pDelete = con
						.prepareStatement(IQueryMapper.DEL_Employee_QRY)) {

			pDelete.setString(1, ename);

			int rowCount = pDelete.executeUpdate();

			if (rowCount == 1) {
				isDone = true;
			}
		} catch (SQLException exp) {
			//log.error(exp);
			throw new EmployeeException("Employee is not inserted");
		}

		return isDone;
	}

	@Override
	public Employee get(String ename) throws EmployeeException {
		Employee employee=null;
		try (Connection con = conProvider.getConnection();
				PreparedStatement pSelect = con
						.prepareStatement(IQueryMapper.GET_Employee_QRY)) {

			pSelect.setString(1,ename);

			ResultSet rs = pSelect.executeQuery();
			
			if(rs.next()){
				employee = new Employee();
				employee.setEname(rs.getString("ename"));
				employee.setCname(rs.getString("cname"));
				employee.setSalary(rs.getDouble("salary"));
				employee.setJoinDate(rs.getDate("joindate").toLocalDate());
			}
			
		} catch (SQLException exp) {
			//log.error(exp);
			throw new EmployeeException("Employee is not available");
		}		
		return employee;
	}

	@Override
	public List<Employee> getAll() throws EmployeeException {
		List<Employee> employees=null;
		try (Connection con = conProvider.getConnection();
				PreparedStatement pSelect = con
						.prepareStatement(IQueryMapper.GET_ALL_Employee_QRY)) {

			ResultSet rs = pSelect.executeQuery();
			
			employees = new ArrayList<Employee>();
			
			while(rs.next()){
				Employee employee = new Employee();
				employee.setEname(rs.getString("ename"));
				employee.setCname(rs.getString("cname"));
				employee.setSalary(rs.getDouble("salary"));
				employee.setJoinDate(rs.getDate("joindate").toLocalDate());
				employees.add(employee);
			}
			
		} catch (SQLException exp) {
			//log.error(exp);
			throw new EmployeeException("No employees are available.");
		}		
		return employees;	
	}

	@Override
	public boolean update(Employee employee) throws EmployeeException {
		boolean isDone = false;
		if (employee != null) {
			try (Connection con = conProvider.getConnection();
					PreparedStatement pUpdate = con
							.prepareStatement(IQueryMapper.MODIFY_Employee_QRY)) {

				
				pUpdate.setString(2, employee.getCname());
				pUpdate.setDouble(4, employee.getSalary());
				pUpdate.setDate(3, Date.valueOf(employee.getJoinDate()));
				pUpdate.setString(1, employee.getEname());
				

				int rowCount = pUpdate.executeUpdate();

				if (rowCount == 1) {
					isDone = true;
				}
			} catch (SQLException exp) {
				//log.error(exp);
				throw new EmployeeException("Employee is not updated.");
			}
		}
		return isDone;
	}

	@Override
	public void persist() throws EmployeeException {
		/* no implementation required */
		
	}
}
