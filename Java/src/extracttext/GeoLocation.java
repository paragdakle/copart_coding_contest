package extracttext;

/**
 * Created by dakle on 4/4/17.
 */
public class GeoLocation {

    private String zipCode;

    private double latitude;

    private double longitude;

    private String city;

    private String state;

    private String county;

    public GeoLocation() {
        this.zipCode = "";
        this.latitude = 0;
        this.longitude = 0;
        this.city = "";
        this.state = "";
        this.county = "";
    }

    public static GeoLocation convertStringToGeolocation(String geoLocationStr) {
        GeoLocation location = new GeoLocation();
        String attrs[] = geoLocationStr.split(",");
        System.out.println(attrs[0]);
        return location;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public static double calculateDistance(GeoLocation location1, GeoLocation location2) {
        int radius = 6371;
        double distanceLatitude = location1.degreeToRadians(location1.getLatitude() - location2.getLatitude());
        double distanceLongitude = location1.degreeToRadians(location1.getLongitude() - location2.getLongitude());
        double value1 = (Math.sin(distanceLatitude / 2) * Math.sin(distanceLatitude / 2))
                + (Math.cos(location1.degreeToRadians(location1.getLatitude())) * Math.cos(location2.degreeToRadians(location2.getLatitude())))
                + (Math.sin(distanceLongitude / 2) * Math.sin(distanceLongitude / 2));
        double value2 = 2 * Math.atan2(Math.sqrt(value1), Math.sqrt(1 - value1));
        return value2 * radius;
    }

    private double degreeToRadians(double degree) {
        return degree * (Math.PI / 180);
    }
}
