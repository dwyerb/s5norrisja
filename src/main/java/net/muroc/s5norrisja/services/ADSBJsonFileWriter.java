package net.muroc.s5norrisja.services;
import org.json.JSONArray;
import java.io.IOException;

public interface ADSBJsonFileWriter {

    public void writeToDisk(JSONArray jsonArray) throws IOException;
}
