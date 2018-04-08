import java.util.Scanner;

/**
 * File: PassengerShip.java 
 * Author: Maria Tkacheva 
 * Date: March 24, 2018
 * Assignment:Project 1 
 * UMUC CMSC 335 
 * PassengerShip class extends Ship and has
 * cargo ship specific variables
 */
public class PassengerShip extends Ship {
	private int numberOfOccupiedRooms;
	private int numberOfPassengers;
	private int numberOfRooms;

	public PassengerShip(Scanner sc) {
		super(sc);
		if (sc.hasNextInt())
			numberOfPassengers = sc.nextInt();
		if (sc.hasNextInt())
			numberOfRooms = sc.nextInt();
		if (sc.hasNextInt())
			numberOfOccupiedRooms = sc.nextInt();
	} // end Scanner constructor

	public int getNumberOfOccupiedRooms() {
		return numberOfOccupiedRooms;
	}

	public void setNumberOfOccupiedRooms(int numberOfOccupiedRooms) {
		this.numberOfOccupiedRooms = numberOfOccupiedRooms;
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	@Override
	public String toString() {
		String st = "Passenger Ship: " + super.toString();
		if (getJobs().size() == 0)
			return st;
		for (Job mj : getJobs())
			st += "\n       - " + mj;
		return st;
	} // end method toString

}
