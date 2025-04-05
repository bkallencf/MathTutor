package src;

import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.BufferedReader;

public class JavaToPython {

    //Passes a String prompt to Gemini in gemini_ai and gives the output response back to Main
    public static String getGeminiResponse(String prompt) {
        String response = "";

        try {
            //Filepath to python script
            String pyScriptPath = "src/gemini_ai.py";
            ProcessBuilder pb = new ProcessBuilder("python", pyScriptPath);

            Process process = pb.start();

            //Writes standard input to python
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream(), StandardCharsets.UTF_8));
            writer.write(prompt);
            writer.newLine();
            writer.flush();
            writer.close();

            //Reads standard output from python
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            String line;
            StringBuilder outputBuilder = new StringBuilder();

            while((line = reader.readLine()) != null) {
                outputBuilder.append(line).append("\n");
            }

            response = outputBuilder.toString();

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Python Script exited with code: " + exitCode);
            }

        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }

        return response;
    }

    //Tells initialize_log to clear the memory log
    public static void clearGeminiLog() {
        try {
            //Filepath to python file
            String pyInitializeLogPath = "src/initialize_log.py";
            ProcessBuilder pb = new ProcessBuilder("python", pyInitializeLogPath);


            Process process = pb.start();

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8)
            );

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[Log Init Python]: " + line);
            }
    
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Python Log Init Script exited with code: " + exitCode);
            }
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}