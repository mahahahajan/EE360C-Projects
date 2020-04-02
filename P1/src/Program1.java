
/*
 * Name: Pulkit Mahajan
 * EID: pm28838
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your solution goes in this class.
 * 
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */
public class Program1 extends AbstractProgram1 {
	/**
	 * Determines whether a candidate Matching represents a solution to the Stable
	 * Marriage problem. Study the description of a Matching in the project
	 * documentation to help you with this.
	 */
	@Override
	public boolean isStableMatching(Matching marriage) {
		/* TODO implement this function */
//		System.out.println(marriage.getLocationCount());
//    	for(int i = 0; i < ); i++) {
//    		System.out.println(i);
//    	}
		if (marriage == null || marriage.getEmployeeMatching() == null) {
			return true; // if its null its still stable technically
		} else {
			ArrayList<Integer> employee_matching = marriage.getEmployeeMatching();
//			Matching employeeOptimal = 
			
			Matching locationOptimal = stableMarriageGaleShapley_locationoptimal(marriage);
			if(employee_matching.equals(locationOptimal.getEmployeeMatching())) {
				return true;
			}
			else {
				Matching employeeOptimal = stableMarriageGaleShapley_employeeoptimal(marriage);
				if(employee_matching.equals(employeeOptimal.getEmployeeMatching())) {
					return true;
				}
				else {

					return false;
				}
			}
		}

	}

	/**
	 * Determines a employee optimal solution to the Stable Marriage problem from
	 * the given input set. Study the description to understand the variables which
	 * represent the input to your solution.
	 *
	 * @return A stable Matching.
	 */
	@Override
	public Matching stableMarriageGaleShapley_employeeoptimal(Matching marriage) {
		/* TODO implement this function */

		// ok i might have to do functions for this one to keep it clean and help debug
		int n = marriage.getEmployeeCount();
		ArrayList<Integer> employee_matching = new ArrayList<Integer>();
		ArrayList<Integer> openSpots = new ArrayList<Integer>();
		Queue<Integer> q = new LinkedList<>();
		int flag = 0;

		int indexOfCurrEmployeeWithinJob = -1;
		int indexOfEmployeeAlreadyAtJob = -1;

		for (int i = 0; i < n; i++) {
			// need to set size
			employee_matching.add(-1);
			q.add(i); // this is O(1) so its ok
		}
		for (int i = 0; i < marriage.getLocationCount(); i++) {
			int numOpenSpots = marriage.getLocationSlots().get(i);
			openSpots.add(numOpenSpots);
		}

		while (!q.isEmpty()) {
			int currEmployee = q.poll();
			for (int i = 0; i < marriage.getEmployeePreference().get(currEmployee).size(); i++) {

				int employeeCurrPref = marriage.getEmployeePreference().get(currEmployee).get(i);
//				out.println("Current Employee: " + currEmployee + "     Current Preference: " + employeeCurrPref);
				if (openSpots.get(employeeCurrPref) > 0) {
					employee_matching.set(currEmployee, employeeCurrPref); // setting current employee to the proper
					openSpots.set(employeeCurrPref, openSpots.get(employeeCurrPref) - 1);
					break;
				} else {
					ArrayList<Integer> storePreference = marriage.getLocationPreference().get(employeeCurrPref);
					int counter = storePreference.size() - 1;
					while (counter > -1) {
						int tempEmployee = storePreference.get(counter);
						if (tempEmployee == currEmployee) { // if the pref hits our employee
							indexOfCurrEmployeeWithinJob = counter; // I hit this first which means this is lower than
																	// anything else

							break; // THIS HAS BEEN HIT SO I KNOW THERES NO REPLACE NOW
						}
						if (employee_matching.get(tempEmployee) == employeeCurrPref) {
							// REPLACE
							indexOfEmployeeAlreadyAtJob = counter;
							int oldEmployee = tempEmployee;
							q.add(oldEmployee);
							employee_matching.set(currEmployee, employeeCurrPref);
							employee_matching.set(oldEmployee, -1);
//							currEmployee = 
							i = 0;
							currEmployee = q.poll();
							break;

						}
						counter--;
					}

				}
			}

		}

//		
//		System.out.println("THIS IS RIGHT BEFORE THE RETUNR STATEMENT");
		Matching employeesMatchedMarriage = new Matching(marriage.getLocationCount(), marriage.getEmployeeCount(),
				marriage.getLocationPreference(), marriage.getEmployeePreference(), marriage.getLocationSlots(),
				employee_matching);
		return employeesMatchedMarriage; /* TODO remove this line */

	}

	public boolean doesLocationHaveOpening(Matching marriage, int location, ArrayList<Integer> locationSpots) {

		return false;
	}

	/**
	 * Determines a location optimal solution to the Stable Marriage problem from
	 * the given input set. Study the description to understand the variables which
	 * represent the input to your solution.
	 *
	 * @return A stable Matching.
	 */
	@Override
	public Matching stableMarriageGaleShapley_locationoptimal(Matching marriage) {
		/* TODO implement this function */

		int n = marriage.getEmployeeCount();
		ArrayList<Integer> employee_matching = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {
			// need to set size
			employee_matching.add(-1);
		}
//		System.out.println("Employrr count is " + marriage.getEmployeeCount());

		for (int i = 0; i < marriage.getLocationCount(); i++) { // i is my currentLocation

			int open_spots = marriage.getLocationSlots().get(i); // open location spots
			int wantedEmployeeCounter = -1; // this is so that if we reach the end and need to find the next one on the
											// preference list for cmpany

			for (int j = 0; j < open_spots; j++) { // while I still have open spots

				int wanted_employee = this.getNextWantedEmployee(marriage.getLocationPreference(), i,
						wantedEmployeeCounter);

				if (employee_matching.get(wanted_employee) == -1) {
					employee_matching.set(wanted_employee, i);
					wantedEmployeeCounter++;

				} else {
					int currJob = employee_matching.get(wanted_employee);
					int currJobIndex = -1;
					int currCompanyIndex = -1;
					for (int a = 0; a < marriage.getEmployeePreference().get(wanted_employee).size(); a++) {
						if (marriage.getEmployeePreference().get(wanted_employee).get(a) == i) {
							currCompanyIndex = a;
						}
						if (marriage.getEmployeePreference().get(wanted_employee).get(a) == currJob) {
							currJobIndex = a;

						}
					}
					if (currJobIndex < currCompanyIndex) {

						employee_matching.set(wanted_employee, i);
						wantedEmployeeCounter++;
						i = 0; // instead of doing something like putting all the locations in the queue, we're
								// just gonna be marignally inefficient and just restart the location list --
								// logic should still work
					} else {
						wantedEmployeeCounter++;
					}

				}

			}
		}
		Matching employeesMatchedMarriage = new Matching(marriage.getLocationCount(), marriage.getEmployeeCount(),
				marriage.getLocationPreference(), marriage.getEmployeePreference(), marriage.getLocationSlots(),
				employee_matching);
		return employeesMatchedMarriage; /* TODO remove this line */
	}

	public int getNextWantedEmployee(ArrayList<ArrayList<Integer>> locationPreference, int location, int curr) {

		int nextEmployee = locationPreference.get(location).get(curr + 1);
		return nextEmployee;

	}
}
