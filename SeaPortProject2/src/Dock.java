import java.util.Scanner;

/**
 * File: Dock.java 
 * Author: Maria Tkacheva 
 * Date: March 24, 2018
 * Assignment:Project 1 
 * UMUC CMSC 335 
 * Dock class extends Thing and can have ship
 * standing at the dock and dock is in the port (assigned to a port)
 */
public class Dock extends Thing {

	private Ship ship;
	private SeaPort port;

	public Dock(Scanner sc) {
		super(sc);
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public SeaPort getPort() {
		return port;
	}

	public void setPort(SeaPort port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "Dock: " + super.toString() + "\n  Ship: " + ship.toString();
	}// end method toString
}
