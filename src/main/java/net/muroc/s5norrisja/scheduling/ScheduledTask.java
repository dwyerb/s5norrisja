package net.muroc.s5norrisja.scheduling;

import net.muroc.s5norrisja.utilities.JsonReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.scheduling.annotation.Scheduled;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScheduledTask {

    @Scheduled(fixedRate=1500)
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
            if(adsb_Record.has("lat"))
            {
                //adsb_Record.put("epoch_time",jEpochNumber);
                adsb_Record.append("epochTime",jEpochNumber);
                System.out.println(adsb_Record.get("lat"));
            }
        }

    }
}
