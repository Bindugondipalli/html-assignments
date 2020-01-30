package com.cts.escp.dao;

public interface IQueryMapper {

	public static final String ADD_Employee_QRY = 
			"INSERT INTO employee(ename,  cname, joindate, salary) VALUES(?,?,?,?)";
	public static final String MODIFY_Employee_QRY = 
			"UPDATE employee SET ename=?,cname=?,joindate=?,salary=? WHERE ename=?";
	public static final String DEL_Employee_QRY = 
			"DELETE FROM employee WHERE ename=?";
	public static final String GET_ALL_Employee_QRY = 
			"SELECT * FROM employee";
	public static final String GET_Employee_QRY = 
			"SELECT * FROM employee WHERE ename=?";
	public static final String GET_ALL_Employee_QRY1 = null;
}