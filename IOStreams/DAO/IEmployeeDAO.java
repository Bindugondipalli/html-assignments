	package com.cts.escp.dao;
	import com.cts.escp.model.Employee;
	import com.cts.escp.exception.EmployeeException;
	
	import java.util.List;
	
	public interface IEmployeeDAO {	
		String add(Employee employee) throws EmployeeException;
		boolean delete(String ename)throws EmployeeException;
		Employee get(String ename) throws EmployeeException;;
		List<Employee> getAll() throws EmployeeException;;
		boolean update(Employee employee) throws EmployeeException;
		void persist()throws EmployeeException;
	}