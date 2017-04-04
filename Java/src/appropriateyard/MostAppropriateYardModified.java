import java.util.*;

public class MostAppropriateYardModified{
    static ArrayList<ArrayList<Integer>> K = null;
    
    public static void main(String args[]) {
        DataManager manager = new DataManager("zip_codes_states.csv"); //Class to Load the CSV Data
        manager.generateData();
        GeoLocation inputLocation = new GeoLocation();  //Class to handle each Location
        GeoLocation C[] = Cluster(manager.geoLocations, 3); //Clustering of Locations using K Means
        //Query Location Coordinates accepted as arguments
        inputLocation.setLatitude(Double.parseDouble(args[0]));
        inputLocation.setLongitude(Double.parseDouble(args[1]));
        double min = inputLocation.calculateDistance(inputLocation,C[C.length-1]);
        int cur = C.length-1;
        //Find Nearest Cluster
        for(int i=0;i < C.length-1;i++){

          double dis = inputLocation.calculateDistance(inputLocation,C[i]);
          if(dis < min){
            min = dis;
            cur = i;
          }
        }
        
        //Search for query point in closest Cluster and find 3 Closest Yards
        double minDistance = -1, distance;
        double min1=-1,min2=-1,min3=-1;
        GeoLocation minDistanceLocation1 = null;
        GeoLocation minDistanceLocation2 = null;
        GeoLocation minDistanceLocation3 = null;
        for (int x : K.get(cur)) {
                
            distance = GeoLocation.calculateDistance(manager.geoLocations.get(x), inputLocation);
            /*if(min1 == -1 || distance < min1) {
                min1 = distance;
                minDistanceLocation1 = manager.geoLocations.get(x);
            }*/
            if(min1 > distance || min1 == -1)
            {	
            min3 = min2;
            min2 = min1;
            min1 = distance;
            minDistanceLocation3=minDistanceLocation2;
            minDistanceLocation2=minDistanceLocation1;
            minDistanceLocation1= manager.geoLocations.get(x);
            }
            else if(min2 > distance)
            {	
            min3=min2;
            min2=distance;
            minDistanceLocation3=minDistanceLocation2;
            minDistanceLocation2=manager.geoLocations.get(x);
            }
            else if(min3 > distance)
            {	
            min3=distance;
            minDistanceLocation3=manager.geoLocations.get(x);
            }
        }
        System.out.println(minDistanceLocation1.toString());
        System.out.println(minDistanceLocation2.toString());
        System.out.println(minDistanceLocation3.toString());
    }

  static GeoLocation[] Cluster(List<GeoLocation> G,int k){
      GeoLocation helper = new GeoLocation();
      
      GeoLocation C[] = new GeoLocation[k]; //Centers
      Random r = new Random();
      for(int i=0;i < k;i++){
        int c = r.nextInt(G.size());
        C[i] = G.get(c);
      }
   int I = 50;

   while(I > 0){
    
     K = new ArrayList<>(); // Start with empty  Clusters
     for(int c=0;c < k;c++){
      K.add(new ArrayList<Integer>());
     } 
     for(int i = 0;i < G.size(); i++){
        double min = helper.calculateDistance(G.get(i),C[0]);
        int cur = 0;
        //Find Distance to each Cluster
        for(int c = 1;c < k;c++){
          double dis = helper.calculateDistance(G.get(i),C[c]);
          if(dis < min){
            min = dis;
            cur = c;
          }
        }
        K.get(cur).add(i);
     }
     
     for(int c=0;c < k;c++){
      double latSum = C[c].getLatitude()/ (K.get(c).size()+1);
      double lngSum = C[c].getLongitude() / (K.get(c).size()+1);
      for(int x:K.get(c)){
        latSum += (G.get(x).getLatitude()/ (K.get(c).size()+1));
        lngSum += (G.get(x).getLongitude() / (K.get(c).size()+1));
      }
      //New Cluster Center is the centre of all points in the cluster
      C[c].setLatitude(latSum);
      C[c].setLongitude(lngSum);
     }
     
   I--;
   }
   return C;
  }

}
