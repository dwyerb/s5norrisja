package net.muroc.s5norrisja.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("Windows")
@Service
public class ADSBJsonFileWriter_Windows_Impl implements ADSBJsonFileWriter {

    @Value("${outputFile}")
    private String outputFile;

    @Value("${outputPath}")
    private String outputPath;


    @Override
    public void writeToDisk(JSONArray jsonArray) throws IOException {
        try
        {
            File targetFile = new File(outputPath,outputFile);
            FileWriter file = new FileWriter(targetFile);
            System.out.println(outputFile);
            //FileWriter file = new FileWriter("C:\\udemy\\json_files\\json_output.json");
            file.write(jsonArray.toString());
            file.close();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
}
