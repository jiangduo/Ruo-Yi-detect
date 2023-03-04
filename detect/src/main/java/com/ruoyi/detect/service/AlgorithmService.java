package com.ruoyi.detect.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlgorithmService {

    public String runPythonAlgorithm(int num1, int num2) throws IOException {
        // create command list to run the python script
        List<String> commandList = new ArrayList<String>();
        commandList.add("python");
        commandList.add("algorithm.py");
        commandList.add(Integer.toString(num1));
        commandList.add(Integer.toString(num2));

        // create process builder to run the command
        ProcessBuilder processBuilder = new ProcessBuilder(commandList);
        Process process = processBuilder.start();

        // read the output of the command
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder output = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            output.append(line);
        }

        // wait for the command to complete and get the exit code
        int exitCode = -1;
        try {
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // check if the command completed successfully
        if (exitCode == 0) {
            return output.toString();
        } else {
            return "Error running Python algorithm.";
        }
    }
}
