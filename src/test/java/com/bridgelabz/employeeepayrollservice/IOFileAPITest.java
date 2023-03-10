package com.bridgelabz.employeeepayrollservice;

import org.junit.jupiter.api.Assertions;
import com.bridgelabz.employeepayrollservice.EmployeePayrollService;
import com.bridgelabz.employeepayrollservice.JavaWatchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class IOFileAPITest {
    private static String PATH = "C:\\Users\\aksha\\IdeaProjects\\EmployeePayrollService\\src";
    private static String NEW_DIRECTORY_NAME = "TempPlayGround";

    @Test
    public void givenPathWhenCheckedThenConfirm() throws IOException {
        // Check File Exists
        Path homePath = Paths.get(PATH);
        Assertions.assertTrue(Files.exists(homePath));

        // Delete File and Check File Not Exist
        Path playPath = Paths.get(PATH + "/" + NEW_DIRECTORY_NAME);
        if (Files.exists(playPath))
            EmployeePayrollService.deleteFiles(playPath.toFile());
        Assertions.assertTrue(Files.notExists(playPath));
        // Create Directory
        Files.createDirectory(playPath);
        Assertions.assertTrue(Files.exists(playPath));
        // Create File
        IntStream.range(1, 2).forEach(cntr -> {
            Path tempFile = Paths.get(playPath + "/temp" + cntr);
            Assertions.assertTrue(Files.notExists(tempFile));
            try {
                Files.createFile(tempFile);
            } catch (IOException e) {
            }
            Assertions.assertTrue(Files.exists(tempFile));
        });
        // List Files, Directories as well as files with Extension
        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        Files.newDirectoryStream(playPath, path -> path.toFile().isFile() && path.toString().startsWith("temp"))
                .forEach(System.out::println);
    }
    @Test
    public void givenADirectoryWhenWatchedListsAllTheActivities() throws IOException{
        Path dir = Paths.get(PATH+ "/"+NEW_DIRECTORY_NAME);
        Files.list(dir).filter(Files::isRegularFile).forEach(System.out::println);
        new JavaWatchService(dir).processEvents();
    }
}