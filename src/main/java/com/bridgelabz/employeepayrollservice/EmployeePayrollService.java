package com.bridgelabz.employeepayrollservice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
    public enum IOService {
        CONSOLE_IO, FILE_IO, REST_IO
    };

    private List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();

    public EmployeePayrollService(List<EmployeePayrollData> emList) {
        employeePayrollList = emList;
    }

    public static void main(String[] args) {

    }

    public void readEmployeePayrollData() {
        Scanner consoleInputReader = new Scanner(System.in);
        System.out.println("Enter Employee ID: ");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter Employee name: ");
        String name = consoleInputReader.next();
        System.out.println("Enter Employee Salary: ");
        double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
        consoleInputReader.close();
    }

    /**
     *
     *
     * @param writeTo
     */
    public void writeEmployeePayrollData(IOService writeTo) {
        if (writeTo.equals(IOService.CONSOLE_IO))
            System.out.println("\nWriting Employee Payroll Data to Console\n" + employeePayrollList);
        else if (writeTo.equals(IOService.FILE_IO)) {
            new EmployeePayrollFileIOService().writeEmployeePayrollDate(employeePayrollList);
        }
    }

    public void printData(IOService printTo) {
        if (printTo.equals(IOService.FILE_IO))
            new EmployeePayrollFileIOService().printData();
    }
    public long countEntries() {
        return new EmployeePayrollFileIOService().countEntries();
    }
    /**
     *
     *
     * @param readFrom
     * @return
     */
    public List<EmployeePayrollData> readData(IOService readFrom) {
        if (readFrom.equals(IOService.FILE_IO))
            return new EmployeePayrollFileIOService().readData();
        return null;
    }
    public static boolean deleteFiles(File contentsToDelete) {
        File[] allContents = contentsToDelete.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteFiles(file);
            }
        }
        return contentsToDelete.delete();
    }
}