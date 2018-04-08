import java.util.ArrayList;
import java.util.Scanner;

/**
 * File: SeaPort.java 
 * Author: Maria Tkacheva 
 * Date: April 8, 2018
 * Assignment:Project 2  
 * UMUC CMSC 335 
 * SeaPort class extends Thing and has
 * ArrayLists to hold Port details
 */
public class SeaPort extends Thing {
	private ArrayList<Dock> docks;
	private ArrayList<Ship> que;
	private ArrayList<Ship> ships;
	private ArrayList<Person> persons;

	
	public SeaPort(Scanner sc) {
		super(sc);
		docks = new ArrayList<>();
		ships = new ArrayList<>();
		que = new ArrayList<>();
		persons = new ArrayList<>();

	} // end end Scanner constructor

	public ArrayList<Dock> getDocks() {
		return docks;
	}

	public void setDocks(ArrayList<Dock> docks) {
		this.docks = docks;
	}

	public ArrayList<Ship> getQue() {
		return que;
	}

	public void setQue(ArrayList<Ship> que) {
		this.que = que;
	}

	public ArrayList<Ship> getShips() {
		return ships;
	}

	public void setShips(ArrayList<Ship> ships) {
		this.ships = ships;
	}

	public ArrayList<Person> getPersons() {
		return persons;
	}

	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		String st = "\n\nSeaPort: " + super.toString();
		for (Dock md : docks)
			st += "\n" + md.toString();
		st += "\n\n --- List of all ships in que:";
		for (Ship ms : que)
			st += "\n   > " + ms.toString();
		st += "\n\n --- List of all ships:";
		for (Ship ms : ships)
			st += "\n   > " + ms.toString();
		st += "\n\n --- List of all persons:";
		for (Person mp : persons)
			st += "\n   > " + mp.toString();
		return st;
	} // end method toString


}
