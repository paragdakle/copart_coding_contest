package extracttext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dakle on 4/4/17.
 */
public class DataManager {

    private String filePath = "";

    private BufferedReader reader = null;

    public List<GeoLocation> geoLocations;

    public DataManager(String filePath) {
        this.filePath = filePath;
        this.geoLocations = new ArrayList<>();
    }

    public boolean openFile() {
        try {
            File f = new File(this.filePath);
            reader = new BufferedReader(new FileReader(f));
            if(reader != null) {
                return true;
            }
            return false;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getLine() {
        try {
            if(reader != null) {
                return reader.readLine();
            }
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean closeFile() {
        try {
            if(reader != null) {
                reader.close();
                return true;
            }
            return false;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void generateData() {
        String line;

    }
}
