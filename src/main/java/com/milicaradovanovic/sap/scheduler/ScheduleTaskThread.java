package com.milicaradovanovic.sap.scheduler;

import com.milicaradovanovic.sap.controller.ScheduleTaskController;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class ScheduleTaskThread implements Runnable {

    private String code;
    private static final Logger logger = LoggerFactory.getLogger(ScheduleTaskController.class);
    private final GroovyShell shell = new GroovyShell();

    public ScheduleTaskThread(String code) {
        this.code = code;
    }

    @Override
    public void run() {
        try {
            this.printWithGroovyShell(this.code);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printWithGroovyShell(String message) throws IOException {
        String fileName = "filename" + LocalDateTime.now().toString();
        String filePath = "src/main/resources/" + fileName + ".txt";
        this.createFile(fileName);

        this.writeToFile(message, filePath);

        Script script = shell.parse(new File(filePath));
        script.run();
    }

    public void createFile(String filePath) throws IOException {
        Path newFilePath = Paths.get(filePath);
        Files.createFile(newFilePath);
    }

    public void writeToFile(String code, String fileName)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.append(' ');
        writer.append(code);

        writer.close();
    }
}
