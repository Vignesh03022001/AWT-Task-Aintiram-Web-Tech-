package com.employee.analytics.system;

import javax.swing.text.DateFormatter;
import java.io.File;
import java.io.FileReader;
import java.text.spi.DateFormatProvider;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeProcessing {

    public static void main(String args[]) {
        try {
            File file = new File("employeeDetails.csv");
            FileReader reader = new FileReader(file);
            int character;
            StringBuilder str = new StringBuilder();
            while ((character = reader.read()) > 0) {
                str.append((char) character);
            }
            convertStrAsList(str.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void convertStrAsList(String str) {
        String[] eachLine = str.split("\n");
        List<Employee> empList = new ArrayList<>();
        for (String s : eachLine) {
            String[] employeeData = s.split(",");
            int salary = 0;
            if (!employeeData[3].isBlank()) salary = Integer.parseInt(employeeData[3]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(employeeData[4],formatter);
            Employee e = new Employee(employeeData[0], employeeData[1], employeeData[2], salary, date, employeeData[4]);
            empList.add(e);
        }

        for (Employee e : empList) {
            //Find and print the names of all employees who work in the "IT" department.
            if (e.getDept().equalsIgnoreCase("IT"))
                System.out.println(e.getName());
            if (e.getSalary() > 70000) System.out.println(e.getName() + "--" + e.getSalary());
        }
        int companyTotalSalaryPaid= empList.stream().mapToInt(Employee::getSalary).sum();
        System.out.println("Company's Total Cost for Salary -->"+companyTotalSalaryPaid);
        Optional<Employee> maxSalariedEmployee = empList.stream().max(Comparator.comparingInt(Employee::getSalary));
        maxSalariedEmployee.ifPresent(e->{
            System.out.println("Max Salaried Employee -->"+e.toString());
        });
        List<Employee> employeesJoinedBefore2022 = empList.stream().filter(e->{
            LocalDate dt = e.getDoj();
            return dt.getYear() < 2021;
        }).toList();
        System.out.println("Employee Joined Before 2022 -->>"+ employeesJoinedBefore2022);
    }
}
