package src;

import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.BufferedReader;

public class JavaToPython {

    public static String getGeminiResponse(String prompt) {
        String response = "";

        try {
            //Filepath to python script
            String pyScriptPath = "src/gemini_ai.py";
            ProcessBuilder pb = new ProcessBuilder("python", pyScriptPath);

            Process process = pb.start();

            //Writes standard input to python
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write(prompt);
            writer.newLine();
            writer.flush();
            writer.close();

            //Reads standard output from python
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
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
}