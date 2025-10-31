package com.employee.analytics.system;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class EmployeeProcessing {

	public static void main(String args[]) {
		try {
			File file = new File("employeeDetails.csv");
			FileReader reader = new FileReader(file);
			int character;
			StringBuilder str = new StringBuilder();
			while((character = reader.read())>0) {
				str.append((char)character);
			}
//			System.out.println(str.toString());
			convertStrAsList(str.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void convertStrAsList(String str) {
		String[] eachLine = str.split("\n");
		List<Employee> empList = new ArrayList<>();
//		System.out.println(Arrays.toString(eachLine));
		System.out.println(eachLine[3]);
		for(String s : eachLine) {
			String [] employeeData = s.split(",");
//			System.out.println(Arrays.toString(employeeData));
			int salary = Integer.parseInt(employeeData[3]);
			String [] dateParts = employeeData[4].split("-");
			int year = Integer.parseInt(dateParts[2]);
			int month = Integer.parseInt(dateParts[1]);
			int day = Integer.parseInt(dateParts[0]);
			LocalDate date = LocalDate.of(year,month,day);
			Employee e = new Employee(employeeData[0], employeeData[1], employeeData[2], salary, date, employeeData[4]);
			empList.add(e);
		}
		
		for(Employee e:empList) {
//			System.out.println(e.toString());
		}
	}
}
