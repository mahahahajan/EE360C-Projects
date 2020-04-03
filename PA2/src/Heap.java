import java.util.ArrayList;

public class Heap {
    
	private ArrayList<City> minHeap;
    
    
    
    public Heap() {
        minHeap = new ArrayList<City>();
    }

    /**
     * buildHeap(ArrayList<City> cities)
     * Given an ArrayList of Cities, build a min-heap keyed on each City's minCost
     * Time Complexity - O(n)
     *
     * @param cities
     */
    public void buildHeap(ArrayList<City> cities) {
        // TODO: implement this method
    	for(int i = 0; i < cities.size(); i++) {
    		minHeap.add(cities.get(i));
    	}
    	for(int i = cities.size()/2 - 1; i >= 0; i--) {
    		siftDown(i);
    	}
    	for(int i = 0; i < cities.size(); i++) {
    		minHeap.get(i).setCityIndex(i);
    	}
    }
    
    /**
     * insertNode(City in)
     * Insert a City into the heap.
     * Time Complexity - O(log(n))
     *
     * @param in - the City to insert.
     */
    public void insertNode(City in) {
        // TODO: implement this method

    	in.setCityIndex(minHeap.size());
    	minHeap.add(in);
    	siftUp(minHeap.size() - 1);
    }

    /**
     * findMin()
     *
     * @return the minimum element of the heap. Must run in constant time.
     */
    public City findMin() {
        
        return minHeap.get(0);
    }

    /**
     * extractMin()
     * Time Complexity - O(log(n))
     *
     * @return the minimum element of the heap, AND removes the element from said heap.
     */
    public City extractMin() {
    	if(minHeap.size() != 0) {
    		City minCity = minHeap.get(0);
        	delete(0);
            return minCity;
    	}
    	else {
    		return null;
    	}
    }

    /**
     * delete(int index)
     * Deletes an element in the min-heap given an index to delete at.
     * Time Complexity - O(log(n))
     *
     * @param index - the index of the item to be deleted in the min-heap.
     */
    public void delete(int index) {
    	
    	swap(index, minHeap.size() - 1);
    	minHeap.get(index).setCityIndex(index);
    	minHeap.get(minHeap.size() - 1).setCityIndex(minHeap.size() - 1);
    	minHeap.remove(minHeap.size()-1);

    	
    	siftDown(index);
    }

    /**
     * changeKey(City c, int newCost)
     * Updates and rebalances a heap for City c.
     * Time Complexity - O(log(n))
     *
     * @param c       - the city in the heap that needs to be updated.
     * @param newCost - the new cost of city c in the heap (note that the heap is keyed on the values of minCost)
     */
    public void changeKey(City c, int newCost) {
        // TODO: implement this method
    	int index = c.getCityIndex();
//    	c.setMinCost(newCost);
//    	siftUp(index);
    	delete(index);
    	c.setMinCost(newCost);
    	insertNode(c);
    }

    public String toString() {
        String output = "Heap(city names): ";
        for (int i = 0; i < minHeap.size(); i++) {
            output += minHeap.get(i).getCityName() + " ";
        }
        return output;
    }
    public String toStringIndex() {
        String output = "Heap(indexes): ";
        for (int i = 0; i < minHeap.size(); i++) {
            output += minHeap.get(i).getCityIndex() + " ";
        }
        return output + "\n";
    }
    public String toStringCost() {
    	String output = "Heap(cost): ";
    	for (int i = 0; i < minHeap.size(); i++) {
            output += minHeap.get(i).getMinCost() + " ";
        }
        return output;
    }
    
    public void siftUp(int i) {
    	int size = minHeap.size();
    	int parent = (i/2) ;
    	int parentCity = minHeap.get(parent).getCityName();
    	
    	
//    	System.out.println("THE PARENT CITY IS " + parentCity);
//    	System.out.println();
//    	System.out.println(toString());
//    	System.out.println(toStringIndex());
//    	System.out.println();
    	
    	
    	if(minHeap.get(parent).getMinCost() > minHeap.get(i).getMinCost()) {
//    		System.out.println("Need to swap");
    		swap(parent, i);
    		
    		siftUp(parent);
    	}
    	minHeap.get(parent).setCityIndex(parent);
		minHeap.get(i).setCityIndex(i);
    	
    }
    
    public void siftDown(int i) {
    	int left = 2 * i + 1;
    	int right = 2 * i + 2;
    	int smallest = i;
    	int size = minHeap.size();
    	
//    	System.out.println();
//    	System.out.println(toString());
//    	System.out.println(toStringIndex());
//    	System.out.println();
//    	
    	if(right < size) {
    		// has a right child
    		if(minHeap.get(i).getMinCost() > minHeap.get(right).getMinCost()) {
    			//Need to swap with right with current
    			smallest = right;
    		}
    	}
    	
    	if(left < size ) { // im gonna give left priority arbritarily
    		//Has a left child
    		if(minHeap.get(i).getMinCost() > minHeap.get(left).getMinCost()) {
    			//Need to swap with left with current
    			smallest = left;
    		}
    	}
    	
    	if(smallest != i) { //ok so even tho I check against left first, priority will go to the right side bc of the way the check works
    		
    		swap(i, smallest); //swap function
    		siftDown(smallest);
    	}
    	
//    	minHeap.get(smallest).setCityIndex(smallest);
//    	minHeap.get(left).setCityIndex(left);
//    	minHeap.get(right).setCityIndex(right);
    }
    //TODO: Swap function
    public void swap(int current, int small) {

    	City currentCity = minHeap.get(current); // should be current
    	 //currentCitys

    	minHeap.set(current, minHeap.get(small));//set smaller object to the current position
    	minHeap.set(small, currentCity);
    	
    	
    	minHeap.get(current).setCityIndex(current);
    	minHeap.get(small).setCityIndex(small);
    	
//    	System.out.println("Did a swap");
//    	System.out.println(toString());
//    	System.out.println(toStringIndex());
    }
    
///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

    public ArrayList<City> toArrayList() {
        return minHeap;
    }
}
