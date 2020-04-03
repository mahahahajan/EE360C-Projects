// feel free to add things to this file. Just don't remove anything

import java.util.*;

public class City {
    private int minCost;
    private int cityName;
    private int cityIndex = -1;
    private ArrayList<City> neighbors;
    private ArrayList<Integer> weights;

    public City(int x) {
        cityName = x;
        minCost = Integer.MAX_VALUE;
        neighbors = new ArrayList<City>();
        weights = new ArrayList<Integer>();
    }
    public City(int x, int minCost) {
    	cityName = x;
        neighbors = new ArrayList<City>();
        weights = new ArrayList<Integer>();
        this.minCost = minCost;	
    }
    
    public void setNeighborAndWeight(City n, Integer w) {
        neighbors.add(n);
        weights.add(w);
    }

    public ArrayList<City> getNeighbors() {
        return neighbors;
    }

    public ArrayList<Integer> getWeights() {
        return weights;
    }

    public int getMinCost() {
        return minCost;
    }

    public int getCityIndex() {
        return cityIndex;
    }

    public void setMinCost(int x) {
        minCost = x;
    }
    public void setCityIndex(int index) {
    	cityIndex = index;
    }
    public void resetMinCost() {
        minCost = Integer.MAX_VALUE;
    }
    public void resetCityIndex() {
    	cityIndex = -1;
    }
    public int getCityName() {
        return cityName;
    }
}
