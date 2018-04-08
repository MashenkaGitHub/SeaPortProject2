import java.util.Scanner;

/**
 * File: CargoShip.java 
 * Author: Maria Tkacheva 
 * Date: March 24, 2018
 * Assignment:Project 1 
 * UMUC CMSC 335 
 * CargoShip class extends Ship and has cargo
 * ship specific variables
 */
public class CargoShip extends Ship {
	double cargoValue;
	double cargoVolume;
	double cargoWeight;

	public CargoShip(Scanner sc) {
		super(sc);
		cargoWeight = sc.nextDouble();
		cargoVolume = sc.nextDouble();
		cargoValue = sc.nextDouble();
	}

	public double getCargoValue() {
		return cargoValue;
	}

	public void setCargoValue(double cargoValue) {
		this.cargoValue = cargoValue;
	}

	public double getCargoVolume() {
		return cargoVolume;
	}

	public void setCargoVolume(double cargoVolume) {
		this.cargoVolume = cargoVolume;
	}

	public double getCargoWeight() {
		return cargoWeight;
	}

	public void setCargoWeight(double cargoWeight) {
		this.cargoWeight = cargoWeight;
	}

	@Override
	public String toString() {
		return "Cargo Ship: " + super.toString();
	}// end method toString
}
