import java.util.ArrayList;
import java.util.*;

public class Program2 {
    private ArrayList<City> cities;     //this is a list of all cities, populated by Driver class.
    private Heap minHeap;

    // feel free to add any fields you'd like, but don't delete anything that's already here
    public ArrayList<City> cityPath;
    ArrayList<Integer> cityMinCosts;
    
    
    
    public Program2(int numCities) {
        minHeap = new Heap();
        cities = new ArrayList<City>();
        cityPath =  new ArrayList<City>();
        cityMinCosts = new ArrayList<Integer>();
    }

    /**
     * findCheapestPathPrice(City start, City dest)
     *
     * @param start - the starting city.
     * @param dest  - the end (destination) city.
     * @return the minimum cost possible to get from start to dest.
     * If no path exists, return Integer.MAX_VALUE
     */
    public int findCheapestPathPrice(City start, City dest) {
        // TODO: implement this function
    	ArrayList<City> cheapPathCities = findCheapestPath(start, dest);
    	int index = dest.getCityIndex();
    	return cityMinCosts.get(index);
    	
    }

    /**
     * findCheapestPath(City start, City dest)
     *
     * @param start - the starting city.
     * @param dest  - the end (destination) city.
     * @return an ArrayList of nodes representing a minimum-cost path on the graph from start to dest.
     * If no path exists, return null
     */
    public ArrayList<City> findCheapestPath(City start, City dest) {
        // TODO: implement this function
    	
    	int counter = 0;
    	cityMinCosts.add(0);
    	start.setMinCost(0);
    	minHeap.buildHeap(cities);
    	int startIndex = start.getCityName();
    	
    	System.out.println("State before change key");
    	System.out.println(minHeap.toString());
    	System.out.println(minHeap.toStringIndex());
    	System.out.println("Begin pathfinding");
    	
    	while(startIndex != dest.getCityName() && counter < 10) {
    		
    		for(int i = 0; i < cities.get(startIndex).getWeights().size(); i++) {
    			
        		int weight = cities.get(startIndex).getWeights().get(i) +  cities.get(startIndex).getMinCost();
        		int oldCost = cities.get(startIndex).getNeighbors().get(i).getMinCost();
        		if(weight < oldCost) {
        			cityMinCosts.add(cities.get(startIndex).getNeighbors().get(i).getCityName(), weight);
        			City currStartCity = cities.get(startIndex);
        			City currNeighbor = currStartCity.getNeighbors().get(i);
        			minHeap.changeKey(currNeighbor, weight);
        		}
        		 //some sort of if condition here to see if we need to replace the weight
        		
        		
        		System.out.println(minHeap.toString());
        		System.out.println(minHeap.toStringIndex());
//        		System.out.println("Min cost list: " + toString(cityMinCosts));
        	}
    		cityPath.add(minHeap.extractMin());
    		startIndex = minHeap.findMin().getCityName();
    		System.out.println("COUNTER IS: " + counter);
    		counter++;
    	}
    	
    	cityPath.add(minHeap.extractMin());
    	
        return cityPath;
    }

    /**
     * findLowestTotalCost()
     *
     * @return The sum of all edge weights in a minimum spanning tree for the given graph.
     * Assume the given graph is always connected.
     * The government wants to shut down as many tracks as possible to minimize costs.
     * However, they can't shut down a track such that the cities don't remain connected.
     * The tracks you're leaving open cost some money (aka the edge weights) to maintain. Minimize the overall cost.
     */
    public int findLowestTotalCost() {
        // TODO: implement this function
        return -1;
    }

    //returns edges and weights in a string.
    public String toString() {
        String o = "";
        for (City v : cities) {
            boolean first = true;
            o += "City ";
            o += v.getCityName();
            o += " has neighbors: ";
            ArrayList<City> ngbr = v.getNeighbors();
            for (City n : ngbr) {
                o += first ? n.getCityName() : ", " + n.getCityName();
                first = false;
            }
            first = true;
            o += " with weights ";
            ArrayList<Integer> wght = v.getWeights();
            for (Integer i : wght) {
                o += first ? i : ", " + i;
                first = false;
            }
            o += System.getProperty("line.separator");

        }

        return o;
    }
    public String toString(ArrayList<?> x) {
		String output = "";
    	for(int i = 0; i < x.size(); i++) {
			output += x.get(i) + " ";
		}
    	return output;
    }

///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

    public Heap getHeap() {
        return minHeap;
    }

    public ArrayList<City> getAllCities() {
        return cities;
    }

    //used by Driver class to populate each Node with correct neighbors and corresponding weights
    public void setEdge(City curr, City neighbor, Integer weight) {
        curr.setNeighborAndWeight(neighbor, weight);
    }

    //This is used by Driver.java and sets vertices to reference an ArrayList of all nodes.
    public void setAllNodesArray(ArrayList<City> x) {
        cities = x;
    }
}
