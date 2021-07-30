package net.muroc.s5norrisja.services;
import org.json.JSONArray;

import java.io.IOException;


public interface ADSBWebReader {

    public JSONArray readADSBEndpoint() throws IOException;
}
