package net.muroc.s5norrisja.services;

import net.muroc.s5norrisja.utilities.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ADSBWebReader_LocalHost_Impl implements ADSBWebReader {
    @Override
    public JSONArray readADSBEndpoint() throws IOException {
        JSONObject json = JsonReader.readJsonFromUrl("http://localhost:8080/data/aircraft.json");
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
        return jsonArray;
    }
}
