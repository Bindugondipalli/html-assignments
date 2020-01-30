package com.cts.escp.dao;

import com.cts.escp.model.Employee;
import com.cts.escp.exception.EmployeeException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EmployeeDAOIOStreamImpl implements IEmployeeDAO {
	public static final String DATA_STORE_FILE_NAME = "employee.dat";

	private Map<String, Employee> employees;

	@SuppressWarnings("unchecked")
	public EmployeeDAOIOStreamImpl() throws EmployeeException {
		File file = new File(DATA_STORE_FILE_NAME);

		if (!file.exists()) {
			employees = new TreeMap<>();
		} else {
			try (ObjectInputStream fin = new ObjectInputStream(
					new FileInputStream(DATA_STORE_FILE_NAME))) {

				Object obj = fin.readObject();

				if (obj instanceof Map) {
					employees = (Map<String, Employee>) obj;
				} else {
					throw new EmployeeException("Not a valid DataStore");
				}
			} catch (IOException | ClassNotFoundException exp) {
				throw new EmployeeException("Loading Data Failed");
			}
		}
	}

	@Override
	public String add(Employee employee) throws EmployeeException {
		String ename = null;
		if (employee!= null) {
			ename = employee.getEname();
			employees.put(ename, employee);
		}
		return ename;
	}

	@Override
	public boolean delete(String ename) throws EmployeeException {
		boolean isDone = false;
		if (ename != null) {
			employees.remove(ename);
			isDone = true;
		}
		return isDone;
	}

	@Override
	public Employee get(String ename) throws EmployeeException {
		return employees.get(ename);
	}

	@Override
	public List<Employee> getAll() throws EmployeeException {
		return new ArrayList<Employee>(employees.values());
	}

	@Override
	public boolean update(Employee employee) throws EmployeeException {
		boolean isDone = false;
		if (employee != null) {
			String ename = employee.getEname();
			employees.replace(ename, employee);
		}
		return isDone;
	}

	
	public void persist() throws EmployeeException {
		try (ObjectOutputStream fout = new ObjectOutputStream(
				new FileOutputStream(DATA_STORE_FILE_NAME))) {
			fout.writeObject(employees);
		} catch (IOException exp) {
			throw new EmployeeException("Saving Data Failed");
		}
	}


}
