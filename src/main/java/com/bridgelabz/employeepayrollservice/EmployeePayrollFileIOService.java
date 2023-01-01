package com.bridgelabz.employeepayrollservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class EmployeePayrollFileIOService {
    private static String FILE_NAME = "payroll-data.txt";

    /**
     * UC4
     *
     * @param employeeList
     */
    public void writeEmployeePayrollDate(List<EmployeePayrollData> employeeList) {
        StringBuffer employeeBuffer = new StringBuffer();
        employeeList.forEach(employee -> {
            String employeeString = employee.toString().concat("\n");
            employeeBuffer.append(employeeString);
        });
        try {
            Files.write(Paths.get(FILE_NAME), employeeBuffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printData() {
        try {
            Files.lines(new File(FILE_NAME).toPath()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long countEntries() {
        long entries = 0;
        try {
            entries = Files.lines(new File(FILE_NAME).toPath()).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }
    /**
     * UC6
     *
     * @return
     */
    public List<EmployeePayrollData> readData() {
        List<EmployeePayrollData> employeeList = new ArrayList<EmployeePayrollData>();
        try {
            Files.lines(new File(FILE_NAME).toPath()).map(line -> line.trim()).forEach(n -> {
                String[] employeeData = n.split(",");
                int id = 0;
                String name = "";
                double salary = 0;
                for (String data : employeeData) {
                    if (data.startsWith("Employee"))
                        id = Integer.parseInt(data.substring(24));
                    if (data.startsWith(" name"))
                        name = data.substring(6);
                    if (data.startsWith(" salary"))
                        salary = Double.parseDouble(data.substring(8, data.length() - 1));
                }
                employeeList.add(new EmployeePayrollData(id, name, salary));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}