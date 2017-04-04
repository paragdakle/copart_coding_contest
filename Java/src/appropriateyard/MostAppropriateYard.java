package appropriateyard;

/**
 * Created by dakle on 4/4/17.
 */
public class MostAppropriateYard {

    public static void main(String args[]) {
        DataManager manager = new DataManager("zip_codes_states.csv");
        manager.generateData();
        GeoLocation inputLocation = new GeoLocation();
        inputLocation.setLatitude(Double.parseDouble(args[0]));
        inputLocation.setLongitude(Double.parseDouble(args[1]));
        double minDistance = -1, distance;
        GeoLocation minDistanceLocation = null;
        for (GeoLocation location :
                manager.geoLocations) {
            distance = GeoLocation.calculateDistance(location, inputLocation);
            if(minDistance == -1 || distance < minDistance) {
                minDistance = distance;
                minDistanceLocation = location;
            }
        }
        System.out.println(minDistanceLocation.toString());
    }
}
