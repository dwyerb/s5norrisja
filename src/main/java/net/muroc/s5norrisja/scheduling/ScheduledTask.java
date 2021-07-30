package net.muroc.s5norrisja.scheduling;

import net.muroc.s5norrisja.services.ADSBJsonFileWriter;
import net.muroc.s5norrisja.services.ADSBWebReader;
import net.muroc.s5norrisja.utilities.JsonReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.*;

import java.io.File;
import java.io.FileWriter;

import java.io.IOException;

@Component
public class ScheduledTask {

    private final ADSBWebReader adsbWebReader;
    private final ADSBJsonFileWriter adsbJsonFileWriter;

    public ScheduledTask(ADSBWebReader adsbWebReader, ADSBJsonFileWriter adsbJsonFileWriter)
    {
        this.adsbWebReader = adsbWebReader;
        this.adsbJsonFileWriter = adsbJsonFileWriter;
    }

    @Scheduled(fixedRate = 10000)
    public void getAirPlanes() throws IOException
    {
        JSONArray aircraftJson = adsbWebReader.readADSBEndpoint();
        adsbJsonFileWriter.writeToDisk(aircraftJson);
    }

    /*

    @Value("${outputFile}")
    private String outputFile;

    @Value("${outputPath}")
    private String outputPath;

    @Scheduled(fixedRate=15000)
    public void readADSBEndpoint() throws IOException, JSONException {
        JSONObject json = JsonReader.readJsonFromUrl("http://192.168.254.99:8080/data/aircraft.json");
        String jpart = json.get("aircraft").toString();
        Double dEpoch = (Double) json.get("now");

        Long jEpochNumber = dEpoch.longValue();
        JSONArray jsonArray = new JSONArray(jpart);

        for(int i = 0; i < jsonArray.length(); i++)
        {
            System.out.println(jEpochNumber);
            JSONObject adsb_Record = jsonArray.getJSONObject(i);
            if(adsb_Record.has("lat") && adsb_Record.has("lon"))
            {
                //add linux epoch time to record
                adsb_Record.append("epochTime",jEpochNumber);
                System.out.println(adsb_Record.get("lat"));
            }
            else
            {
                //if current record does not have lat AND lon, delete from array
                jsonArray.remove(i);
            }
        }

        //write json to file
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
        System.out.println("Done");

    }
    */
}
