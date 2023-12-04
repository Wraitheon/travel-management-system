package Models;

import java.util.List;

public class Destination {
    private int destination_ID;
    private String name;
    private List<Landmarks> landmarks;
    

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
   
    public List<Landmarks> getLandmarks() {
        return landmarks;
    }
    public void setLandmarks(List<Landmarks> landmarks) {
        this.landmarks = landmarks;
    }
    
    public Destination(int destination_ID, String name) {
        this.destination_ID = destination_ID;
        this.name = name;
    }
    public Destination(String tripDestination) {
    }
    @Override
    public String toString() {
         return name + " (ID: " + destination_ID + ")";
    }
    public int getDestination_ID() {
        return destination_ID;
    }
  

    //getter setters
}
