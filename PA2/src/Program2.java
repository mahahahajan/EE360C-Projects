import java.util.ArrayList;
import java.util.*;

public class Program2 {
	private ArrayList<City> cities; // this is a list of all cities, populated by Driver class.
	private Heap minHeap;

	// feel free to add any fields you'd like, but don't delete anything that's
	// already here

	// needed

	public int numCities; // number of cities
	int[] parents;// Reverse list containing parent of each city
	int[] visited;
	ArrayList<City> cityPath;
	// unsure
	int[] cityMinCosts;

	public Program2(int numCities) {
		minHeap = new Heap();
		cities = new ArrayList<City>();
		this.numCities = numCities;

		// My data structures
		parents = new int[numCities];
		cityPath = new ArrayList<City>();
		visited = new int[numCities];

//        seen = new ArrayList<City>();

		// unsure
		cityMinCosts = new int[numCities];

	}

	/**
	 * findCheapestPathPrice(City start, City dest)
	 *
	 * @param start - the starting city.
	 * @param dest  - the end (destination) city.
	 * @return the minimum cost possible to get from start to dest. If no path
	 *         exists, return Integer.MAX_VALUE
	 */
	public int findCheapestPathPrice(City start, City dest) {
		reset();

		start.setMinCost(0);
		int startCityLocation = start.getCityName();
		parents[startCityLocation] = -1; // This has no parent

		int numNeighbors = start.getNeighbors().size();
		int oldCost = 0;
		int newCost = 0;
		minHeap.buildHeap(cities);
		// This for loop initializes the first level of seen cities
//		for (int i = 0; i < numNeighbors; i++) {
//			City currNeighbor = start.getNeighbors().get(i);
//			int currWeight = start.getWeights().get(i); // getting i from both means this weight corresponds
//			newCost = start.getMinCost() + currWeight; // start.getMinCost() should always be 0, so
//			minHeap.changeKey(currNeighbor, newCost);
//			// basically just setting these up
//			parents[currNeighbor.getCityName()] = startCityLocation;
//		}

		City currCity;

		while ((currCity = minHeap.extractMin()) != null) {
			numNeighbors = currCity.getNeighbors().size();
//			System.out.println("City 3 has a minCost of " + dest.getMinCost());
			oldCost = 0;
			newCost = 0;
			for (int i = 0; i < numNeighbors; i++) {
				City currNeighbor = currCity.getNeighbors().get(i);
				int currWeight = currCity.getWeights().get(i);
				oldCost = currNeighbor.getMinCost();
				newCost = currCity.getMinCost() + currWeight;
				if (newCost < oldCost) {
					if (visited[currNeighbor.getCityName()] != 1) {
						// update stuff
						minHeap.changeKey(currNeighbor, newCost);
//						System.out.println(minHeap.toString());
//						System.out.println(minHeap.toStringIndex());
					}
					else {
						currNeighbor.setMinCost(newCost);
						minHeap.insertNode(currNeighbor);
					}
					parents[currNeighbor.getCityName()] = currCity.getCityName();
				}

			}
			visited[currCity.getCityName()] = 1;
//			cityPath.add(currCity);
		}
		return dest.getMinCost();
	}

	/**
	 * findCheapestPath(City start, City dest)
	 *
	 * @param start - the starting city.
	 * @param dest  - the end (destination) city.
	 * @return an ArrayList of nodes representing a minimum-cost path on the graph
	 *         from start to dest. If no path exists, return null
	 */
	public ArrayList<City> findCheapestPath(City start, City dest) {
		// TODO: implement this function
		reset();

		start.setMinCost(0);
		int startCityLocation = start.getCityName();
		parents[startCityLocation] = -1; // This has no parent

		int numNeighbors = start.getNeighbors().size();
		int oldCost = 0;
		int newCost = 0;
		minHeap.buildHeap(cities);
		// This for loop initializes the first level of seen cities
//		for (int i = 0; i < numNeighbors; i++) {
//			City currNeighbor = start.getNeighbors().get(i);
//			int currWeight = start.getWeights().get(i); // getting i from both means this weight corresponds
//			newCost = start.getMinCost() + currWeight; // start.getMinCost() should always be 0, so
//			minHeap.changeKey(currNeighbor, newCost);
//			// basically just setting these up
//			parents[currNeighbor.getCityName()] = startCityLocation;
//		}

		City currCity;

		while ((currCity = minHeap.extractMin()) != null) {
			numNeighbors = currCity.getNeighbors().size();
//			System.out.println("City 3 has a minCost of " + dest.getMinCost());
			oldCost = 0;
			newCost = 0;
			for (int i = 0; i < numNeighbors; i++) {
				City currNeighbor = currCity.getNeighbors().get(i);
				int currWeight = currCity.getWeights().get(i);
				oldCost = currNeighbor.getMinCost();
				newCost = currCity.getMinCost() + currWeight;
				if (newCost < oldCost) {
					if (visited[currNeighbor.getCityName()] != 1) {
						// update stuff
						minHeap.changeKey(currNeighbor, newCost);
//						System.out.println(minHeap.toString());
//						System.out.println(minHeap.toStringIndex());
					}
					else {
						currNeighbor.setMinCost(newCost);
						minHeap.insertNode(currNeighbor);
					}
					parents[currNeighbor.getCityName()] = currCity.getCityName();
				}

			}
			visited[currCity.getCityName()] = 1;
//			cityPath.add(currCity);
		}
		boolean notFound = true;
		currCity = dest;
		while(parents[currCity.getCityName()] != -1) {
			cityPath.add(0, currCity);
			currCity = cities.get(parents[currCity.getCityName()]);
		}
		cityPath.add(0, currCity);

//    	int counter = 0;
////    	cityMinCosts.add(0);
//    	start.setMinCost(0);
//    	minHeap.buildHeap(cities);
//    	int startIndex = start.getCityName();
//    	visited[startIndex] = 1;
//    	boolean updated = false;
//    	
//    	System.out.println("State before change key");
//    	System.out.println(minHeap.toString());
//    	System.out.println(minHeap.toStringIndex());
//    	System.out.println("Begin pathfinding");
//    	
//    	int weight = 0;
//		int oldCost = 0;
//    	
//    	while(startIndex != dest.getCityName() && counter < 10) {
//    		
//    		for(int i = 0; i < cities.get(startIndex).getWeights().size(); i++) {
//    			
//    			City currStartCity = cities.get(startIndex);
//    			City currNeighbor = currStartCity.getNeighbors().get(i);
//    			weight = currStartCity.getWeights().get(i) +  currStartCity.getMinCost();
//    			oldCost = currNeighbor.getMinCost();
//        		if(weight < oldCost) {
//        			//Need to do this 
//        			cityMinCosts[currNeighbor.getCityName()] = weight;
//            		minHeap.changeKey(currNeighbor, weight);
//            		
//            		//figure out this condition	
//            		updated = true;
//        			
//        			
//        		}
//        		 //some sort of if condition here to see if we need to replace the weight
//        		
//        		
//        		System.out.println(minHeap.toString());
//        		System.out.println(minHeap.toStringCost());
//        		System.out.println(minHeap.toStringIndex());
////        		System.out.println("Min cost list: " + toString(cityMinCosts));
//        	}
//    		
//    		if(!updated) {
//    			City visitedCity = cityPath.get(cityPath.size() - 1);
//    			visited[visitedCity.getCityName()] = 1;
//    			cityPath.remove(cityPath.size() - 1);
//    		}
//    		cityPath.add(minHeap.extractMin());
//        	startIndex = minHeap.findMin().getCityName();
//    		updated = false;
////    		System.out.println("COUNTER IS: " + counter);
//    		counter++;
//    		update();
//    	}
//    	
//    	cityPath.add(minHeap.extractMin());

		return cityPath;
	}

	/**
	 * findLowestTotalCost()
	 *
	 * @return The sum of all edge weights in a minimum spanning tree for the given
	 *         graph. Assume the given graph is always connected. The government
	 *         wants to shut down as many tracks as possible to minimize costs.
	 *         However, they can't shut down a track such that the cities don't
	 *         remain connected. The tracks you're leaving open cost some money (aka
	 *         the edge weights) to maintain. Minimize the overall cost.
	 */
	public int findLowestTotalCost() {
		// TODO: implement this function
		reset();
		City start = cities.get(0);
		start.setMinCost(0);
		int startCityLocation = start.getCityName();
		parents[startCityLocation] = -1; // This has no parent

		int numNeighbors = start.getNeighbors().size();
		int oldCost = 0;
		int newCost = 0;
		minHeap.buildHeap(cities);
		// This for loop initializes the first level of seen cities
//		for (int i = 0; i < numNeighbors; i++) {
//			City currNeighbor = start.getNeighbors().get(i);
//			int currWeight = start.getWeights().get(i); // getting i from both means this weight corresponds
//			newCost = start.getMinCost() + currWeight; // start.getMinCost() should always be 0, so
//			minHeap.changeKey(currNeighbor, newCost);
//			// basically just setting these up
//			parents[currNeighbor.getCityName()] = startCityLocation;
//		}

		City currCity;

		while ((currCity = minHeap.extractMin()) != null) {
			numNeighbors = currCity.getNeighbors().size();
//			System.out.println("City 3 has a minCost of " + dest.getMinCost());
			oldCost = 0;
			newCost = 0;
			cityMinCosts[currCity.getCityName()] = currCity.getMinCost();
			for (int i = 0; i < numNeighbors; i++) {
				City currNeighbor = currCity.getNeighbors().get(i);
				int currWeight = currCity.getWeights().get(i);
				oldCost = currNeighbor.getMinCost();
				newCost = currWeight;
				if (newCost < oldCost) {
					if (visited[currNeighbor.getCityName()] != 1) {
						// update stuff
						minHeap.changeKey(currNeighbor, newCost);
						cityMinCosts[currNeighbor.getCityName()] = newCost;
//						System.out.println(minHeap.toString());
//						System.out.println(minHeap.toStringIndex());
					}
					parents[currNeighbor.getCityName()] = currCity.getCityName();
				}

			}
			visited[currCity.getCityName()] = 1;
//			cityPath.add(currCity);
		}
		int minCost = 0;
		for(int i = 0; i < numCities; i++) {
			minCost += cityMinCosts[cities.get(i).getCityName()];
		}
		return minCost;
	}
	

	// returns edges and weights in a string.
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
		for (int i = 0; i < x.size(); i++) {
			output += x.get(i) + " ";
		}
		return output;
	}

	public void reset() {
		minHeap = new Heap();
		cityPath = new ArrayList<City>();
		cityMinCosts = new int[numCities];
		visited = new int[numCities];
		parents = new int[numCities];
		for (int i = 0; i < cities.size(); i++) {
			cities.get(i).resetMinCost();
			cities.get(i).resetCityIndex();
		}
	}
//    public void update() {
//    	System.out.println("Path so far: ");
//    	for(int i = 0; i < cityPath.size(); i++) {
//    		System.out.print(cityPath.get(i).getCityName() + " -> ");
//    	}
//    	System.out.println("");
//    }
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

	// used by Driver class to populate each Node with correct neighbors and
	// corresponding weights
	public void setEdge(City curr, City neighbor, Integer weight) {
		curr.setNeighborAndWeight(neighbor, weight);
	}

	// This is used by Driver.java and sets vertices to reference an ArrayList of
	// all nodes.
	public void setAllNodesArray(ArrayList<City> x) {
		cities = x;
	}
}
