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
        try {
            GeoLocation location = new GeoLocation();
            String attrs[] = geoLocationStr.split(",");
            if (attrs.length != 6) return null;
            location.zipCode = attrs[0].replaceAll("\"", "");
            if(attrs[1].length() > 0)
                location.latitude = Double.parseDouble(attrs[1].replaceAll("\"", ""));
            else {
                return null;
            }
            if(attrs[2].length() > 0)
                location.longitude = Double.parseDouble(attrs[2].replaceAll("\"", ""));
            location.city = attrs[3].replaceAll("\"", "");
            location.state = attrs[4].replaceAll("\"", "");
            location.county = attrs[5].replaceAll("\"", "");
            return location;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(geoLocationStr);
        }
        return null;
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
        double distanceLatitude = location1.degreeToRadians(location2.getLatitude() - location1.getLatitude());
        double distanceLongitude = location1.degreeToRadians(location1.getLongitude() - location1.getLongitude());
        double value1 = (Math.sin(distanceLatitude / 2) * Math.sin(distanceLatitude / 2))
                + (Math.cos(location1.degreeToRadians(location1.getLatitude())) * Math.cos(location2.degreeToRadians(location2.getLatitude()))
                * Math.sin(distanceLongitude / 2) * Math.sin(distanceLongitude / 2));
        double value2 = 2 * Math.atan2(Math.sqrt(value1), Math.sqrt(1 - value1));
        return value2 * radius;
    }

    private double degreeToRadians(double degree) {
        return degree * (Math.PI / 180);
    }

    @Override
    public String toString() {
        return "" + this.zipCode + " " + this.latitude + " " + this.longitude +
                " " + this.city + " " + this.state + " " + this.county;
    }
}
